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
import com.blockchain.service.query.ConCludeResponse;
import com.blockchain.service.query.TradeRequest;
import com.smurfs.console.business.dal.txn.ConCludeSheepDal;
import com.smurfs.console.business.domain.txn.ConCludeSheet;
import com.smurfs.console.business.jbcc.BusinessReturnResultBean;
import com.smurfs.console.business.jbcc.TBCOriginalBean;
import com.smurfs.console.util.BeanCopyUtil;
import com.smurfs.console.util.StringUtil;

@Service
public class ConCludeService {
	public static Logger log = LoggerFactory.getLogger(ConCludeService.class);
	@Autowired
	private ConCludeSheepDal conCludeSheepDal;

	public String createConClude(ConCludeSheet sheep) {
		return conCludeSheepDal.createConCludeSheep(sheep);
	}

	/**
	 * 查询成交原始交易
	 * 
	 * @param request
	 * @return
	 */
	public PageResponse<ConCludeResponse> query(TradeRequest request) {
		PageResponse<ConCludeResponse> page = new PageResponse<ConCludeResponse>();
		page.setPageNum(request.getPageNum());
		page.setPageSize(request.getPageSize());
		page.setTotalSize(0);
		List<ConCludeResponse> dataList = new ArrayList<ConCludeResponse>();
		String jsonMsg = conCludeSheepDal.query(request);
		if (StringUtil.isNotBlank(jsonMsg)) {
			BusinessReturnResultBean returnBean = JSON.parseObject(jsonMsg, BusinessReturnResultBean.class);
			if (returnBean != null && returnBean.getData() != null) {
				page.setTotalSize(returnBean.getTotalCount());
				ArrayList<String> list = JSON.parseObject(returnBean.getData().toString(), new ArrayList().getClass());
				if (!CollectionUtils.isEmpty(list)) {
					for (String objStr : list) {
						TBCOriginalBean bean = JSON.parseObject(objStr, TBCOriginalBean.class);
						ConCludeResponse conCludeResponse = BeanCopyUtil.cvtDozer(bean.getNonquantifiableInfo(),
								ConCludeResponse.class);

						dataList.add(conCludeResponse);
					}
				}
			}
		}
		page.setList(dataList);
		return page;
	}
}
