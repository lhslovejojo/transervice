package com.smurfs.console.business.jbcc;

import java.util.HashMap;

public class TBCCustomizeOriginalPositionBean {
	private String txID; // 交易ID ：GQS001_20170301001
	private Double leftQuantity; // 资金剩余数量
	private String txUnit; // 单位：吨、千克、克拉、桶、吨、加仑、升 立方米
	private Double orderPrice; // 成交价格：100￥/吨
	private String memCode; // 资产所属账户
	private String holdId; // 持仓单号
	private String busiDatetime; //
	private String txDescription; // 描述：购买大宗商品例如：铜
	private String productCode; // 产品编码
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

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}


	public String getTxDescription() {
		return txDescription;
	}

	public void setTxDescription(String txDescription) {
		this.txDescription = txDescription;
	}

	public HashMap<String, String> getNonquantifiableInfo() {
		return nonquantifiableInfo;
	}

	public void setNonquantifiableInfo(HashMap<String, String> nonquantifiableInfo) {
		this.nonquantifiableInfo = nonquantifiableInfo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Double getLeftQuantity() {
		return leftQuantity;
	}

	public void setLeftQuantity(Double leftQuantity) {
		this.leftQuantity = leftQuantity;
	}

	public String getHoldId() {
		return holdId;
	}

	public void setHoldId(String holdId) {
		this.holdId = holdId;
	}

	public String getBusiDatetime() {
		return busiDatetime;
	}

	public void setBusiDatetime(String busiDatetime) {
		this.busiDatetime = busiDatetime;
	}

}
