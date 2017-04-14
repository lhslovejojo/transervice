package com.smurfs.console.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blockchain.service.page.PageResponse;
import com.blockchain.service.query.BalanceResponse;
import com.blockchain.service.query.FundChangeRequest;
import com.blockchain.service.query.FundChangeResponse;
import com.blockchain.service.query.UserRequest;
import com.blockchain.service.query.UserResponse;
import com.blockchain.service.query.domain.PositionResponse;
import com.google.gson.JsonElement;
import com.smurfs.console.business.dal.basic.UserInfDal;
import com.smurfs.console.business.domain.basic.UserInf;
import com.smurfs.console.business.jbcc.BusinessReturnResultBean;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.util.BeanCopyUtil;
import com.smurfs.console.util.DateUtil;
import com.smurfs.console.util.NumberUtil;
import com.smurfs.console.util.StringUtil;

@Service("userInfService")
public class UserInfService {
	public static Logger log = LoggerFactory.getLogger(UserInfService.class);
	@Autowired
	private UserInfDal userInfDal;
	@Autowired
	private SettlePriceService settlePriceService;

	public String frozenService(String memCode, String exchangeId, short frozenType) {
		if (frozenType == 0) {
			return userInfDal.frozen(memCode, exchangeId);
		} else {
			return userInfDal.unfrozen(memCode, exchangeId);
		}
	}

	public boolean closeAccount(String memCode, String exchangeId) {
		return userInfDal.closeAccount(memCode, exchangeId);
	}

	public String syncAccount(UserInf userInf) {
		return userInfDal.syncAccount(userInf);
	}

	/**
	 * 修改账户余额，只能是非量化属性
	 * 
	 * @param memCode
	 *            商编
	 * @param map
	 *            要修改的属性
	 * @return
	 */
	public boolean updateAccountByMap(String memCode, HashMap<String, String> map) {
		return userInfDal.updateAccountByMap(memCode, map);
	}

	/**
	 * 通过用户编码查询 用户信息，带有资金账号信息;
	 * 
	 * @param memCode
	 * @return
	 */
	public UserResponse getOneAccountByMemCode(String memCode) {
		String jsonStr = userInfDal.queryByAccountId(memCode);
		if (StringUtil.isNotBlank(jsonStr)) {
			return bindJsonToUser(jsonStr);
		}
		return null;
	}

	/**
	 * 通过用户编码查询 用户信息，带有资金账号信息;
	 * 
	 * @param memCode
	 * @return
	 */
	public PageResponse<UserResponse> queryByParam(UserRequest request) {
		PageResponse<UserResponse> page = new PageResponse<UserResponse>();
		page.setPageNum(request.getPageNum());
		page.setPageSize(request.getPageSize());
		page.setTotalSize(0);
		List<UserResponse> userList = new ArrayList<UserResponse>();
		String jsonMsg = userInfDal.queryByKey(request);
		if (StringUtil.isNotBlank(jsonMsg)) {
			BusinessReturnResultBean returnBean = JSON.parseObject(jsonMsg, BusinessReturnResultBean.class);
			if (returnBean != null && returnBean.getData() != null) {
				page.setTotalSize(returnBean.getTotalCount());
				JSONArray data = (JSONArray) returnBean.getData();
				Iterator<Object> it = data.iterator();
				while (it.hasNext()) {
					JSONObject ob = (JSONObject) it.next();
					UserResponse user = BeanCopyUtil.cvtDozer(ob, UserResponse.class);
					BalanceResponse balanceResponse = new BalanceResponse();
					balanceResponse.setAbleBal(NumberUtil.getBigDecimalFromStr(ob.getString(Constants.FUND_ABLE_RMB)));
					balanceResponse.setTotalBal(NumberUtil.getBigDecimalFromStr(ob.getString(Constants.FUND_ALL_RMB)));
					balanceResponse
							.setAdvanceBal(NumberUtil.getBigDecimalFromStr(ob.getString(Constants.FUND_TAKE_RMB)));
					balanceResponse.setFundAccount(user.getTradeAccount());
					balanceResponse.setExchangeFundAccount(user.getExchangeFundAccount());
					balanceResponse.setExchangeId(user.getExchangeId());
					user.setBalanceResponse(balanceResponse);
					userList.add(user);
				}
			}
			page.setList(userList);

		}
		return page;
	}

