package com.smurfs.console.business.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blockchain.service.QueryService;
import com.blockchain.service.page.PageResponse;
import com.blockchain.service.query.AllBalanceResponse;
import com.blockchain.service.query.BalanceResponse;
import com.blockchain.service.query.DepositHisRequest;
import com.blockchain.service.query.DepositHisResponse;
import com.blockchain.service.query.FundChangeRequest;
import com.blockchain.service.query.FundChangeResponse;
import com.blockchain.service.query.TradeRequest;
import com.blockchain.service.query.TradeResponse;
import com.blockchain.service.query.UserRequest;
import com.blockchain.service.query.UserResponse;
import com.blockchain.service.query.domain.PositionResponse;
import com.blockchain.service.tran.TranResponse;
import com.smurfs.console.business.service.ConCludeService;
import com.smurfs.console.business.service.FundFlowService;
import com.smurfs.console.business.service.TollService;
import com.smurfs.console.business.service.UserInfService;
import com.smurfs.console.constants.ReturnConstants;

@Service("queryService")
public class QueryServiceFacade implements QueryService {
	public static Logger log = LoggerFactory.getLogger(AccountServiceFacade.class);
	@Autowired
	private FundFlowService fundFlowService;

	@Autowired
	private ConCludeService conCludeService;

	@Autowired
	private TollService tollService;
	@Autowired
	private UserInfService userInfService;

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

	/**
	 * 根据商编查询相关信息 返回一条
	 */
	@Override
	public UserResponse getUserByMemCode(String memCode) {
		return userInfService.getOneAccountByMemCode(memCode);
	}

	/**
	 * 根据交易所编码、商编、商户名称、资金账号，查询账号 返回带分页的多条
	 */
	@Override
	public PageResponse<UserResponse> queryUser(UserRequest request) {
		return userInfService.queryByParam(request);
	}

	/**
	 * 出入金流水凭证查询
	 */
	@Override
	public PageResponse<TradeResponse> queryTrade(TradeRequest request) {
		// TODO Auto-generated method stub
		return fundFlowService.query(request);
	}

	/**
	 * 查询资金账户余额
	 */
	@Override
	public BalanceResponse queryBal(String exchangeId, String memCode) {
		return userInfService.queryBal(exchangeId, memCode);
	}

	/**
	 * 查询资金账户余额变动情况
	 */
	@Override
	public PageResponse<FundChangeResponse> queryFund(FundChangeRequest request) {
		// TODO Auto-generated method stub
		return userInfService.queryBalChangeHis(request);
	}

	/**
	 * 查询总资产情况
	 */
	@Override
	public AllBalanceResponse queryAllBal(String exchangeId, String memCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查询持仓汇总
	 */
	@Override
	public List<PositionResponse> queryPositionAsset(String exchangeId, String memCode,String productCode) {
		// TODO Auto-generated method stub
		return userInfService.queryPositionAsset(exchangeId, memCode, productCode);
	}
	/**
	 * 查询持仓明细
	 */
	@Override
	public List<PositionResponse> queryPositionAssetHis(String exchangeId, String memCode,String productCode) {
		// TODO Auto-generated method stub
		return userInfService.queryPositionAssetHis(exchangeId, memCode, productCode);
	}


	@Override
	public DepositHisResponse syncBankInfo(DepositHisRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
