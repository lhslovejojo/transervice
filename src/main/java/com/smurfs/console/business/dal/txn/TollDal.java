package com.smurfs.console.business.dal.txn;

import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smurfs.console.business.domain.txn.FundFlow;
import com.smurfs.console.business.domain.txn.Toll;
import com.smurfs.console.business.jbcc.JbccClientUtil;
import com.smurfs.console.business.jbcc.SplitTxUtils;
import com.smurfs.console.business.jbcc.TBCCustomizeOriginalBean;
import com.smurfs.console.constants.Constants;
import com.smurfs.console.constants.ErrorConstants;
import com.smurfs.console.constants.EventTypeEnum;
import com.smurfs.console.frame.exception.JbccSyncException;
import com.smurfs.console.util.BeanCopyUtil;
import com.tiandetech.jbcc.sll.bean.ABCAtomicBean;
import com.tiandetech.jbcc.sll.bean.TDBCTxBean;
import com.tiandetech.jbcc.sll.thrift.JBCCResult;

@Service("tollDal")
public class TollDal {
	public static Logger log = LoggerFactory.getLogger(TollDal.class);

	@Autowired
	JbccClientUtil jbccClientUtil;

	/**
	 * 收费转账操作
	 * 
	 * @param toll
	 * @return
	 */
	public String createToll(Toll toll) {
		toll.setEventType(EventTypeEnum.TRANSFER.toString());
		TBCCustomizeOriginalBean tbcBean = new TBCCustomizeOriginalBean();
		tbcBean.setTxID("TOll_" + toll.getBusiDate()+toll.getBusiNo());
		toll.setEventId(tbcBean.getTxID());
		tbcBean.setMemCode(toll.getPayerMemCode());
		tbcBean.setOppMemCode(toll.getPayeeFundAccount());
		tbcBean.setDealTotalPrice(toll.getFeesBalance());
		tbcBean.setTxTimestamp(toll.getBusiTime());
		tbcBean.setNonquantifiableInfo(BeanCopyUtil.toMap(toll, Constants.ignorePropertys));
		ABCAtomicBean abcAtomicTx = SplitTxUtils.splitTxFromTBCOriginalToABCAtomicForToll(tbcBean);
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
				log.error("send toll serialNo:" + toll.getBusiNo() + " JBCCResult error" + myresult.toString());
				throw new JbccSyncException(ErrorConstants.toll_error_code, ErrorConstants.toll_error_msg);
			}
			return tbcBean.getTxID();
		} catch (TException e) {
			// TODO Auto-generated catch block
			log.error("send toll serialNo:" + toll.getBusiNo() + ErrorConstants.user_error_msg, e);
			jbccClientUtil.reConnection();
			throw new JbccSyncException(ErrorConstants.toll_error_code, ErrorConstants.toll_error_msg, e);
		}

	}

	public int modifyFundFlow(FundFlow flow) {
		return 0;
	}

}