	private UserResponse bindJsonToUser(String jsonStr) {
		Map<String, Map<Long, String>> jsonMap = JSON.parseObject(jsonStr, HashMap.class);
		Iterator<String> keys = jsonMap.keySet().iterator();
		HashMap<String, Object> map = new HashMap<String, Object>();
		while (keys.hasNext()) {
			String key = keys.next();
			Map<Long, String> objMap = jsonMap.get(key);
			if (objMap != null) {
				map.put(key, objMap.values().iterator().next());
			}
		}
		DozerBeanMapper mapper = new DozerBeanMapper();
		UserResponse user = mapper.map(map, UserResponse.class);
		BalanceResponse balanceResponse = new BalanceResponse();
		if (jsonMap.get(Constants.FUND_ABLE_RMB) != null) {
			balanceResponse.setAbleBal(
					NumberUtil.getBigDecimalFromStr(jsonMap.get(Constants.FUND_ABLE_RMB).values().iterator().next()));
		} else {
			balanceResponse.setAbleBal(new BigDecimal(0));
		}
		if (jsonMap.get(Constants.FUND_ALL_RMB) != null) {
			balanceResponse.setTotalBal(
					NumberUtil.getBigDecimalFromStr(jsonMap.get(Constants.FUND_ALL_RMB).values().iterator().next()));
		} else {
			balanceResponse.setTotalBal(new BigDecimal(0));
		}
		if (jsonMap.get(Constants.FUND_TAKE_RMB) != null) {
			balanceResponse.setAdvanceBal(
					NumberUtil.getBigDecimalFromStr(jsonMap.get(Constants.FUND_TAKE_RMB).values().iterator().next()));
		} else {
			balanceResponse.setAdvanceBal(new BigDecimal(0));
		}
		balanceResponse.setFundAccount(user.getTradeAccount());
		user.setBalanceResponse(balanceResponse);
		return user;
	}

	/**
	 * 通过用户编码查询 用户信息，带有资金账号信息;
	 * 
	 * @param memCode
	 * @return
	 */
	public BalanceResponse queryBal(String exchangeId, String memCode) {
		HashMap<String, String> searchParam = new HashMap<String, String>();
		UserRequest request = new UserRequest();
		request.setMemCode(memCode);
		request.setExchangeId(exchangeId);
		request.setPageSize(1);
		request.setPageNum(1);
		UserResponse userResponse = getOneAccountByMemCode(memCode);
		if (userResponse != null && userResponse.getBalanceResponse() != null)
			return userResponse.getBalanceResponse();
		else
			return null;
	}

	/**
	 * 查询资金账户的变动历史
	 * 
	 * @param exchangeId
	 * @param memCode
	 * @return
	 */
	public PageResponse<FundChangeResponse> queryBalChangeHis(FundChangeRequest request) {
		PageResponse<FundChangeResponse> page = new PageResponse<FundChangeResponse>();
		page.setPageNum(request.getPageNum());
		page.setPageSize(request.getPageSize());
		page.setTotalSize(0);
		String jsonMsg = userInfDal.queryAccountBalHis(request.getMemCode());
		if (StringUtil.isNotBlank(jsonMsg)) {
			UserResponse response = new UserResponse();
			response = JSON.parseObject(jsonMsg, UserResponse.class);
			JSONObject jsonObject = JSON.parseObject(jsonMsg);
			BalanceResponse balanceResponse = new BalanceResponse();
			balanceResponse.setAbleBal(NumberUtil.getBigDecimalFromStr(jsonObject.getString(Constants.FUND_ABLE_RMB)));
			balanceResponse.setTotalBal(NumberUtil.getBigDecimalFromStr(jsonObject.getString(Constants.FUND_ALL_RMB)));
			balanceResponse
					.setAdvanceBal(NumberUtil.getBigDecimalFromStr(jsonObject.getString(Constants.FUND_TAKE_RMB)));
			balanceResponse.setFundAccount(jsonObject.getString("tradeAccount"));
			response.setBalanceResponse(balanceResponse);
		}
		return page;
	}

