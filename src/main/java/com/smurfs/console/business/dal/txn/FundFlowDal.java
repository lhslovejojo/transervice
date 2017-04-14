package com.smurfs.console.business.dal.txn;

import java.math.BigDecimal;
import java.util.Date;
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
import com.blockchain.service.query.TradeRequest;
import com.smurfs.console.business.domain.txn.FundFlow;
import com.smurfs.console.business.jbcc.JbccClientUtil;
import com.smurfs.console.business.jbcc.SplitTxUtils;
import com.smurfs.console.business.jbcc.TBCCustomizeOriginalBean;
import com.smurfs.console.business.jbcc.TDBCQueryBean4OriginalTx;
import com.smurfs.console.business.jbcc.TDBCQueryBean4OriginalTxBySingleCondition;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ErrorConstants;
import com.smurfs.console.constants.EventTypeEnum;
import com.smurfs.console.constants.JbccConstant;
import com.smurfs.console.frame.exception.JbccQueryException;
import com.smurfs.console.frame.exception.JbccSyncException;
import com.smurfs.console.util.BeanCopyUtil;
import com.smurfs.console.util.DateUtil;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.TDBCTxBean;
import com.tiandetech.jbcc.sll.thrift.JBCCResult;

@Service("fundFlowDal")
public class FundFlowDal {
	public static Logger log = LoggerFactory.getLogger(FundFlowDal.class);
	@Autowired
	JbccClientUtil jbccClientUtil;

	// // 测试调用充值
	// public static void main(String[] args) {
	// // 充值
	// // FundFlow fundFlow = new FundFlow();
	// // fundFlow.setOptType(1);
	// // fundFlow.setSerialNo("018");// 清算中心流水号（防重用）
	// // fundFlow.setTradeSerialNo("011");// 交易所流水
	// // fundFlow.setOrderId("01");// 订单号
	// // fundFlow.setSystemCode("1000001");// 发起方编号，本接口写交易所编码
	// // fundFlow.setBusiDate("20170310");// 业务发生日期(YYYYMMDD)
	// // fundFlow.setBusiTime("20170310111111");// 业务发生时间
	// // // fundFlow.setProductType("00");// 产品分类
	// // // fundFlow.setProductCode("TON");// 产品编码
	// // fundFlow.setBusiType("FUNDFLOW");// 业务类型 
	// // fundFlow.setMemCode("0008");// 会员编码
	// // fundFlow.setFundAccountClear("11");// 资金账户
	// // fundFlow.setMoneyType("CNY");// 币种编码
	// // fundFlow.setOrderAmt(1000d);// 订单总金额
	// // fundFlow.setBankProCode("BANKPROCODE");// 银行产品代码
	// // fundFlow.setAccountName("BANK-ACCOUNT");// 银行账户名 
	// // fundFlow.setBankAccount("6290083211232323");// 银行帐号
	// // fundFlow.setBankNO("ICBC");// 银行代码
	// // fundFlow.setMemberMainType("1");// 会员主体类型
	// // fundFlow.setFullName("测试会员");// 会员全称
	// // fundFlow.setIdKind("1");// 证件类型
	// // fundFlow.setIdNo("32032199212323232222");// 证件号码
	// // FundFlowDal fundFlowDal = new FundFlowDal();
	// // fundFlowDal.createFundFlow(fundFlow);
	// cj();
	//
	// }

	public static void cj() {
		// 充值
		FundFlow fundFlow = new FundFlow();
		fundFlow.setOptType(2);
		fundFlow.setSerialNo("09");// 清算中心流水号（防重用）
		fundFlow.setTradeSerialNo("09");// 交易所流水
		fundFlow.setOrderId("09");// 订单号
		fundFlow.setSystemCode("1000001");// 发起方编号，本接口写交易所编码
		fundFlow.setBusiDate("20170310");// 业务发生日期(YYYYMMDD)
		fundFlow.setBusiTime("20170310111111");// 业务发生时间
		// fundFlow.setProductType("00");// 产品分类
		// fundFlow.setProductCode("TONG");// 产品编码
		fundFlow.setOrderAmt(new BigDecimal(10));
		fundFlow.setBusiType("FUNDTOKEOUT");// 业务类型 
		fundFlow.setMemCode("0008");// 会员编码
		// fundFlow.setFundAccountClear("11");// 资金账户
		// fundFlow.setMoneyType("CNY");// 币种编码
		// fundFlow.setOrderAmt(10000d);// 订单总金额
		fundFlow.setBankProCode("BANKPROCODE");// 银行产品代码
		fundFlow.setAccountName("BANK-ACCOUNT");// 银行账户名 
		fundFlow.setBankAccount("6290083211232323");// 银行帐号
		fundFlow.setBankNO("ICBC");// 银行代码
		fundFlow.setMemberMainType("1");// 会员主体类型
		fundFlow.setFullName("测试会员");// 会员全称
		fundFlow.setIdKind("1");// 证件类型
		fundFlow.setIdNo("32032199212323232222");// 证件号码
		FundFlowDal fundFlowDal = new FundFlowDal();
		fundFlowDal.createFundFlow(fundFlow);
	}

