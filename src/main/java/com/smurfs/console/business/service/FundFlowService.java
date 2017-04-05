package com.smurfs.console.business.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.blockchain.service.page.PageResponse;
import com.blockchain.service.query.TradeRequest;
import com.blockchain.service.query.TradeResponse;
import com.smurfs.console.business.dal.txn.FundFlowDal;
import com.smurfs.console.business.domain.txn.FundFlow;
import com.smurfs.console.business.jbcc.BusinessReturnResultBean;
import com.smurfs.console.business.jbcc.TBCOriginalBean;
import com.smurfs.console.util.BeanCopyUtil;
import com.smurfs.console.util.StringUtil;

@Service
public class FundFlowService {
	public static Logger log = LoggerFactory.getLogger(FundFlowService.class);
	@Autowired
	private FundFlowDal fundFlowDal;

	public String fundIn(FundFlow flow) {
		flow.setOptType(1);
		return fundFlowDal.createFundFlow(flow);
	}

	public String fundOut(FundFlow flow) {
		flow.setOptType(2);
		return fundFlowDal.createFundFlow(flow);
	}

	public String fundFrozen(FundFlow flow) {
		flow.setOptType(3);
		return fundFlowDal.createFundFlow(flow);
	}

	/**
	 * 查询出入金原始交易
	 * 
	 * @param request
	 * @return
	 */
	public PageResponse<TradeResponse> query(TradeRequest request) {
		PageResponse<TradeResponse> page = new PageResponse<TradeResponse>();
		page.setPageNum(request.getPageNum());
		page.setPageSize(request.getPageSize());
		page.setTotalSize(0);
		List<TradeResponse> dataList = new ArrayList<TradeResponse>();
		String jsonMsg = fundFlowDal.query(request);
		if (StringUtil.isNotBlank(jsonMsg)) {
			BusinessReturnResultBean returnBean = JSON.parseObject(jsonMsg, BusinessReturnResultBean.class);
			if (returnBean != null && returnBean.getData() != null) {
				page.setTotalSize(returnBean.getTotalCount());
				ArrayList<String> list = JSON.parseObject(returnBean.getData().toString(), new ArrayList().getClass());
				if (!CollectionUtils.isEmpty(list)) {
					for (String objStr : list) {
						TBCOriginalBean bean = JSON.parseObject(objStr, TBCOriginalBean.class);
						TradeResponse response = BeanCopyUtil.cvtDozer(bean.getNonquantifiableInfo(),
								TradeResponse.class);
						response.setOccurAmount(bean.getNonquantifiableInfo().get("orderAmt"));
						response.setClearSerialNo(bean.getNonquantifiableInfo().get("serialNo"));
						response.setInitDate(bean.getNonquantifiableInfo().get("busiDate"));
						response.setExchangeId(bean.getNonquantifiableInfo().get("systemCode"));
						response.setExchangeFundAccount(bean.getNonquantifiableInfo().get("fundAccountClear"));
						response.setInoutFlag(bean.getNonquantifiableInfo().get("optType"));
						response.setSourceFrom(bean.getNonquantifiableInfo().get("inoutSource"));

						dataList.add(response);
					}
				}
			}
		}
		page.setList(dataList);
		return page;
	}

}
