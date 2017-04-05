package com.smurfs.console.business.domain.txn;

import java.util.Date;

public class ConCludeSheet {
	private Long id;
	private Date requestTime;
	private String requestId;
	private String busiNo;
	private String exchangeId;
	private String exchangeMarketType;
	private String busiType;
	private String memCode;
	private String fundAccountClear;
	private String openTradeAccount;
	private String oppMemCode;
	private String oppFundAccount;
	private String oppTradeAccount;
	private String moneyType;
	private String productCode;
	private String productCategoryId;
	private String tradeType;
	private String busiDate;
	private String busiTime;
	private String tradeDir;
	private String dealType;
	private String oppDealType;
	private String orderWay;
	private String depositWay;
	private Double orderPrice;
	private Double holdPrice;
	private Double orderQuantity;
	private Double dealTotalPrice;
	private Double depositRate;
	private String depositRatioType;
	private String depositType;
	private Double depositBalance;
	private Double openPoundage;
	private Double oppPoundage;
	private String depotOrderNo;
	private String oppDepotOrderNo;
	private String orderId;
	private String oppOrderId;
	private String settlementDate;
	private String requestDesc;
	/** 事件类型，事件ID，用于区分在区块链查询事件种类 */
	private String eventType; // 事件类型
	private String eventId; // 事件ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getExchangeMarketType() {
		return exchangeMarketType;
	}

	public void setExchangeMarketType(String exchangeMarketType) {
		this.exchangeMarketType = exchangeMarketType;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}

	public String getFundAccountClear() {
		return fundAccountClear;
	}

	public void setFundAccountClear(String fundAccountClear) {
		this.fundAccountClear = fundAccountClear;
	}

	public String getOpenTradeAccount() {
		return openTradeAccount;
	}

	public void setOpenTradeAccount(String openTradeAccount) {
		this.openTradeAccount = openTradeAccount;
	}

	public String getOppMemCode() {
		return oppMemCode;
	}

	public void setOppMemCode(String oppMemCode) {
		this.oppMemCode = oppMemCode;
	}

	public String getOppFundAccount() {
		return oppFundAccount;
	}

	public void setOppFundAccount(String oppFundAccount) {
		this.oppFundAccount = oppFundAccount;
	}

	public String getOppTradeAccount() {
		return oppTradeAccount;
	}

	public void setOppTradeAccount(String oppTradeAccount) {
		this.oppTradeAccount = oppTradeAccount;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBusiDate() {
		return busiDate;
	}

	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

	public String getBusiTime() {
		return busiTime;
	}

	public void setBusiTime(String busiTime) {
		this.busiTime = busiTime;
	}

	public String getTradeDir() {
		return tradeDir;
	}

	public void setTradeDir(String tradeDir) {
		this.tradeDir = tradeDir;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getOppDealType() {
		return oppDealType;
	}

	public void setOppDealType(String oppDealType) {
		this.oppDealType = oppDealType;
	}

	public String getOrderWay() {
		return orderWay;
	}

	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}

	public String getDepositWay() {
		return depositWay;
	}

	public void setDepositWay(String depositWay) {
		this.depositWay = depositWay;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Double getHoldPrice() {
		return holdPrice;
	}

	public void setHoldPrice(Double holdPrice) {
		this.holdPrice = holdPrice;
	}

	public Double getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getDealTotalPrice() {
		return dealTotalPrice;
	}

	public void setDealTotalPrice(Double dealTotalPrice) {
		this.dealTotalPrice = dealTotalPrice;
	}

	public Double getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(Double depositRate) {
		this.depositRate = depositRate;
	}

	public String getDepositRatioType() {
		return depositRatioType;
	}

	public void setDepositRatioType(String depositRatioType) {
		this.depositRatioType = depositRatioType;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public Double getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(Double depositBalance) {
		this.depositBalance = depositBalance;
	}

	public Double getOpenPoundage() {
		return openPoundage;
	}

	public void setOpenPoundage(Double openPoundage) {
		this.openPoundage = openPoundage;
	}

	public Double getOppPoundage() {
		return oppPoundage;
	}

	public void setOppPoundage(Double oppPoundage) {
		this.oppPoundage = oppPoundage;
	}

	public String getDepotOrderNo() {
		return depotOrderNo;
	}

	public void setDepotOrderNo(String depotOrderNo) {
		this.depotOrderNo = depotOrderNo;
	}

	public String getOppDepotOrderNo() {
		return oppDepotOrderNo;
	}

	public void setOppDepotOrderNo(String oppDepotOrderNo) {
		this.oppDepotOrderNo = oppDepotOrderNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOppOrderId() {
		return oppOrderId;
	}

	public void setOppOrderId(String oppOrderId) {
		this.oppOrderId = oppOrderId;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	@Override
	public String toString() {
		return "ConCludeSheet [id=" + id + ", requestTime=" + requestTime + ", requestId=" + requestId + ", busiNo="
				+ busiNo + ", exchangeId=" + exchangeId + ", exchangeMarketType=" + exchangeMarketType + ", busiType="
				+ busiType + ", memCode=" + memCode + ", fundAccountClear=" + fundAccountClear + ", openTradeAccount="
				+ openTradeAccount + ", oppMemCode=" + oppMemCode + ", oppFundAccount=" + oppFundAccount
				+ ", oppTradeAccount=" + oppTradeAccount + ", moneyType=" + moneyType + ", productCode=" + productCode
				+ ", productCategoryId=" + productCategoryId + ", tradeType=" + tradeType + ", busiDate=" + busiDate
				+ ", busiTime=" + busiTime + ", tradeDir=" + tradeDir + ", dealType=" + dealType + ", oppDealType="
				+ oppDealType + ", orderWay=" + orderWay + ", depositWay=" + depositWay + ", orderPrice=" + orderPrice
				+ ", holdPrice=" + holdPrice + ", orderQuantity=" + orderQuantity + ", dealTotalPrice=" + dealTotalPrice
				+ ", depositRate=" + depositRate + ", depositRatioType=" + depositRatioType + ", depositType="
				+ depositType + ", depositBalance=" + depositBalance + ", openPoundage=" + openPoundage
				+ ", oppPoundage=" + oppPoundage + ", depotOrderNo=" + depotOrderNo + ", oppDepotOrderNo="
				+ oppDepotOrderNo + ", orderId=" + orderId + ", oppOrderId=" + oppOrderId + ", settlementDate="
				+ settlementDate + ", requestDesc=" + requestDesc + "]";
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
