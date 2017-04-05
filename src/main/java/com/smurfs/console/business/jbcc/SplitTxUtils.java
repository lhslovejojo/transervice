package com.smurfs.console.business.jbcc;

import java.util.ArrayList;

import com.smurfs.console.constants.BusinessTypeEnum;
import com.smurfs.console.constants.Constants;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.ABCAtomicQuantifyBean;

/**
 * 分解原始分离器，用于将原始交易分离为原子单方交易 涉及到三方：买方、卖方、平台
 */
public class SplitTxUtils {

	

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
			// 1.购买方,资金交易
			// 资金总和
			selfAllFund.setAccountID(tob.getMemCode());
			selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
			selfAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			selfAllFund.setAmount(-1 * (tob.getDealTotalPrice() + tob.getOpenPoundage())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAllFund.setOrigialTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAllFund);
			// 可用资金
			selfAbleFund.setAccountID(tob.getMemCode());
			selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			selfAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			selfAbleFund.setAmount(-1 * (tob.getDealTotalPrice() + tob.getOpenPoundage())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAbleFund.setOrigialTxID(tob.getTxID());
			selfAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAbleFund);
			// 可提资金
			selfTakeFund.setAccountID(tob.getMemCode());
			selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			selfTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			selfTakeFund.setAmount(-1 * (tob.getDealTotalPrice() + tob.getOpenPoundage())); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfTakeFund.setOrigialTxID(tob.getTxID());
			selfTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfTakeFund);

			// 2.购买方,单方资产交易
			selfAsset.setAccountID(tob.getMemCode());
			selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
			selfAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			selfAsset.setAmount(tob.getOrderQuantity()); // 购买商品方，资产为正数
			selfAsset.setOrigialTxID(tob.getTxID());
			selfAsset.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAsset);
			//购买方持仓成本 
			selfAssetHold.setAccountID(tob.getMemCode());
			selfAssetHold.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode()+Constants.ASSET_HOLD_AMOUNT_SUFFIX);
			selfAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			selfAssetHold.setAmount(tob.getDealTotalPrice()); // 购买商品方，资产为正数
			selfAssetHold.setOrigialTxID(tob.getTxID());
			selfAssetHold.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAssetHold);

			// 3.卖方,单方资金交易
			peerAllFund.setAccountID(tob.getOppMemCode());
			peerAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
			peerAllFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			peerAllFund.setAmount(tob.getDealTotalPrice() - tob.getOppPoundage()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAllFund.setOrigialTxID(tob.getTxID());
			peerAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAllFund);

			peerAbleFund.setAccountID(tob.getOppMemCode());
			peerAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			peerAbleFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			peerAbleFund.setAmount(tob.getDealTotalPrice() - tob.getOppPoundage()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAbleFund.setOrigialTxID(tob.getTxID());
			peerAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAbleFund);

			peerTakeFund.setAccountID(tob.getOppMemCode());
			peerTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			peerTakeFund.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			peerTakeFund.setAmount(tob.getDealTotalPrice() - tob.getOppPoundage()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerTakeFund.setOrigialTxID(tob.getTxID());
			peerTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerTakeFund);

			// 4.卖方,单方资产交易
			peerAsset.setAccountID(tob.getOppMemCode());
			peerAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
			peerAsset.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			peerAsset.setAmount((-1) * tob.getOrderQuantity()); // 出售商品方，资产为负数
			peerAsset.setOrigialTxID(tob.getTxID());
			peerAsset.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAsset);
			
			peerAssetHold.setAccountID(tob.getOppMemCode());
			peerAssetHold.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode()+Constants.ASSET_HOLD_AMOUNT_SUFFIX);
			peerAssetHold.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			peerAssetHold.setAmount((-1) * tob.getDealTotalPrice()); // 出售商品方，资产为负数
			peerAssetHold.setOrigialTxID(tob.getTxID());
			peerAssetHold.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAssetHold);
			
			// 5.缴纳平台费用，是由AB双方缴纳的平台费用之和
			platformFee.setAccountID(tob.getTxFeePlatformAccountID());
			platformFee.setQuantifyName(Constants.FUND_ALL_RMB);
			platformFee.setQuantifyType(BusinessTypeEnum.TRADE.toString());
			platformFee.setAmount(tob.getOpenPoundage() + tob.getOppPoundage()); // 交易发起方，AB双方缴纳平台费用的和
			platformFee.setOrigialTxID(tob.getTxID());
			platformFee.setDescription(tob.getTxDescription());
			quantifyInfo.add(platformFee);

			aab.setQuantifyInfo(quantifyInfo); // 分解原始交易为原子单方交易，涉及到三方：买方、卖方、平台
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
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
			selfAllFund.setAmount(0d); // 资金账户初始值
			selfAllFund.setOrigialTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAllFund);
			// 可用资金
			selfAbleFund.setAccountID(tob.getMemCode());
			selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			selfAbleFund.setQuantifyType(BusinessTypeEnum.ACCOUNT.toString());
			selfAbleFund.setAmount(0d);
			selfAbleFund.setOrigialTxID(tob.getTxID());
			selfAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAbleFund);
			// 可提资金
			selfTakeFund.setAccountID(tob.getMemCode());
			selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			selfTakeFund.setQuantifyType(BusinessTypeEnum.ACCOUNT.toString());
			selfTakeFund.setAmount(0d);
			selfTakeFund.setOrigialTxID(tob.getTxID());
			selfTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfTakeFund);

			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
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
			if (tob.getDealTotalPrice() != null && tob.getDealTotalPrice() < 0)
				busType = BusinessTypeEnum.FUND_OUT.toString();
			// 1.充值提现 ，单方资金交易
			if (tob.getDealTotalPrice() != null) {
				// 资金总和
				selfAllFund.setAccountID(tob.getMemCode());
				selfAllFund.setQuantifyName(Constants.FUND_ALL_RMB); // 资金账户
				selfAllFund.setQuantifyType(busType);
				selfAllFund.setAmount(tob.getDealTotalPrice()); // 资金账户初始值
				selfAllFund.setOrigialTxID(tob.getTxID());
				selfAllFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAllFund);
				// 可用资金
				selfAbleFund.setAccountID(tob.getMemCode());
				selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
				selfAbleFund.setQuantifyType(busType);
				selfAbleFund.setAmount(tob.getDealTotalPrice());
				selfAbleFund.setOrigialTxID(tob.getTxID());
				selfAbleFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAbleFund);
				// 可提资金
				selfTakeFund.setAccountID(tob.getMemCode());
				selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
				selfTakeFund.setQuantifyType(busType);
				selfTakeFund.setAmount(tob.getDealTotalPrice());
				selfTakeFund.setOrigialTxID(tob.getTxID());
				selfTakeFund.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfTakeFund);
			}
			// 2.如果有直接充资产
			if (tob.getProductCode() != null && tob.getOrderQuantity() != null) {
				selfAsset.setAccountID(tob.getMemCode());
				selfAsset.setQuantifyName(Constants.ASSET_ACCOUNT_PREFIX + tob.getProductCode());
				selfAsset.setAmount(tob.getOrderQuantity());
				if (tob.getOrderQuantity() > 0) {
					selfAsset.setQuantifyType(BusinessTypeEnum.FUND_IN.toString()); //充资产
				} else {
					selfAsset.setQuantifyType(BusinessTypeEnum.FUND_OUT.toString()); //提出资产
				}
				selfAsset.setOrigialTxID(tob.getTxID());
				selfAsset.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
			}
			// 3.如果平台费不为空，要缴纳平台费用
			if (tob.getOpenPoundage() != null) {
				platformFee.setAccountID(tob.getTxFeePlatformAccountID());
				platformFee.setQuantifyName(Constants.FUND_ALL_RMB);
				platformFee.setAmount(tob.getOpenPoundage()); // 提现的时候有平台手续费
				platformFee.setQuantifyType(busType);
				platformFee.setOrigialTxID(tob.getTxID());
				platformFee.setDescription(tob.getTxDescription());
				quantifyInfo.add(selfAsset);
			}
			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
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
			selfAllFund.setAmount(-1 * tob.getDealTotalPrice()); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAllFund.setOrigialTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAllFund);
			// 可用资金
			selfAbleFund.setAccountID(tob.getMemCode());
			selfAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			selfAbleFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			selfAbleFund.setAmount(-1 * tob.getDealTotalPrice()); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfAbleFund.setOrigialTxID(tob.getTxID());
			selfAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAbleFund);
			// 可提资金
			selfTakeFund.setAccountID(tob.getMemCode());
			selfTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			selfTakeFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			selfTakeFund.setAmount(-1 * tob.getDealTotalPrice()); // 购买商品方，资金为正数，同时需要缴纳平台费用
			selfTakeFund.setOrigialTxID(tob.getTxID());
			selfTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfTakeFund);

			// 2.接收方资金增加
			// 资金总和
			peerAllFund.setAccountID(tob.getOppMemCode());
			peerAllFund.setQuantifyName(Constants.FUND_ALL_RMB);
			peerAllFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerAllFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAllFund.setOrigialTxID(tob.getTxID());
			peerAllFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAllFund);
			// 可用资金
			peerAbleFund.setAccountID(tob.getOppMemCode());
			peerAbleFund.setQuantifyName(Constants.FUND_ABLE_RMB);
			peerAbleFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerAbleFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerAbleFund.setOrigialTxID(tob.getTxID());
			peerAbleFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerAbleFund);
			// 可提资金
			peerTakeFund.setAccountID(tob.getOppMemCode());
			peerTakeFund.setQuantifyName(Constants.FUND_TAKE_RMB);
			peerTakeFund.setQuantifyType(BusinessTypeEnum.TRANSFER.toString());
			peerTakeFund.setAmount(tob.getDealTotalPrice()); // 出售商品方，资金为负数，同时需要缴纳平台费用
			peerTakeFund.setOrigialTxID(tob.getTxID());
			peerTakeFund.setDescription(tob.getTxDescription());
			quantifyInfo.add(peerTakeFund);

			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
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
			selfAsset.setOrigialTxID(tob.getTxID());
			selfAsset.setDescription(tob.getTxDescription());
			quantifyInfo.add(selfAsset);
			aab.setQuantifyInfo(quantifyInfo);
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

	/**
	 * 用于交易金额和可用金额 变动不同的时候 ，暂没有用
	 * 
	 * @param tob
	 * @return
	 */
	public static ABCAtomicBean splitTxFromTBCOriginalToABCAtomic(TBCCustomizeOriginalBean tob) {
		ABCAtomicBean aab = new ABCAtomicBean();

		// 分解交易双方和中介方，共3方交易,拆解成6笔单方交易
		ArrayList<ABCAtomicQuantifyBean> quantifyInfo = new ArrayList<ABCAtomicQuantifyBean>();
		ABCAtomicQuantifyBean selfFund = new ABCAtomicQuantifyBean(); // 交易发起方
																		// ：可用资金
		ABCAtomicQuantifyBean selfLockFund = new ABCAtomicQuantifyBean(); // 交易发起方
																			// ：
																			// 锁定资金
		ABCAtomicQuantifyBean selfAllFund = new ABCAtomicQuantifyBean(); // 交易发起方
																			// ：
																			// 全部资金

		ABCAtomicQuantifyBean selfAsset = new ABCAtomicQuantifyBean(); // 交易发起方
		ABCAtomicQuantifyBean peerFund = new ABCAtomicQuantifyBean(); // 交易参与方：可用资金
		ABCAtomicQuantifyBean peerLockFund = new ABCAtomicQuantifyBean(); // 交易参与方：
																			// 锁定资金
		ABCAtomicQuantifyBean peerAllFund = new ABCAtomicQuantifyBean(); // 交易参与方：
																			// 全部资金

		ABCAtomicQuantifyBean peerAsset = new ABCAtomicQuantifyBean(); // 交易参与方
		ABCAtomicQuantifyBean platformFee = new ABCAtomicQuantifyBean(); // 交易平台方，收取交易费用

		try {
			// 1.交易发起方,单方资金交易
			selfFund.setAccountID(tob.getMemCode());
			selfFund.setQuantifyName("Constants.FUND_RMB"); // 可用资金
			selfFund.setAmount(tob.getTxFundAmount() - tob.getOpenPoundage()); // 购买商品方，资金为负数，同时需要缴纳平台费用
			selfFund.setOrigialTxID(tob.getTxID());
			selfFund.setDescription(tob.getTxDescription());

			selfLockFund.setAccountID(tob.getMemCode());
			selfLockFund.setQuantifyName("Constants.FUND_LOCK_RMB"); // 锁定资金
			selfLockFund.setAmount(tob.getTxFundLockAmount() - tob.getOpenPoundage()); // 购买商品方，资金为负数，同时需要缴纳平台费用
			selfLockFund.setOrigialTxID(tob.getTxID());
			selfLockFund.setDescription(tob.getTxDescription());

			selfAllFund.setAccountID(tob.getMemCode());
			selfAllFund.setQuantifyName("Constants.FUND_ALL_RMB"); // 全部资金
			selfAllFund.setAmount(tob.getDealTotalPrice() - tob.getOpenPoundage()); // 购买商品方，资金为负数，同时需要缴纳平台费用
			selfAllFund.setOrigialTxID(tob.getTxID());
			selfAllFund.setDescription(tob.getTxDescription());

			// 2.交易发起方,单方资产交易
			selfAsset.setAccountID(tob.getMemCode());
			selfAsset.setQuantifyName(tob.getProductCode()); // 资产商品编码
			selfAsset.setAmount(tob.getOrderQuantity()); // 购买商品方，资产为正数
			selfAsset.setOrigialTxID(tob.getTxID());
			selfAsset.setDescription(tob.getTxDescription());

			// 3.交易参与方,单方资金交易
			peerFund.setAccountID(tob.getOppMemCode());
			peerFund.setQuantifyName("Constants.FUND_RMB");
			peerFund.setAmount((-1) * tob.getTxFundAmount() - tob.getOppPoundage()); // 出售商品方，资金为正数，同时需要缴纳平台费用
			peerFund.setOrigialTxID(tob.getTxID());
			peerFund.setDescription(tob.getTxDescription());

			peerLockFund.setAccountID(tob.getOppMemCode());
			peerLockFund.setQuantifyName("Constants.FUND_LOCK_RMB");
			peerLockFund.setAmount((-1) * tob.getTxFundLockAmount() - tob.getOppPoundage()); // 出售商品方，资金为正数，同时需要缴纳平台费用
			peerLockFund.setOrigialTxID(tob.getTxID());
			peerLockFund.setDescription(tob.getTxDescription());

			peerAllFund.setAccountID(tob.getOppMemCode());
			peerAllFund.setQuantifyName("Constants.FUND_ALL_RMB");
			peerAllFund.setAmount((-1) * tob.getDealTotalPrice() - tob.getOppPoundage()); // 出售商品方，资金为正数，同时需要缴纳平台费用
			peerAllFund.setOrigialTxID(tob.getTxID());
			peerAllFund.setDescription(tob.getTxDescription());

			// 4.交易参与方,单方资产交易
			peerAsset.setAccountID(tob.getOppMemCode());
			peerAsset.setQuantifyName(tob.getProductCode());
			peerAsset.setAmount((-1) * tob.getOrderQuantity()); // 出售商品方，资产为负数
			peerAsset.setOrigialTxID(tob.getTxID());
			peerAsset.setDescription(tob.getTxDescription());

			// 5.缴纳平台费用，是由AB双方缴纳的平台费用之和
			platformFee.setAccountID(tob.getTxFeePlatformAccountID());
			platformFee.setQuantifyName("Constants.FUND_RMB");
			platformFee.setAmount(tob.getOpenPoundage() + tob.getOppPoundage()); // 交易发起方，AB双方缴纳平台费用的和
			platformFee.setOrigialTxID(tob.getTxID());
			platformFee.setDescription(tob.getTxDescription());

			quantifyInfo.add(selfFund);
			quantifyInfo.add(selfLockFund);
			quantifyInfo.add(selfAllFund);

			quantifyInfo.add(selfAsset);
			quantifyInfo.add(peerFund);
			quantifyInfo.add(peerLockFund);
			quantifyInfo.add(peerAllFund);

			quantifyInfo.add(peerAsset);
			quantifyInfo.add(platformFee);

			aab.setQuantifyInfo(quantifyInfo); // 分解原始交易为原子单方交易，涉及到三方：买方、卖方、平台
		} catch (Exception ef) {
			System.out.println("Please check whether the quantification information is complete?");
		}

		try {
			aab.setNonQuantifiableInfo(tob.getNonquantifiableInfo()); // 保存单方交易里的非量化信息
		} catch (Exception ef) {
			System.out.println("Please check whether the non-quantifiable information is complete?");
		}
		return aab;
	}

}
