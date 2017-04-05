package com.smurfs.console.constants;

import java.util.Arrays;
import java.util.List;

/**
 * name：Constants.java<br>
 * describe：the util constants<br>
 * modify：2016-8-18 14:40:38
 * 
 * @since 2016-8-18
 * @author qibo
 */
public interface Constants {
	/** userDal bean name */
	String BEAN_USER_DAL = "userDal";

	/** menu folder type */
	String MU_TP_FD = "folder";
	/** menu leaf item type */
	String MU_TP_LF = "item";

	int PRI_RES_TP_MU = 1;
	int PRI_RES_TP_UR = 2;

	// ~~~~~~~~~~~basic
	/** default departmenr code */
	String DEF_DEPR_ID = "def";

	String RES_TYPE = "restype_";
	/**
	 * 量化属性key
	 */
	public final static List<String> ignorePropertys = Arrays.asList("FUND_ALL_RMB", "FUND_ABLE_RMB",
			"FUND_TAKE_RMB");
	/**
	 * 滞纳金属性Key
	 */
	public final static String DELAY_ACCOUNT_AMOUNT = "DELAY_ACCOUNT_AMOUNT";
	/**
	 * 履约准备金属性Key
	 */
	public final static String DEPOSIT_ACCOUNT_AMOUNT = "DEPOSIT_ACCOUNT_AMOUNT";

	public static final String FUND_ALL_RMB = "FUND_ALL_RMB"; // 人民币资金总和
	public static final String FUND_ABLE_RMB = "FUND_ABLE_RMB"; // 人民币可用资金
	public static final String FUND_TAKE_RMB = "FUND_TAKE_RMB"; // 人民币可提资金
	public static final String ASSET_ACCOUNT_PREFIX="ASSET_" ;//资产账户前缀
	public static final String ASSET_HOLD_AMOUNT_SUFFIX="_HOLDALLCOST" ;//资产持仓成本合计
	public static final String ASSET_SETTLE_PRICE_PREFIX="ASSET_SETTLE_PRICE_" ;//资产账户前缀

}
