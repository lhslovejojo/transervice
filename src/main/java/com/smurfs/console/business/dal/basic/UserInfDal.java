package com.smurfs.console.business.dal.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blockchain.service.query.UserRequest;
import com.smurfs.console.business.domain.basic.UserInf;
import com.smurfs.console.business.jbcc.JbccClientUtil;
import com.smurfs.console.business.jbcc.SplitTxUtils;
import com.smurfs.console.business.jbcc.TBCCustomizeOriginalBean;
import com.smurfs.console.business.jbcc.TDBCQueryBean;
import com.smurfs.console.business.jbcc.TDBCScanBean;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ErrorConstants;
import com.smurfs.console.constants.JbccConstant;
import com.smurfs.console.frame.exception.JbccQueryException;
import com.smurfs.console.frame.exception.JbccSyncException;
import com.smurfs.console.util.BeanCopyUtil;
import com.smurfs.console.util.StringUtil;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.TDBCTxBean;
import com.tiandetech.jbcc.sll.thrift.JBCCResult;

@Service("userInfDal")
public class UserInfDal {
	public static Logger log = LoggerFactory.getLogger(UserInfDal.class);

	@Autowired
	JbccClientUtil jbccClientUtil;
	//
	// // 测试调用
	// public static void main(String[] args) {
	// // UserInf userInf = new UserInf();
	// // userInf.setExchangeId("1000001");// 交易所代码，主键，不能修改
	// // userInf.setSerialNo("08"); // 交易流水
	// // userInf.setMemCode("0008");// 会员编号，主键，不能修改
	// // userInf.setExchangeMemberStatus("1");// 会员状态
	// // userInf.setFullName("会员名称0008"); // 会员全称
	// // userInf.setShortName("会员简称0008");// 会员简称
	// // userInf.setEnFullName("英文全称0008");//
	// // userInf.setEnShortName("英文简称0008");//
	// // userInf.setGender("1");//
	// // userInf.setNationality("CHN");// 国籍
	// // userInf.setIdKind("1");// 证件类型
	// // userInf.setIdNo("320321199912311238");// 证件号码
	// // userInf.setTel("15801402008");// 联系电话
	// // userInf.setUpMemCode("test");// 关联会员账号
	// UserInfDal userInfDal = new UserInfDal();
	// // userInfDal.syncAccount(userInf);
	// //
	// userInfDal.updateAccountBalance("account7002",Constants.DELAY_ACCOUNT_AMOUNT,94d);
	// // userInfDal.updateSettlePrice("account7002","TONG",94d);
	// userInfDal.queryByAccountId("account7002");
	//
	// }

	/**
	 * 创建注册用户
	 * 
	 * @param userInf
	 * @return 返回修改后的凭证字符串及 trxId;
	 */
	public String syncAccount(UserInf userInf) {
		TBCCustomizeOriginalBean tbcBean = new TBCCustomizeOriginalBean();
		tbcBean.setTxID("USER_" + userInf.getSerialNo());
		tbcBean.setMemCode(userInf.getMemCode());
		tbcBean.setTxTimestamp(userInf.getBusiDate());
		tbcBean.setNonquantifiableInfo(BeanCopyUtil.toMap(userInf, Constants.ignorePropertys));
		ABCAtomicBean abcAtomicTx = SplitTxUtils.splitTxFromTBCOriginalToABCAtomicForUser(tbcBean);
		TDBCTxBean tdbcTxBean = new TDBCTxBean();
		tdbcTxBean.setTxId(tbcBean.getTxID()); // 必须的
		tdbcTxBean.setTbcOriginalTx(JSON.toJSONString(tbcBean, SerializerFeature.DisableCircularReferenceDetect)); // 原始交易
		tdbcTxBean.setAbcAtomicTx(abcAtomicTx); // 原子交易
		System.out.println(JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect));
		try {
			List<JBCCResult> myresult = jbccClientUtil.getJbccClient().finsertToBC(tdbcTxBean, "TDBC");
			log.info(
					"send tdbcTxBean:" + JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect)
							+ " myresult:" + myresult);
			if (myresult == null || myresult.size() < 1
					|| (myresult.get(0).getStatus() != 0 && !myresult.get(0).getMessage().contains("already exists"))) {
				log.error(
						"sende userInf serialNo:" + userInf.getSerialNo() + " JBCCResult error" + myresult.toString());
				throw new JbccSyncException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			return tbcBean.getTxID();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("sende userInf serialNo:" + userInf.getSerialNo() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}

	}

