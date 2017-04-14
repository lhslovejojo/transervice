package com.smurfs.console.business.jbcc;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smurfs.console.constants.BusinessTypeEnum;
import com.smurfs.console.constants.Constants;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.ABCAtomicQuantifyBean;

/**
 * 分解原始分离器，用于将原始交易分离为原子单方交易 涉及到三方：买方、卖方、平台
 */
public class SplitTxUtils {
	public static Logger log = LoggerFactory.getLogger(SplitTxUtils.class);

	/**
	 * 成交 分解原始交易为原子单方交易，涉及到三方：买方、卖方、平台
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForConClude(TBCOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 分解交易双方和中介方，共3方交易,拆解成6笔单方交易
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfAllFund = new ABCAtomicQuantifyBean(); // 购买方资金总和
		ABCAtomicQuantifyBean selfAbleFund = new ABCAtomicQuantifyBean(); // 购买方可用资金总和
		ABCAtomicQuantifyBean selfTakeFund = new ABCAtomicQuantifyBean(); // 购买方可提资金总和
		ABCAtomicQuantifyBean selfAsset = new ABCAtomicQuantifyBean(); // 购买方资产数量
		ABCAtomicQuantifyBean selfAssetHold = new ABCAtomicQuantifyBean(); // 购买方持仓成本金额
		ABCAtomicQuantifyBean peerAllFund = new ABCAtomicQuantifyBean(); // 卖方资金总和
		ABCAtomicQuantifyBean peerAbleFund = new ABCAtomicQuantifyBean(); // 卖方可用资金总和
		ABCAtomicQuantifyBean peerTakeFund = new ABCAtomicQuantifyBean(); // 卖方可提资金总和
		ABCAtomicQuantifyBean peerAsset = new ABCAtomicQuantifyBean(); // 卖方资产数量
		ABCAtomicQuantifyBean peerAssetHold = new ABCAtomicQuantifyBean(); // 卖方资产持仓成本
		ABCAtomicQuantifyBean platformFee = new ABCAtomicQuantifyBean(); // 交易平台方，收取交易费用

		try {
			if ("1".equals(tob.getTxTradeDir())) {// 买方向的， 发起方为买方，参与方为卖方

				// 1.购买方,资金交易
				// 资金总和
				selfAllFund.setAccountID(tob.getMemCode());
				selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
				selfAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAllFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOpenPoundage()))); // 购买商品方，资金为正数，同时需要缴纳平台费用
				selfAllFund.setTxID(tob.getTxID());
				selfAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAllFund);
				// 可用资金
				selfAbleFund.setAccountID(tob.getMemCode());
				selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				selfAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAbleFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOpenPoundage()))); // 购买商品方，资金为正数，同时需要缴纳平台费用
				selfAbleFund.setTxID(tob.getTxID());
				selfAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAbleFund);
				// 可提资金
				selfTakeFund.setAccountID(tob.getMemCode());
				selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				selfTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfTakeFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOpenPoundage()))); // 购买商品方，资金为正数，同时需要缴纳平台费用
				selfTakeFund.setTxID(tob.getTxID());
				selfTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfTakeFund);

				// 2.购买方,单方资产交易
				selfAsset.setAccountID(tob.getMemCode());
				selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				selfAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAsset.setAmount(tob.getOrderQuantity()); // 购买商品方，资产为正数
				selfAsset.setTxID(tob.getTxID());
				selfAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
				// 购买方持仓成本
				selfAssetHold.setAccountID(tob.getMemCode());
				selfAssetHold.setQuantifyName(
						Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode() + Constants.ASSET_HOLD_AMOUNT_SUFFIX);
				selfAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAssetHold.setAmount(tob.getDealTotalPrice()); // 购买商品方，资产为正数
				selfAssetHold.setTxID(tob.getTxID());
				selfAssetHold.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAssetHold);

				// 3.卖方,单方资金交易
				peerAllFund.setAccountID(tob.getOppMemCode());
				peerAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
				peerAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAllFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOppPoundage())); // 出售商品方，资金为负数，同时需要缴纳平台费用
				peerAllFund.setTxID(tob.getTxID());
				peerAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAllFund);

				peerAbleFund.setAccountID(tob.getOppMemCode());
				peerAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				peerAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAbleFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOppPoundage())); // 出售商品方，资金为负数，同时需要缴纳平台费用
				peerAbleFund.setTxID(tob.getTxID());
				peerAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAbleFund);

				peerTakeFund.setAccountID(tob.getOppMemCode());
				peerTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				peerTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerTakeFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOppPoundage())); // 出售商品方，资金为负数，同时需要缴纳平台费用
				peerTakeFund.setTxID(tob.getTxID());
				peerTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerTakeFund);

				// 4.卖方,单方资产交易
				peerAsset.setAccountID(tob.getOppMemCode());
				peerAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				peerAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAsset.setAmount(new BigDecimal(-1).multiply(tob.getOrderQuantity())); // 出售商品方，资产为负数
				peerAsset.setTxID(tob.getTxID());
				peerAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAsset);

				peerAssetHold.setAccountID(tob.getOppMemCode());
				peerAssetHold.setQuantifyName(
						Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode() + Constants.ASSET_HOLD_AMOUNT_SUFFIX);
				peerAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAssetHold.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice())); // 出售商品方，资产为负数
				peerAssetHold.setTxID(tob.getTxID());
				peerAssetHold.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAssetHold);

				// 5.缴纳平台费用，是由AB双方缴纳的平台费用之和
				platformFee.setAccountID(tob.getTxFeePlatformAccountID());
				platformFee.setQuantifyName(Constants.FUND_ALL_RMB);
				platformFee.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				platformFee.setAmount(tob.getOpenPoundage().add(tob.getOppPoundage())); // 交易发起方，AB双方缴纳平台费用的和
				platformFee.setTxID(tob.getTxID());
				platformFee.setDescription(tob.getTxDescription());
				quantifyInfo.add(platformFee);
			} else { // 卖方向的，发起方为卖方，参与方为买方
				// 1.卖方,资金交易
				// 资金总和
				log.info("tob.getTxTradeDir():" + tob.getTxTradeDir());
				selfAllFund.setAccountID(tob.getMemCode());
				selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
				selfAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAllFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOpenPoundage())); // 卖方商品方，资金为正数，同时需要缴纳平台费用
				selfAllFund.setTxID(tob.getTxID());
				selfAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAllFund);
				// 可用资金
				selfAbleFund.setAccountID(tob.getMemCode());
				selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				selfAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAbleFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOpenPoundage())); // 卖方商品方，资金为正数，同时需要缴纳平台费用
				selfAbleFund.setTxID(tob.getTxID());
				selfAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAbleFund);
				// 可提资金
				selfTakeFund.setAccountID(tob.getMemCode());
				selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				selfTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfTakeFund.setAmount(tob.getDealTotalPrice().subtract(tob.getOpenPoundage())); // 购买商品方，资金为正数，同时需要缴纳平台费用
				selfTakeFund.setTxID(tob.getTxID());
				selfTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfTakeFund);

				// 2.卖方,单方资产交易
				selfAsset.setAccountID(tob.getMemCode());
				selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				selfAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAsset.setAmount(new BigDecimal(-1).multiply(tob.getOrderQuantity())); // 卖方，资产为负数
				selfAsset.setTxID(tob.getTxID());
				selfAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
				// 卖方持仓成本
				selfAssetHold.setAccountID(tob.getMemCode());
				selfAssetHold.setQuantifyName(
						Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode() + Constants.ASSET_HOLD_AMOUNT_SUFFIX);
				selfAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				selfAssetHold.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice())); // 卖方，资产为负
				selfAssetHold.setTxID(tob.getTxID());
				selfAssetHold.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAssetHold);

				// 3.买方,单方资金交易
				peerAllFund.setAccountID(tob.getOppMemCode());
				peerAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
				peerAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAllFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOppPoundage()))); // 买方，资金为负数，同时需要缴纳平台费用
				peerAllFund.setTxID(tob.getTxID());
				peerAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAllFund);

				peerAbleFund.setAccountID(tob.getOppMemCode());
				peerAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				peerAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAbleFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOppPoundage()))); // 买方，资金为负数，同时需要缴纳平台费用
				peerAbleFund.setTxID(tob.getTxID());
				peerAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAbleFund);

				peerTakeFund.setAccountID(tob.getOppMemCode());
				peerTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				peerTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerTakeFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice().add(tob.getOppPoundage()))); // 买方，资金为负数，同时需要缴纳平台费用
				peerTakeFund.setTxID(tob.getTxID());
				peerTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerTakeFund);

				// 4.买方,单方资产交易
				peerAsset.setAccountID(tob.getOppMemCode());
				peerAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				peerAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAsset.setAmount(tob.getOrderQuantity()); // 买方，资产为正数
				peerAsset.setTxID(tob.getTxID());
				peerAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAsset);

				peerAssetHold.setAccountID(tob.getOppMemCode());
				peerAssetHold.setQuantifyName(
						Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode() + Constants.ASSET_HOLD_AMOUNT_SUFFIX);
				peerAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				peerAssetHold.setAmount(tob.getDealTotalPrice()); // 买方，资产为正数
				peerAssetHold.setTxID(tob.getTxID());
				peerAssetHold.setDescription(tob.getTxDescription());
				quantifyInfo.add(peerAssetHold);

				// 5.缴纳平台费用，是由AB双方缴纳的平台费用之和
				platformFee.setAccountID(tob.getTxFeePlatformAccountID());
				platformFee.setQuantifyName(Constants.FUND_ALL_RMB);
				platformFee.setQuantifyType(BusinessTypeEnum.TRADE.toString());
				platformFee.setAmount(tob.getOpenPoundage().add(tob.getOppPoundage())); // 交易发起方，AB双方缴纳平台费用的和
				platformFee.setTxID(tob.getTxID());
				platformFee.setDescription(tob.getTxDescription());
				quantifyInfo.add(platformFee);
			}

			aab.setQuantifyInfo(quantifyInfo); // 分解原始交易为原子单方交易，涉及到三方：买方、卖方、平台
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 针对注册用户的拆分原子交易
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForUser(TBCCustomizeOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 拆分一条
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfAllFund = new ABCAtomicQuantifyBean(); // 资金总和
		ABCAtomicQuantifyBean selfAbleFund = new ABCAtomicQuantifyBean(); // 可用资金总和
		ABCAtomicQuantifyBean selfTakeFund = new ABCAtomicQuantifyBean(); // 可提资金总和
		try {
			// 资金总和
			selfAllFund.setAccountID(tob.getMemCode());
			selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB); // 资金账户
			selfAllFund.setQuantifyType(BusinessTypeEnum.ACCOUNT.toString());
			selfAllFund.setAmount(new BigDecimal(0)); // 资金账户初始值
			selfAllFund.setTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAllFund);
			// 可用资金
			selfAbleFund.setAccountID(tob.getMemCode());
			selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			selfAbleFund.setQuantifyType(BusinessTypeEnum.ACCOUNT.toString());
			selfAbleFund.setAmount(new BigDecimal(0));
			selfAbleFund.setTxID(tob.getTxID());
			selfAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAbleFund);
			// 可提资金
			selfTakeFund.setAccountID(tob.getMemCode());
			selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			selfTakeFund.setQuantifyType(BusinessTypeEnum.ACCOUNT.toString());
			selfTakeFund.setAmount(new BigDecimal(0));
			selfTakeFund.setTxID(tob.getTxID());
			selfTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfTakeFund);

			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 针对注册用户的拆分原子交易
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForSettlePrice(TBCCustomizeOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();
		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 针对出入金拆分原子交易
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForFundFlow(TBCCustomizeOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 拆分一条
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfAllFund = new ABCAtomicQuantifyBean(); // 购买方资金总和
		ABCAtomicQuantifyBean selfAbleFund = new ABCAtomicQuantifyBean(); // 购买方可用资金总和
		ABCAtomicQuantifyBean selfTakeFund = new ABCAtomicQuantifyBean(); // 购买方可提资金总和
		ABCAtomicQuantifyBean selfAsset = new ABCAtomicQuantifyBean(); // 交易发起方
		ABCAtomicQuantifyBean platformFee = new ABCAtomicQuantifyBean(); // 交易发起方
		try {
			String busType = BusinessTypeEnum.FUND_IN.toString();
			if (tob.getDealTotalPrice() != null && tob.getDealTotalPrice().doubleValue() < 0)
				busType = BusinessTypeEnum.FUND_OUT.toString();
			// 1.充值提现 ，单方资金交易
			if (tob.getDealTotalPrice() != null) {
				// 资金总和
				selfAllFund.setAccountID(tob.getMemCode());
				selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB); // 资金账户
				selfAllFund.setQuantifyType(busType);
				selfAllFund.setAmount(tob.getDealTotalPrice()); // 资金账户初始值
				selfAllFund.setTxID(tob.getTxID());
				selfAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAllFund);
				// 可用资金
				selfAbleFund.setAccountID(tob.getMemCode());
				selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				selfAbleFund.setQuantifyType(busType);
				selfAbleFund.setAmount(tob.getDealTotalPrice());
				selfAbleFund.setTxID(tob.getTxID());
				selfAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAbleFund);
				// 可提资金
				selfTakeFund.setAccountID(tob.getMemCode());
				selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				selfTakeFund.setQuantifyType(busType);
				selfTakeFund.setAmount(tob.getDealTotalPrice());
				selfTakeFund.setTxID(tob.getTxID());
				selfTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfTakeFund);
			}
			// 2.如果有直接充资产
			if (tob.getProductCode() != null && tob.getOrderQuantity() != null) {
				selfAsset.setAccountID(tob.getMemCode());
				selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				selfAsset.setAmount(tob.getOrderQuantity());
				if (tob.getOrderQuantity().doubleValue() > 0) {
					selfAsset.setQuantifyType(BusinessTypeEnum.FUND_IN.toString()); // 充资产
				} else {
					selfAsset.setQuantifyType(BusinessTypeEnum.FUND_OUT.toString()); // 提出资产
				}
				selfAsset.setTxID(tob.getTxID());
				selfAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
			}
			// 3.如果平台费不为空，要缴纳平台费用
			if (tob.getOpenPoundage() != null) {
				platformFee.setAccountID(tob.getTxFeePlatformAccountID());
				platformFee.setQuantifyName(Constants.FUND_ALL_RMB);
				platformFee.setAmount(tob.getOpenPoundage()); // 提现的时候有平台手续费
				platformFee.setQuantifyType(busType);
				platformFee.setTxID(tob.getTxID());
				platformFee.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
			}
			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 分解外部收费单-资金转账
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForToll(TBCCustomizeOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 拆分两条
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfAllFund = new ABCAtomicQuantifyBean(); // 购买方资金总和
		ABCAtomicQuantifyBean selfAbleFund = new ABCAtomicQuantifyBean(); // 购买方可用资金总和
		ABCAtomicQuantifyBean selfTakeFund = new ABCAtomicQuantifyBean(); // 购买方可提资金总和
		ABCAtomicQuantifyBean peerAllFund = new ABCAtomicQuantifyBean(); // 卖方资金总和
		ABCAtomicQuantifyBean peerAbleFund = new ABCAtomicQuantifyBean(); // 卖方可用资金总和
		ABCAtomicQuantifyBean peerTakeFund = new ABCAtomicQuantifyBean(); // 卖方可提资金总和
		try {
			// 1. 发起方资金减少
			// 资金总和
			selfAllFund.setAccountID(tob.getMemCode());
			selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
			selfAllFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			selfAllFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAllFund.setTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAllFund);
			// 可用资金
			selfAbleFund.setAccountID(tob.getMemCode());
			selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			selfAbleFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			selfAbleFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAbleFund.setTxID(tob.getTxID());
			selfAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAbleFund);
			// 可提资金
			selfTakeFund.setAccountID(tob.getMemCode());
			selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			selfTakeFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			selfTakeFund.setAmount(new BigDecimal(-1).multiply(tob.getDealTotalPrice())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfTakeFund.setTxID(tob.getTxID());
			selfTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfTakeFund);

			// 2.接收方资金增加
			// 资金总和
			peerAllFund.setAccountID(tob.getOppMemCode());
			peerAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
			peerAllFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerAllFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAllFund.setTxID(tob.getTxID());
			peerAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAllFund);
			// 可用资金
			peerAbleFund.setAccountID(tob.getOppMemCode());
			peerAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			peerAbleFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerAbleFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAbleFund.setTxID(tob.getTxID());
			peerAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAbleFund);
			// 可提资金
			peerTakeFund.setAccountID(tob.getOppMemCode());
			peerTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			peerTakeFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerTakeFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerTakeFund.setTxID(tob.getTxID());
			peerTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerTakeFund);

			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 分解 持仓信息
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomicForPosition(TBCCustomizeOriginalPositionBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 拆分一条
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfAsset = new ABCAtomicQuantifyBean(); // 购买方资金总和
		try {
			// 1. 发起方资金减少
			// 资金总和
			selfAsset.setAccountID(tob.getHoldId());
			selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
			selfAsset.setQuantifyType(BusinessTypeEnum.POSITION.toString());
			selfAsset.setAmount(tob.getLeftQuantity()); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAsset.setTxID(tob.getTxID());
			selfAsset.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAsset);
			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			tob.getNonquantifiableInfo().put("txID", tob.getTxID());
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

}
