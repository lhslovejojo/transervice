package com.smurfs.console.business.domain.txn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Position implements Serializable {

	private static final long serialVersionUID = 4210289700643416761L;
	/** 请求日期 */
	private Date requestTime;
	/** 请求流水号 */
	private String requestId;
	/** 业务单号,建议productCode+建仓单号 */
	private String BusiNo;
	/** 交易所代码 */
	private String exchangeId;
	/** 会员编码 */
	private String memCode;
	/** 交易账号 */
	private String openTradeAccount;
	/** 商品合约编码，产品代码 */
	private String productCode;
	/** 产品类别ID */
	private String productCategoryId;
	/** 交易类型 */
	private String tradeType;
	/** 业务发生日期(YYYYMMDD) */
	private String busiDate;
	/** 业务发生时间，成交时间YYYYMMDDHHmmdd */
	private String busiTime;
	/** 买卖方向(1买2卖) */
	private String tradeDir;
	/** 成交类型(1开仓 2平仓) */
	private String dealType;
	/** 对手方成交类型 */
	private String oppDealType;
	/** 仓单/定金方式(0仓单 1定金) */
	private String depositWay;
	/** 成交价格 */
	private BigDecimal orderPrice;
	/** 持仓价格 */
	private BigDecimal holdPrice;
	/** 成交数量 */
	private BigDecimal orderQuantity;
	/** 剩余数量 */
	private BigDecimal leftQuantity;
	/** 数量单位 */
	private String presentUnit;
	/** 定金率 */
	private BigDecimal depositRate;
	/** 定金率是否比率(0-固定 1-比率) */
	private String depositRatioType;
	/** 定金收取方式(0-开仓价 1-持仓价) */
	private String depositType;
	/** 定金金额或履约准备金 */
	private BigDecimal depositBalance;
	/** 手续费 */
	private BigDecimal openPoundage;
	/** 滞纳金 */
	private BigDecimal delayFees;
	/** 平仓盈亏 */
	private BigDecimal squareLoss;
	/** 结算盈亏 */
	private BigDecimal settleLoss;
	/** 结算价格 */
	private BigDecimal settlePrice;
	/** 建仓单号 */
	private String depotOrderNo;
	/** 说明，备注 */
	private String requestDesc;

	@Override
	public String toString() {
		return "PositionRequest [requestTime=" + requestTime + ", requestId=" + requestId + ", BusiNo=" + BusiNo
				+ ", exchangeId=" + exchangeId + ", memCode=" + memCode + ", openTradeAccount=" + openTradeAccount
				+ ", productCode=" + productCode + ", productCategoryId=" + productCategoryId + ", tradeType="
				+ tradeType + ", busiDate=" + busiDate + ", busiTime=" + busiTime + ", tradeDir=" + tradeDir
				+ ", dealType=" + dealType + ", oppDealType=" + oppDealType + ", depositWay=" + depositWay
				+ ", orderPrice=" + orderPrice + ", holdPrice=" + holdPrice + ", orderQuantity=" + orderQuantity
				+ ", leftQuantity=" + leftQuantity + ", presentUnit=" + presentUnit + ", depositRate=" + depositRate
				+ ", depositRatioType=" + depositRatioType + ", depositType=" + depositType + ", depositBalance="
				+ depositBalance + ", openPoundage=" + openPoundage + ", delayFees=" + delayFees + ", squareLoss="
				+ squareLoss + ", settleLoss=" + settleLoss + ", settlePrice=" + settlePrice + ", depotOrderNo="
				+ depotOrderNo + ", requestDesc=" + requestDesc + "]";
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
		return BusiNo;
	}

	public void setBusiNo(String busiNo) {
		BusiNo = busiNo;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}

	public String getOpenTradeAccount() {
		return openTradeAccount;
	}

	public void setOpenTradeAccount(String openTradeAccount) {
		this.openTradeAccount = openTradeAccount;
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

	public String getDepositWay() {
		return depositWay;
	}

	public void setDepositWay(String depositWay) {
		this.depositWay = depositWay;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getHoldPrice() {
		return holdPrice;
	}

	public void setHoldPrice(BigDecimal holdPrice) {
		this.holdPrice = holdPrice;
	}

	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public BigDecimal getLeftQuantity() {
		return leftQuantity;
	}

	public void setLeftQuantity(BigDecimal leftQuantity) {
		this.leftQuantity = leftQuantity;
	}

	public String getPresentUnit() {
		return presentUnit;
	}

	public void setPresentUnit(String presentUnit) {
		this.presentUnit = presentUnit;
	}

	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(BigDecimal depositRate) {
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

	public BigDecimal getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(BigDecimal depositBalance) {
		this.depositBalance = depositBalance;
	}

	public BigDecimal getOpenPoundage() {
		return openPoundage;
	}

	public void setOpenPoundage(BigDecimal openPoundage) {
		this.openPoundage = openPoundage;
	}

	public BigDecimal getDelayFees() {
		return delayFees;
	}

	public void setDelayFees(BigDecimal delayFees) {
		this.delayFees = delayFees;
	}

	public BigDecimal getSquareLoss() {
		return squareLoss;
	}

	public void setSquareLoss(BigDecimal squareLoss) {
		this.squareLoss = squareLoss;
	}

	public BigDecimal getSettleLoss() {
		return settleLoss;
	}

	public void setSettleLoss(BigDecimal settleLoss) {
		this.settleLoss = settleLoss;
	}

	public BigDecimal getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

	public String getDepotOrderNo() {
		return depotOrderNo;
	}

	public void setDepotOrderNo(String depotOrderNo) {
		this.depotOrderNo = depotOrderNo;
	}

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

}
