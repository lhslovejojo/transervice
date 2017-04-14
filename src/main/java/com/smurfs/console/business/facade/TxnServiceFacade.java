package com.smurfs.console.business.facade;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blockchain.service.TxnService;
import com.blockchain.service.tran.BalanceFrozenRequest;
import com.blockchain.service.tran.ConCludeRequest;
import com.blockchain.service.tran.DelegateRequest;
import com.blockchain.service.tran.PositionRequest;
import com.blockchain.service.tran.RechargeRequest;
import com.blockchain.service.tran.SettlePriceRequest;
import com.blockchain.service.tran.SettlementRequest;
import com.blockchain.service.tran.TakeNowRequest;
import com.blockchain.service.tran.TollRequest;
import com.blockchain.service.tran.TranResponse;
import com.smurfs.console.business.domain.txn.ConCludeSheet;
import com.smurfs.console.business.domain.txn.FundFlow;
import com.smurfs.console.business.domain.txn.SettlePrice;
import com.smurfs.console.business.domain.txn.Toll;
import com.smurfs.console.business.service.ConCludeService;
import com.smurfs.console.business.service.FundFlowService;
import com.smurfs.console.business.service.SettlePriceService;
import com.smurfs.console.business.service.TollService;
import com.smurfs.console.business.service.UserInfService;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ReturnConstants;
import com.smurfs.console.util.BeanCopyUtil;

@Service("txnService")
public class TxnServiceFacade implements TxnService {
	public static Logger log = LoggerFactory.getLogger(AccountServiceFacade.class);
	@Autowired
	private FundFlowService fundFlowService;

	@Autowired
	private ConCludeService conCludeService;

	@Autowired
	private TollService tollService;
	@Autowired
	private UserInfService userInfService;
	@Autowired
	private SettlePriceService settlePriceService;

	/**
	 * 客户入金
	 */
	@Override
	public TranResponse recharge(RechargeRequest request) {
		try {
			FundFlow flow = BeanCopyUtil.cvtDozer(request, FundFlow.class);
			String eviNo = fundFlowService.fundIn(flow);
			return createSuccTran(request.getRequestId(), eviNo);
		} catch (Exception e) {
			log.error("recharge error requstId:" + request.getRequestId() + "serialNo:" + request.getSerialNo(), e);
			return createFailTran(request.getRequestId());
		}
	}

	/**
	 * 客户出金
	 */
	@Override
	public TranResponse takeNow(TakeNowRequest request) {
		try {
			FundFlow flow = BeanCopyUtil.cvtDozer(request, FundFlow.class);
			String eviNo = fundFlowService.fundOut(flow);
			return createSuccTran(request.getRequestId(), eviNo);
		} catch (Exception e) {
			log.error("takeNow error requstId:" + request.getRequestId() + "serialNo:" + request.getSerialNo(), e);
			return createFailTran(request.getRequestId());
		}
	}

	/**
	 * 成交
	 */
	@Override
	public TranResponse conClude(ConCludeRequest request) {
		try {
			ConCludeSheet sheet = BeanCopyUtil.cvtDozer(request, ConCludeSheet.class);
			String eviNo = conCludeService.createConClude(sheet);
			return createSuccTran(request.getRequestId(), eviNo);
		} catch (Exception e) {
			log.error("takeNow error requstId:" + request.getRequestId() + "serialNo:" + request.getBusiNo(), e);
			return createFailTran(request.getRequestId());
		}
	}

	/**
	 * 收费单
	 */
	@Override
	public TranResponse toll(TollRequest request) {
		try {
			Toll toll = BeanCopyUtil.cvtDozer(request, Toll.class);
			String eviNo = tollService.createToll(toll);
			return createSuccTran(request.getRequestId(), eviNo);
		} catch (Exception e) {
			log.error("takeNow error requstId:" + request.getRequestId() + "serialNo:" + request.getBusiNo(), e);
			return createFailTran(request.getRequestId());
		}
	}

	/**
	 * 持仓信息 更新 滞纳金、履约准备金、结算价
	 */
	@Override
	public TranResponse position(PositionRequest request) {
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("memCode", request.getMemCode());
			// 根据商编更新滞纳金
			if (request.getMemCode() != null && request.getDelayFees() != null && request.getDelayFees().doubleValue() > 0) {
				map.put(Constants.DELAY_ACCOUNT_AMOUNT, request.getDelayFees().toString());
			}
			// 根据商编更新履约准备金
			if (request.getMemCode() != null && request.getDepositBalance() != null
					&& request.getDepositBalance().doubleValue() > 0) {
				map.put(Constants.DEPOSIT_ACCOUNT_AMOUNT, request.getDepositBalance().toString());
			}
//			// 根据商编和产品编码更新资产结算价
//			if (request.getMemCode() != null && request.getProductCode() != null && request.getSettlePrice() != null) {
//				map.put(Constants.ASSET_SETTLE_PRICE_PREFIX + request.getProductCode(),
//						request.getSettlePrice().toString());
//			}
			if (userInfService.updateAccountByMap(request.getMemCode(), map)) {
				return createSuccTran(request.getRequestId());
			} else {
				return createFailTran(request.getRequestId());
			}
		} catch (Exception e) {
			log.error("takeNow error requstId:" + request.getRequestId() + "serialNo:" + request.getBusiNo(), e);
			return createFailTran(request.getRequestId());
		}
	}

	@Override
	public TranResponse frozenBalance(BalanceFrozenRequest request) {
		FundFlow flow = BeanCopyUtil.cvtDozer(request, FundFlow.class);
		String eviNo = fundFlowService.fundFrozen(flow);
		return createSuccTran(request.getRequestId(), eviNo);
	}

	@Override
	public TranResponse delegate(DelegateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TranResponse settle(SettlementRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private TranResponse createSuccTran(String id, String eviNo) {
		TranResponse resp = new TranResponse();
		resp.setResponseCode(ReturnConstants.SUCCESS);
		resp.setRequestId(id);
		resp.setEvidenceNo(eviNo);
		return resp;
	}

	private TranResponse createSuccTran(String id) {
		TranResponse resp = new TranResponse();
		resp.setResponseCode(ReturnConstants.SUCCESS);
		resp.setRequestId(id);
		return resp;
	}

	private TranResponse createFailTran(String id) {
		TranResponse resp = new TranResponse();
		resp.setResponseCode(ReturnConstants.FAIL);
		resp.setRequestId(id);
		// resp.setEvidenceNo(eviNo);
		return resp;
	}

	@Override
	public TranResponse syncSettlePrice(SettlePriceRequest request) {
		try {
			SettlePrice settlePrice = BeanCopyUtil.cvtDozer(request, SettlePrice.class);
			String eviNo = settlePriceService.updateSettlePrice(settlePrice);
			return createSuccTran(request.getInitDate());
		} catch (Exception e) {
			log.error("syncSettlePrice error request:" + request.toString(), e);
			return createFailTran(request.getInitDate());
		}
	}
}
