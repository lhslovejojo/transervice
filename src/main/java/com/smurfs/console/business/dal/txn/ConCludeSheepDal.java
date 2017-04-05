package com.smurfs.console.business.dal.txn;

import java.util.Date;
import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blockchain.service.query.TradeRequest;
import com.smurfs.console.business.domain.txn.ConCludeSheet;
import com.smurfs.console.business.jbcc.JbccClientUtil;
import com.smurfs.console.business.jbcc.SplitTxUtils;
import com.smurfs.console.business.jbcc.TBCOriginalBean;
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

@Service("conCludeSheepDal")
public class ConCludeSheepDal {
	public static Logger log = LoggerFactory.getLogger(ConCludeSheepDal.class);

	@Autowired
	JbccClientUtil jbccClientUtil;

	// // 测试调用充值
	// public static void main(String[] args) {
	// ConCludeSheet sheet = new ConCludeSheet();
	// sheet.setBusiNo("01");// 业务单号,成交单号deal_id
	// sheet.setExchangeId("1000001");// 交易所代码
	// sheet.setExchangeMarketType("");// 交易所市场编码
	// sheet.setBusiType("");// 交易所市场业务类型
	// sheet.setMemCode("0007");// 会员编码-发起方
	// sheet.setFundAccountClear("123");// 资金账号-发起方
	// sheet.setOpenTradeAccount("456");// 交易账号-发起方
	// sheet.setOppMemCode("0008");// 会员编码-对手方
	// sheet.setOppFundAccount("789");// 资金账号-对手方
	// sheet.setOppTradeAccount("987");// 交易账号-对手方
	// sheet.setMoneyType("CNY");// 币种编码 
	// sheet.setProductCode("TONG");// 商品合约编码
	// sheet.setProductCategoryId("");// 产品类别ID
	// sheet.setTradeType("");// 交易类型
	// sheet.setBusiDate("20170310");// 业务发生日期(YYYYMMDD)
	// sheet.setBusiTime("20170310143543");// 业务发生时间，成交时间 
	// sheet.setTradeDir("1");// 买卖方向(1买2卖)
	// sheet.setDealType("1");// 成交类型(1开仓 2平仓)
	// sheet.setOppDealType("1");// 对手方成交类型
	// sheet.setOrderWay("Z");// 报单方式(1PC客户端  2移动客户端 3浏览器客户端 4电话委托报单 Z其他)
	// sheet.setDepositWay("1");// 仓单/定金方式 (0仓单 1定金)
	// sheet.setOrderPrice(10d);// 成交价格
	// sheet.setHoldPrice(9d);// 持仓价格
	// sheet.setOrderQuantity(50d);// 成交数量
	// sheet.setDealTotalPrice(500d);// 成交总价
	// // sheet.setdepositRate();//定金率
	// // sheet.setdepositRatioType();//定金率是否比率(0-固定 1-比率)
	// // sheet.setdepositType();//定金收取方式(0-开仓价 1-持仓价)
	// // sheet.setdepositBalance();//定金金额
	// sheet.setOpenPoundage(1d);// 发起方手续费
	// sheet.setOppPoundage(1d);// 对手方手续费
	// sheet.setDepotOrderNo("123456");// 建仓单号
	// sheet.setOppDepotOrderNo("7890123");// 对手方建仓单号
	// sheet.setOrderId("hureer");// 报单编号
	// sheet.setOppOrderId("reerhu");// 对手方报单编号
	// sheet.setSettlementDate("20170310");// 结算日期
	// sheet.setRequestDesc("交易铜50手");// 说明，备注
	// ConCludeSheepDal conCludeSheepDal = new ConCludeSheepDal();
	// conCludeSheepDal.createConCludeSheep(sheet);
	// }

	public ConCludeSheet getConCludeSheepById(long id) {
		return null;
	}

	/**
	 * 成交单jbcc
	 * 
	 * @param sheep
	 * @return
	 */
	public String createConCludeSheep(ConCludeSheet sheep) {
		sheep.setEventType(EventTypeEnum.TRADE.toString());
		TBCOriginalBean tbcBean = new TBCOriginalBean();
		tbcBean.setTxID("CONClUDE_" + sheep.getBusiNo());
		sheep.setEventId(tbcBean.getTxID());
		tbcBean.setMemCode(sheep.getMemCode()); // 买方会员编码
		tbcBean.setOppMemCode(sheep.getOppMemCode());// 卖方会员编码
		tbcBean.setDealTotalPrice(sheep.getDealTotalPrice());// 成交总价
		tbcBean.setOpenPoundage(sheep.getOpenPoundage()); // 买方平台手续费
		tbcBean.setOppPoundage(sheep.getOppPoundage());// 卖方平台手续费
		tbcBean.setOrderPrice(sheep.getOrderPrice()); // 成交单价
		tbcBean.setOrderQuantity(sheep.getOrderQuantity()); // 资产成交数量
		tbcBean.setTxDescription(sheep.getRequestDesc()); // 交易描述
		tbcBean.setProductCode(sheep.getProductCode());
		tbcBean.setTxFeePlatformAccountID(sheep.getExchangeId());
		tbcBean.setTxTimestamp(sheep.getBusiTime());
		tbcBean.setNonquantifiableInfo(BeanCopyUtil.toMap(sheep, Constants.ignorePropertys));
		ABCAtomicBean abcAtomicTx = SplitTxUtils.splitTxFromTBCOriginalToABCAtomicForConClude(tbcBean);
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
				log.error("send sheep serialNo:" + sheep.getBusiNo() + " JBCCResult error" + myresult);
				throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg);
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("send sheep serialNo:" + sheep.getBusiNo() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}
		return null;

	}

	/**
	 * 查询成交数据
	 */
	public String query(TradeRequest request) {
		
		TDBCQueryBean4OriginalTxBySingleCondition tqb = new TDBCQueryBean4OriginalTxBySingleCondition();
		tqb.setIactTableName(JbccConstant.indexAccount);
		tqb.setTbcTableName(JbccConstant.tbc);
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
			// TODO Auto-generated catch block
			log.error("query flow request: " + request.toString() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccQueryException(ErrorConstants.fundFlow_error_code, ErrorConstants.fundFlow_error_msg, e);
		}
	}

	public int modifyConCludeSheep(ConCludeSheet sheep) {
		return 0;
	}

}