	/**
	 * 查询持仓情况
	 * 
	 * @param exchangeId
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	public List<PositionResponse> queryPositionAsset(String exchangeId, String memCode, String productCode) {
		List<PositionResponse> list = new ArrayList<PositionResponse>();
		UserResponse user = getOneAccountByMemCode(memCode);
		String jsonMsg = userInfDal.queryAsset(exchangeId, memCode, productCode);
		if (StringUtil.isNotBlank(jsonMsg)) {
			Map<String, Map<Long, String>> jsonMap = JSON.parseObject(jsonMsg, HashMap.class);
			Iterator<String> keys = jsonMap.keySet().iterator();
			HashMap<String, String> map = new HashMap<String, String>();
			while (keys.hasNext()) {
				String key = keys.next();
				Map<Long, String> objMap = jsonMap.get(key);
				if (objMap != null) {
					map.put(key, objMap.values().iterator().next());
				}
			}
			keys = map.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (!key.endsWith(Constants.ASSET_HOLD_AMOUNT_SUFFIX)) {
					PositionResponse position = new PositionResponse();
					String proCode = key.substring(Constants.ASSET_ACCOUNT_PREFIX.length());
					position.setProductCode(proCode);
					position.setFundAccountClear(user.getFundAccountClear());
					position.setHoldQuantity(NumberUtil.getBigDecimalFromStr(map.get(key)));
					position.setHoldPrice(
							NumberUtil.getBigDecimalFromStr(map.get(key + Constants.ASSET_HOLD_AMOUNT_SUFFIX)));
					position.setMemCodeClear(user.getMemCodeClear());
					position.setMemCode(user.getMemCode());
					position.setMarketPrice(settlePriceService.getLastSettlePrice(proCode));
					position.setMarketValue(
							NumberUtil.getBigDecimalFromStr(map.get(key)).multiply(position.getMarketPrice()));
					list.add(position);
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * 查询持仓历史情况
	 * 
	 * @param exchangeId
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	public List<PositionResponse> queryPositionAssetHis(String exchangeId, String memCode, String productCode) {
		List<PositionResponse> list = new ArrayList<PositionResponse>();
		UserResponse user = getOneAccountByMemCode(memCode);
		String jsonMsg = userInfDal.queryAssetHis(exchangeId, memCode, productCode);
		if (StringUtil.isNotBlank(jsonMsg)) {
			Map<String, Map<String, String>> jsonMap = JSON.parseObject(jsonMsg, HashMap.class);
			Iterator<String> keys = jsonMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (!key.endsWith(Constants.ASSET_HOLD_AMOUNT_SUFFIX)) {
					String proCode = key.substring(Constants.ASSET_ACCOUNT_PREFIX.length());
					Map<String, String> assetNumMap = jsonMap.get(key);
					Map<String, String> assetAmountMap = jsonMap.get(key + Constants.ASSET_HOLD_AMOUNT_SUFFIX);
					Iterator<String> assetNumMapKeys = assetNumMap.keySet().iterator();
					while (assetNumMapKeys.hasNext()) {
						String assetNumKey = assetNumMapKeys.next();
						PositionResponse position = new PositionResponse();
						position.setProductCode(proCode);
						position.setFundAccountClear(user.getFundAccountClear());
						position.setHoldQuantity(NumberUtil.getBigDecimalFromStr(assetNumMap.get(assetNumKey)));
						position.setHoldPrice(NumberUtil.getBigDecimalFromStr(assetAmountMap.get(assetNumKey)));
						position.setMemCodeClear(user.getMemCodeClear());
						position.setMemCode(user.getMemCode());
						position.setMarketPrice(settlePriceService.getLastSettlePrice(productCode));
						position.setMarketValue(NumberUtil.getBigDecimalFromStr(assetNumMap.get(assetNumKey))
								.multiply(settlePriceService.getLastSettlePrice(productCode)));
						position.setChangeTime(
								DateUtil.DateToString(new Date(Long.parseLong(assetNumKey)), "yyyy-MM-dd HH:mm:ss"));
						list.add(position);
					}
				}
			}
			return list;
		}
		return null;
	}

}