	public FundFlow getFundFlowById(long id) {
		return null;
	}

	/**
	 * 出入金操作
	 * 
	 * @param flow
	 * @return
	 */
	public String createFundFlow(FundFlow flow) {
		flow.setEventType(EventTypeEnum.FUND_IN_OUT.toString());
		TBCCustomizeOriginalBean tbcBean = new TBCCustomizeOriginalBean();
		tbcBean.setTxID("FUNDFLOW_" + flow.getSerialNo());
		flow.setEventId(tbcBean.getTxID());
		String accountIdFiled="memCode";
		if (StringUtils.isNotBlank(flow.getMemCode())) {
			tbcBean.setMemCode(flow.getMemCode());
		} else {
			tbcBean.setMemCode(flow.getFundAccountClear());
			accountIdFiled="fundAccountClear";
		}
		/** 1-入金,2-出金,3-冻结 */
		if (flow.getOptType() == 1) {
			tbcBean.setDealTotalPrice(flow.getOrderAmt());
		} else {
			tbcBean.setDealTotalPrice(new BigDecimal(-1).multiply(flow.getOrderAmt()));
			tbcBean.setOpenPoundage(flow.getFeeAmt()); // 出金手续费
		}
		tbcBean.setTxTimestamp(flow.getBusiTime());
		tbcBean.setNonquantifiableInfo(BeanCopyUtil.toMap(flow, Constants.ignorePropertys,accountIdFiled));
		ABCAtomicBean abcAtomicTx = SplitTxUtils.splitTxFromTBCOriginalToABCAtomicForFundFlow(tbcBean);
		TDBCTxBean tdbcTxBean = new TDBCTxBean();
		tdbcTxBean.setTxId(tbcBean.getTxID()); // 必须的
		tdbcTxBean.setTbcOriginalTx(JSON.toJSONString(tbcBean, SerializerFeature.DisableCircularReferenceDetect)); // 原始交易
		tdbcTxBean.setAbcAtomicTx(abcAtomicTx); // 原子交易
		try {
			Long currentTime = System.currentTimeMillis();
			List<JBCCResult> myresult = jbccClientUtil.getJbccClient().finsertToBC(tdbcTxBean, "TDBC");
			log.info("send tdbcTxBean:"+JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect)+" myresult:"+myresult);
			System.out.println("耗时:" + (System.currentTimeMillis() - currentTime));
			if (myresult == null || myresult.size() < 1 || (myresult.get(0).getStatus()!= 0 && !myresult.get(0).getMessage().contains("already exists"))) {
				log.error("send flow serialNo:" + flow.getSerialNo() + " JBCCResult error" + myresult.toString());
				throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg);
			}
			return myresult.get(0).getMessage();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("send flow serialNo:" + flow.getSerialNo() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}
	}

	public String query(TradeRequest request) {
		TDBCQueryBean4OriginalTxBySingleCondition tqb = new TDBCQueryBean4OriginalTxBySingleCondition();
		tqb.setIactTableName(JbccConstant.indexAccount);
		tqb.setTbcTableName(JbccConstant.tbc);
		tqb.setAbcTableName(JbccConstant.abc);
		tqb.setAccountID(request.getMemCode());
		tqb.setStartTS(DateUtil.getBeforeNowMonth(-3).getTime());
		tqb.setEndTS(new Date().getTime());
		tqb.setQualifier("eventType");
		tqb.setValue(request.getBusiType());
		tqb.setPageIndex(request.getPageNum());
		tqb.setPageSize(request.getPageSize());
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
			JBCCResult myresult = jbccClientUtil.getJbccClient()
					.fastSelectFromBC(JbccConstant.queryTypeCustomize4GetOriginalTx, queryFilter);
			log.info("query queryFilter:"+queryFilter+" myresult:"+myresult);
			return myresult.getMessage();
		} catch (TException e) {
			jbccClientUtil.reConnection();
			log.error("query flow request: " + request.toString() + ErrorConstants.user_error_msg, e);
			throw new JbccQueryException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}
	}

}
