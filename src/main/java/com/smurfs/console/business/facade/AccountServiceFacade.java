package com.smurfs.console.business.facade;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.blockchain.service.AccountService;
import com.blockchain.service.customer.AccountAssetUpdateRequest;
import com.blockchain.service.customer.AccountBalUpdateRequest;
import com.blockchain.service.customer.AccountCloseRequest;
import com.blockchain.service.customer.AccountFrozenRequest;
import com.blockchain.service.customer.AccountFrozenResponse;
import com.blockchain.service.customer.BankInfoSyncRequest;
import com.blockchain.service.customer.CustomerResponse;
import com.blockchain.service.customer.GoodsInfoSyncRequest;
import com.blockchain.service.customer.UserInfoSyncRequest;
import com.smurfs.console.business.domain.basic.BankInf;
import com.smurfs.console.business.domain.basic.GoodsInf;
import com.smurfs.console.business.domain.basic.UserInf;
import com.smurfs.console.business.service.BankInfService;
import com.smurfs.console.business.service.GoodsInfService;
import com.smurfs.console.business.service.UserInfService;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ReturnConstants;
import com.smurfs.console.util.BeanCopyUtil;

@Service
public class AccountServiceFacade implements AccountService {
	public static Logger log = LoggerFactory.getLogger(AccountServiceFacade.class);
	@Autowired
	private BankInfService bankInfService;
	@Autowired
	private GoodsInfService goodsInfService;
	@Autowired
	private UserInfService userInfService;

	/**
	 * 同步用户注册信息
	 */
	@Override
	public CustomerResponse syncUserInfo(UserInfoSyncRequest request) {
		try {
			UserInf userInf = BeanCopyUtil.cvtDozer(request, UserInf.class);
			userInfService.syncAccount(userInf);
			return createSuccCustomer();
		} catch (Exception e) {
			log.error("syncUserInfo error serialNo:" + request.getSerialNo(), e);
			return createFailCustomer();
		}
	}

	public void queryByMemCode(String memCode) {

	}

	@Override
	public AccountFrozenResponse frozenAccount(AccountFrozenRequest request) {
		String evnNo = userInfService.frozenService(request.getMemCode(), request.getExchangeId(),
				request.getFrozenType());
		return createSuccAccount(evnNo);
	}

	@Override
	public CustomerResponse closeAccount(AccountCloseRequest request) {
		boolean success = userInfService.closeAccount(request.getMemCode(), request.getExchangeId());
		if (success) {
			return createSuccCustomer();
		} else {
			return createFailCustomer(ReturnConstants.DBRC_NO_EXIST);
		}
	}

	@Override
	public CustomerResponse syncGoodsInfo(GoodsInfoSyncRequest request) {
		GoodsInf info = BeanCopyUtil.cvtDozer(request, GoodsInf.class);
		boolean success = goodsInfService.createGoodsInf(info);
		if (success) {
			return createSuccCustomer();
		} else {
			return createFailCustomer(ReturnConstants.DBRC_NO_EXIST);
		}
	}

	@Override
	public CustomerResponse syncBankInfo(BankInfoSyncRequest request) {
		BankInf info = BeanCopyUtil.cvtDozer(request, BankInf.class);
		boolean success = bankInfService.createBankInf(info);
		if (success) {
			return createSuccCustomer();
		} else {
			return createFailCustomer(ReturnConstants.DBRC_NO_EXIST);
		}
	}

	private CustomerResponse createSuccCustomer() {
		CustomerResponse resp = new CustomerResponse();
		resp.setResponseCode(ReturnConstants.SUCCESS);
		return resp;
	}

	private CustomerResponse createFailCustomer(String errCode) {
		CustomerResponse resp = new CustomerResponse();
		resp.setResponseCode(errCode);
		return resp;
	}

	private CustomerResponse createFailCustomer() {
		CustomerResponse resp = new CustomerResponse();
		resp.setResponseCode(ReturnConstants.FAIL);
		return resp;
	}

	private AccountFrozenResponse createSuccAccount(String eviNo) {
		AccountFrozenResponse resp = new AccountFrozenResponse();
		resp.setResponseCode(ReturnConstants.FAIL);
		resp.setEvidenceNo(eviNo);
		return resp;
	}

	@Override
	public CustomerResponse batchUpdateBal(List<AccountBalUpdateRequest> requestList) {
		if (!CollectionUtils.isEmpty(requestList)) {
			for (AccountBalUpdateRequest request : requestList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("memCode", request.getMemCode());
				map.put("accountID", request.getMemCode());
				if (request.getTotalBal() != null) {
					map.put(Constants.FUND_ALL_RMB, request.getTotalBal().toString());
				}
				if (request.getAbleBal() != null) {
					map.put(Constants.FUND_ABLE_RMB, request.getAbleBal().toString());
				}

				if (request.getAdvanceBal() != null) {
					map.put(Constants.FUND_TAKE_RMB, request.getAdvanceBal().toString());
				}
				userInfService.updateAccountByMap(request.getMemCode(), map);
			}
		}

		return createSuccCustomer();
	}

	@Override
	public CustomerResponse batchUpdateAssetBal(List<AccountAssetUpdateRequest> requestList) {
		if (!CollectionUtils.isEmpty(requestList)) {
			for (AccountAssetUpdateRequest request : requestList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("memCode", request.getMemCode());
				map.put("accountID", request.getMemCode());
				map.put(Constants.ASSET_ACCOUNT_PREFIX + request.getProductCode(), request.getQuantity().toString());
				map.put(Constants.ASSET_ACCOUNT_PREFIX + request.getProductCode() + Constants.ASSET_HOLD_AMOUNT_SUFFIX,
						String.valueOf(request.getQuantity().multiply(request.getHoldPrice())));
				userInfService.updateAccountByMap(request.getMemCode(), map);
			}
		}

		return createSuccCustomer();
	}
}
