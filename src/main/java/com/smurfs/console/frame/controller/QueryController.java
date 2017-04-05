package com.smurfs.console.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.blockchain.service.QueryService;
import com.blockchain.service.query.ResVo;
import com.blockchain.service.query.TradeRequest;
import com.blockchain.service.query.UserRequest;
import com.blockchain.service.query.UserResponse;
import com.smurfs.console.business.service.ConCludeService;
import com.smurfs.console.business.service.FundFlowService;
import com.smurfs.console.business.service.UserInfService;
import com.smurfs.console.constants.EventTypeEnum;

@Controller
@RequestMapping(value="query")
public class QueryController {
	public static Logger log = LoggerFactory.getLogger(QueryController.class);
	@Autowired
	QueryService queryService;
	@Autowired
	UserInfService userInfService;
	@Autowired
	ConCludeService conCludeService;
	@Autowired
	FundFlowService  fundFlowService;

	/**
	 * 通过会员编码，精确查询一个会员，包含资金情况
	 * 
	 * @param memCode
	 * @return
	 */
	@RequestMapping(value = "/getAccountByMemCode")
	public @ResponseBody ResVo<UserResponse> grabData(@RequestParam(value = "memCode") String memCode) {
		log.info("getAccountByMemCode memCode" + memCode);
		try {
			return ResVo.getSuccessRes(queryService.getUserByMemCode(memCode));
		} catch (Exception e) {
			return ResVo.getFailRes(e.getMessage());
		}
	}

	/**
	 * 模糊查询，查询会员带有分页信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/queryAccount")
	public @ResponseBody ResVo queryAccount( UserRequest request) {
		log.info("request" + request);
		try {
			return ResVo.getSuccessRes(userInfService.queryByParam(request));
		} catch (Exception e) {
			return ResVo.getFailRes(e.getMessage());
		}
	}
	
	/**
	 * 通过会员编码和产品代码查询持仓信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/queryHoldAsset")
	public @ResponseBody ResVo queryHoldAsset(@RequestParam(value = "memCode",required=true) String memCode,@RequestParam(value = "productCode",required=false) String productCode) {
		log.info("memCode:" + memCode+ " productCode:"+productCode);
		try {
			return ResVo.getSuccessRes(userInfService.queryPositionAsset(null,memCode,productCode));
		} catch (Exception e) {
			return ResVo.getFailRes(e.getMessage());
		}
	}
	/**
	 * 通过会员编码和产品代码查询持仓信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/queryHoldAssetHis")
	public @ResponseBody ResVo queryHoldAssetHis(@RequestParam(value = "memCode",required=true) String memCode,@RequestParam(value = "productCode",required=true) String productCode) {
		log.info("memCode:" + memCode+ " productCode:"+productCode);
		try {
			return ResVo.getSuccessRes(userInfService.queryPositionAssetHis(null,memCode,productCode));
		} catch (Exception e) {
			return ResVo.getFailRes(e.getMessage());
		}
	}
	/**
	 * 查询原始交易
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	@RequestMapping(value = "/queryConClude")
	public @ResponseBody ResVo queryConClude(@RequestParam(value = "memCode",required=true) String memCode,@RequestParam(value = "pageSize",required=false) Integer pageSize,@RequestParam(value = "pageNum",required=false) Integer pageNum) {
		log.info("memCode:" + memCode);
		TradeRequest request =new TradeRequest();
		request.setPageNum(pageNum!=null ?pageNum:1);
		request.setPageSize(pageSize!=null ?pageSize:50);
		request.setMemCode(memCode);
		request.setBusiType(EventTypeEnum.TRADE.toString());
		try {
			return ResVo.getSuccessRes(conCludeService.query(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResVo.getFailRes(e.getMessage());
		}
	}
	/**
	 * 查询原始交易
	 * @param memCode
	 * @param productCode
	 * @return
	 */
	@RequestMapping(value = "/queryFlow")
	public @ResponseBody ResVo queryFlow(@RequestParam(value = "memCode",required=true) String memCode,@RequestParam(value = "pageSize",required=false) Integer pageSize,@RequestParam(value = "pageNum",required=false) Integer pageNum) {
		log.info("memCode:" + memCode);
		TradeRequest request =new TradeRequest();
		request.setMemCode(memCode);
		request.setPageNum(pageNum!=null ?pageNum:1);
		request.setPageSize(pageSize!=null ?pageSize:50);
		request.setBusiType(EventTypeEnum.FUND_IN_OUT.toString());
		try {
			return ResVo.getSuccessRes(fundFlowService.query(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResVo.getFailRes(e.getMessage());
		}
	}
}