	/**
	 * 修改账户余额，只能是非量化属性
	 * 
	 * @param memCode
	 *            会员编码
	 * @param accountType
	 *            账户类型 资金总和，可用余额，等
	 * @param balance
	 *            金额
	 * @return
	 */
	public boolean updateAccountByMap(String memCode, HashMap<String, String> map) {
		TBCCustomizeOriginalBean tbcBean = new TBCCustomizeOriginalBean();
		tbcBean.setTxID("USER_" + System.currentTimeMillis());
		tbcBean.setMemCode(memCode);
		map.put("accountID", memCode);
		tbcBean.setNonquantifiableInfo(map);
		ABCAtomicBean abcAtomicTx = new ABCAtomicBean();
		abcAtomicTx.setNonQuantifiableInfo(tbcBean.getNonquantifiableInfo());
		TDBCTxBean tdbcTxBean = new TDBCTxBean();
		tdbcTxBean.setTxId(tbcBean.getTxID()); // 必须的
		tdbcTxBean.setTbcOriginalTx(JSON.toJSONString(tbcBean, SerializerFeature.DisableCircularReferenceDetect)); // 原始交易
		tdbcTxBean.setAbcAtomicTx(abcAtomicTx); // 原子交易
		try {
			List<JBCCResult> myresult = jbccClientUtil.getJbccClient().finsertToBC(tdbcTxBean, "TDBC");
			log.info(
					"send tdbcTxBean:" + JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect)
							+ " myresult:" + myresult);
			if (myresult == null || myresult.size() < 1
					|| (myresult.get(0).getStatus() != 0 && !myresult.get(0).getMessage().contains("already exists"))) {
				log.error("updateAccountByMap  memCode " + memCode + "map " + map + " JBCCResult error"
						+ myresult.toString());
				throw new JbccSyncException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			return true;
		} catch (TException e) {
			log.error("updateAccountBalance  memCode " + memCode + "map" + map + " JBCCResult error", e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}
	}

	/**
	 * 根据账号，查询账号信息
	 * 
	 * @param accountId
	 * @return
	 */
	public String queryByAccountId(String accountId) {
		TDBCQueryBean tqb = new TDBCQueryBean();
		tqb.setTableName(JbccConstant.indexAccount);
		tqb.setRowId(accountId); // 账户ID
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC(JbccConstant.queryTypeRow,
					queryFilter);
			log.info("query queryFilter:" + queryFilter + " myresult:" + myresult);
			if (myresult == null || myresult.getStatus() != 0) {
				log.error("queryByAccountId  memCode " + accountId + " JBCCResult error" + myresult.toString());
				throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			return myresult.getMessage();
		} catch (TException e) {
			jbccClientUtil.reConnection();
			log.error("query userInf accountId:" + accountId + ErrorConstants.user_error_msg, e);
			throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}

	}

	/**
	 * 根据商编查询资金账户的变动历史 目前是资金合计的变动历史
	 * 
	 * @param searchParam
	 * @return
	 */
	public String queryAccountBalHis(String memCode) {
		TDBCQueryBean tqb = new TDBCQueryBean();
		tqb.setTableName(JbccConstant.abc);
		tqb.setRowId(memCode); // 账户ID
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC("TDBC", queryFilter); // .fselectFromBC(tdbcTxBean,
			log.info("query queryFilter:" + queryFilter + " myresult:" + myresult);
			if (myresult == null || myresult.getStatus() != 0) {
				log.error("queryAccountBalHis  memCode " + memCode + " JBCCResult error" + myresult.toString());
				throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			return myresult.getMessage();
		} catch (TException e) {
			log.error("queryAccountBalHis  memCode:" + memCode + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}

	}

	/**
	 * 查询账户
	 * 
	 * @param searchParam
	 * @return
	 */
	public String queryByKey(UserRequest request) {
		HashMap<String, String> searchParam = new HashMap<String, String>();
		if (StringUtil.isNotBlank(request.getMemCode()))
			searchParam.put("accountID", request.getMemCode());
		if (StringUtil.isNotBlank(request.getFundAccountClear()))
			searchParam.put("fundAccountClear", request.getFundAccountClear());
		if (StringUtil.isNotBlank(request.getExchangeId()))
			searchParam.put("exchangeId", request.getExchangeId());
		if (StringUtil.isNotBlank(request.getFullName()))
			searchParam.put("fullName", request.getFullName());
		TDBCScanBean tsb = new TDBCScanBean();
		tsb.setTableName(JbccConstant.indexAccount);
		tsb.setQualifierMap(searchParam);
		tsb.setPageIndex(request.getPageNum());
		tsb.setPageSize(request.getPageSize());
		String queryFilter = JSON.toJSONString(tsb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC(JbccConstant.queryTypeScan,
					queryFilter);
			log.info("query queryFilter:" + queryFilter + " myresult:" + myresult);
			if (myresult == null || myresult.getStatus() != 0) {
				log.error("queryByKey  queryFilter " + queryFilter + " JBCCResult error" + myresult.toString());
				throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			return myresult.getMessage();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("query userInf accountId:" + searchParam.toString() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}

	}

	/**
	 * 查询持仓信息
	 * 
	 * @param exchangeId
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	public String queryAsset(String exchangeId, String memCode, String productCode) {
		TDBCQueryBean tqb = new TDBCQueryBean();
		tqb.setTableName(JbccConstant.indexAccount);
		tqb.setRowId(memCode); // 账户ID
		if (StringUtils.isNotBlank(productCode)) {
			tqb.setPreQulifierFilter(Constants.ASSET_ACCOUNT_PREFIX + productCode);
		} else {
			tqb.setPreQulifierFilter(Constants.ASSET_ACCOUNT_PREFIX);
		}
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC(JbccConstant.queryTypeRow,
					queryFilter);
			log.info("query queryFilter:" + queryFilter + " myresult:" + myresult);
			if (myresult == null || myresult.getStatus() != 0) {
				log.error("queryAsset  queryFilter " + queryFilter + " JBCCResult error" + myresult.toString());
				throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			System.out.println(myresult);
			return myresult.getMessage();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("queryAsset memCode:" + memCode + "productCode:" + productCode + ErrorConstants.user_error_msg,
					e);
			jbccClientUtil.reConnection();
			throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}
	}

	/**
	 * 查询持仓历史信息
	 * 
	 * @param exchangeId
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	public String queryAssetHis(String exchangeId, String memCode, String productCode) {
		TDBCQueryBean tqb = new TDBCQueryBean();
		tqb.setTableName(JbccConstant.indexAccount);
		tqb.setRowId(memCode); // 账户ID
		tqb.setVersion(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(productCode)) {
			tqb.setPreQulifierFilter(Constants.ASSET_ACCOUNT_PREFIX + productCode);
		} else {
			tqb.setPreQulifierFilter(Constants.ASSET_ACCOUNT_PREFIX);
		}
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC(JbccConstant.queryTypeRow,
					queryFilter);
			log.info("query queryFilter:" + queryFilter + " myresult:" + myresult);
			if (myresult == null || myresult.getStatus() != 0) {
				log.error("queryAsset  queryFilter " + queryFilter + " JBCCResult error" + myresult.toString());
				throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg);
			}
			System.out.println(myresult);
			return myresult.getMessage();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("queryAsset memCode:" + memCode + "productCode:" + productCode + ErrorConstants.user_error_msg,
					e);
			jbccClientUtil.reConnection();
			throw new JbccQueryException(ErrorConstants.user_error_code, ErrorConstants.user_error_msg, e);
		}
	}

	public String frozen(String memCode, String exchangeId) {
		return null;
	}

	public String unfrozen(String memCode, String exchangeId) {
		return null;
	}

	public boolean closeAccount(String memCode, String exchangeId) {
		return true;
	}

}
