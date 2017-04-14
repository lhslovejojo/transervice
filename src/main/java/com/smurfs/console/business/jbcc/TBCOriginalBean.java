package com.smurfs.console.business.jbcc;

import java.math.BigDecimal;
import java.util.HashMap;

public class TBCOriginalBean {
	private String txID; // 交易ID ：唯一性
	private BigDecimal orderQuantity; // 资产成交数量:1000吨

	private BigDecimal dealTotalPrice; // 资金交易金额:100000￥,全部金额

	private String txUnit; // 交易单位：吨、千克、克拉、桶、吨、加仑、升 立方米
	private BigDecimal orderPrice; // 成交价格：100￥/吨
	private String memCode; // 发起方资金账户ID : account1001
	private String oppMemCode; // 接收方账户ID : account1002
	private String txFeePlatformAccountID; // 交易平台手续费账户ID : account0
	private BigDecimal txFeeRate; // 交易平台手续费率
	private BigDecimal openPoundage; // 发起方-交易平台手续费金额
	private BigDecimal oppPoundage; // 接收方 交易平台手续费金额
	private String txTimestamp; // 交易时间戳
	private String txDescription; // 描述：购买大宗商品例如：铜
	private String productCode; // 产品编码
	private String txTradeDir; //交易方向;
	HashMap<String, String> nonquantifiableInfo; // 原始交易中需要保持的非量化信息，比如地址的更新，手机号的更新等
													// //eg. address:beijing
													// phonenumber:132696888888

	public String getTxID() {
		return txID;
	}

	public void setTxID(String txID) {
		this.txID = txID;
	}

	public String getTxUnit() {
		return txUnit;
	}

	public void setTxUnit(String txUnit) {
		this.txUnit = txUnit;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}

	public String getOppMemCode() {
		return oppMemCode;
	}

	public void setOppMemCode(String oppMemCode) {
		this.oppMemCode = oppMemCode;
	}

	public String getTxFeePlatformAccountID() {
		return txFeePlatformAccountID;
	}

	public void setTxFeePlatformAccountID(String txFeePlatformAccountID) {
		this.txFeePlatformAccountID = txFeePlatformAccountID;
	}

	public String getTxTimestamp() {
		return txTimestamp;
	}

	public void setTxTimestamp(String txTimestamp) {
		this.txTimestamp = txTimestamp;
	}

	public String getTxDescription() {
		return txDescription;
	}

	public void setTxDescription(String txDescription) {
		this.txDescription = txDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public HashMap<String, String> getNonquantifiableInfo() {
		return nonquantifiableInfo;
	}

	public void setNonquantifiableInfo(HashMap<String, String> nonquantifiableInfo) {
		this.nonquantifiableInfo = nonquantifiableInfo;
	}

	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public BigDecimal getDealTotalPrice() {
		return dealTotalPrice;
	}

	public void setDealTotalPrice(BigDecimal dealTotalPrice) {
		this.dealTotalPrice = dealTotalPrice;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getTxFeeRate() {
		return txFeeRate;
	}

	public void setTxFeeRate(BigDecimal txFeeRate) {
		this.txFeeRate = txFeeRate;
	}

	public BigDecimal getOpenPoundage() {
		return openPoundage;
	}

	public void setOpenPoundage(BigDecimal openPoundage) {
		this.openPoundage = openPoundage;
	}

	public BigDecimal getOppPoundage() {
		return oppPoundage;
	}

	public void setOppPoundage(BigDecimal oppPoundage) {
		this.oppPoundage = oppPoundage;
	}

	public String getTxTradeDir() {
		return txTradeDir;
	}

	public void setTxTradeDir(String txTradeDir) {
		this.txTradeDir = txTradeDir;
	}


}
