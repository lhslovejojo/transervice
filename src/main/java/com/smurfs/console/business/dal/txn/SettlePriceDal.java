package com.smurfs.console.business.dal.txn;

import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smurfs.console.business.domain.txn.ConCludeSheet;
import com.smurfs.console.business.domain.txn.SettlePrice;
import com.smurfs.console.business.jbcc.JbccClientUtil;
import com.smurfs.console.business.jbcc.SplitTxUtils;
import com.smurfs.console.business.jbcc.TBCCustomizeOriginalBean;
import com.smurfs.console.business.jbcc.TDBCQueryBean;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ErrorConstants;
import com.smurfs.console.constants.JbccConstant;
import com.smurfs.console.frame.exception.JbccSyncException;
import com.smurfs.console.util.BeanCopyUtil;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.TDBCTxBean;
import com.tiandetech.jbcc.sll.thrift.JBCCResult;

@Service
public class SettlePriceDal {
	public static Logger log = LoggerFactory.getLogger(SettlePriceDal.class);

	@Autowired
	JbccClientUtil jbccClientUtil;

//	// 测试调用充值
//	public static void main(String[] args) {
//		ConCludeSheet sheet = new ConCludeSheet();
//		sheet.setBusiNo("01");// 业务单号,成交单号deal_id
//		sheet.setExchangeId("1000001");// 交易所代码
//		sheet.setExchangeMarketType("");// 交易所市场编码
//		sheet.setBusiType("");// 交易所市场业务类型
//		sheet.setMemCode("0007");// 会员编码-发起方
//		sheet.setFundAccountClear("123");// 资金账号-发起方
//		sheet.setOpenTradeAccount("456");// 交易账号-发起方
//		sheet.setOppMemCode("0008");// 会员编码-对手方
//		sheet.setOppFundAccount("789");// 资金账号-对手方
//		sheet.setOppTradeAccount("987");// 交易账号-对手方
//		sheet.setMoneyType("CNY");// 币种编码 
//		sheet.setProductCode("TONG");// 商品合约编码
//		sheet.setProductCategoryId("");// 产品类别ID
//		sheet.setTradeType("");// 交易类型
//		sheet.setBusiDate("20170310");// 业务发生日期(YYYYMMDD)
//		sheet.setBusiTime("20170310143543");// 业务发生时间，成交时间 
//		sheet.setTradeDir("1");// 买卖方向(1买2卖)
//		sheet.setDealType("1");// 成交类型(1开仓 2平仓)
//		sheet.setOppDealType("1");// 对手方成交类型
//		sheet.setOrderWay("Z");// 报单方式(1PC客户端  2移动客户端 3浏览器客户端 4电话委托报单 Z其他)
//		sheet.setDepositWay("1");// 仓单/定金方式 (0仓单 1定金)
//		sheet.setOrderPrice(10d);// 成交价格
//		sheet.setHoldPrice(9d);// 持仓价格
//		sheet.setOrderQuantity(50d);// 成交数量
//		sheet.setDealTotalPrice(500d);// 成交总价
//		// sheet.setdepositRate();//定金率
//		// sheet.setdepositRatioType();//定金率是否比率(0-固定 1-比率)
//		// sheet.setdepositType();//定金收取方式(0-开仓价 1-持仓价)
//		// sheet.setdepositBalance();//定金金额
//		sheet.setOpenPoundage(1d);// 发起方手续费
//		sheet.setOppPoundage(1d);// 对手方手续费
//		sheet.setDepotOrderNo("123456");// 建仓单号
//		sheet.setOppDepotOrderNo("7890123");// 对手方建仓单号
//		sheet.setOrderId("hureer");// 报单编号
//		sheet.setOppOrderId("reerhu");// 对手方报单编号
//		sheet.setSettlementDate("20170310");// 结算日期
//		sheet.setRequestDesc("交易铜50手");// 说明，备注
//		ConCludeSheepDal conCludeSheepDal = new ConCludeSheepDal();
//		conCludeSheepDal.createConCludeSheep(sheet);
//	}

	/**
	 * 成交单jbcc
	 * 
	 * @param sheep
	 * @return
	 */
	public String updateSettlePrice(SettlePrice settlePrice) {
		TBCCustomizeOriginalBean tbcBean = new TBCCustomizeOriginalBean();
		tbcBean.setTxID("SETTLEPRICE_" + settlePrice.getInitDate() + settlePrice.getProductCode());
		tbcBean.setMemCode("SETTLEPRICE_" + settlePrice.getProductCode()); // 会员编号
		tbcBean.setProductCode(settlePrice.getProductCode());
		tbcBean.setTxTimestamp(settlePrice.getInitDate());
		tbcBean.setNonquantifiableInfo(BeanCopyUtil.toMap(settlePrice, Constants.ignorePropertys, "productCode"));
		ABCAtomicBean abcAtomicTx = SplitTxUtils.splitTxFromTBCOriginalToABCAtomicForSettlePrice(tbcBean);
		TDBCTxBean tdbcTxBean = new TDBCTxBean();
		tdbcTxBean.setTxId(tbcBean.getTxID()); // 必须的
		tdbcTxBean.setTbcOriginalTx(JSON.toJSONString(tbcBean, SerializerFeature.DisableCircularReferenceDetect)); // 原始交易
		tdbcTxBean.setAbcAtomicTx(abcAtomicTx); // 原子交易
		System.out.println(JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect));
		try {
			List<JBCCResult> myresult = jbccClientUtil.getJbccClient().finsertToBC(tdbcTxBean, "TDBC");
			log.info("send tdbcTxBean:"+JSON.toJSONString(tdbcTxBean, SerializerFeature.DisableCircularReferenceDetect)+" myresult:"+myresult);
			if (myresult == null || myresult.size() < 1 || (myresult.get(0).getStatus()!= 0 && !myresult.get(0).getMessage().contains("already exists"))) {
				log.error(" settlePrice " + settlePrice.toString() + " JBCCResult error" + myresult);
				throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg);
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("settlePrice " + settlePrice.toString() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}
		return null;

	}

	public String getLastSettlePrice(String productCode) {
		TDBCQueryBean tqb = new TDBCQueryBean();
		tqb.setTableName(JbccConstant.indexAccount);
		tqb.setRowId("SETTLEPRICE_" + productCode); // 账户ID
		tqb.setQualifiers("settlePrice");
		String queryFilter = JSON.toJSONString(tqb, SerializerFeature.DisableCircularReferenceDetect);
		try {
		JBCCResult myresult = jbccClientUtil.getJbccClient().fastSelectFromBC(JbccConstant.queryTypeRow, queryFilter);
		if (myresult == null ||myresult.getStatus() != 0) {
			log.error(" getLastSettlePrice productCode " + productCode + " JBCCResult error" + myresult);
			throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg);
		}
		return myresult.getMessage();
		} catch (TException e) {
			jbccClientUtil.reConnection();
			log.error("getLastSettlePrice productCode " + productCode  + ErrorConstants.user_error_msg, e);
			throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}

	}
}
