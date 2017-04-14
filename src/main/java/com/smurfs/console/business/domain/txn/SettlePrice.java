package com.smurfs.console.business.domain.txn;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结算价信息
 * 
 * @author lihongsong
 *
 */
public class SettlePrice implements Serializable {

	private static final long serialVersionUID = 4210279700643416761L;

	private String initDate;
	private String exchangeId;
	private String exchangeMarketType;
	private String productCategoryId;
	private String productCode;
	private String moneyType;
	private BigDecimal settlePrice;

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
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

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}


	@Override
	public String toString() {
		return "SettlePrice [initDate=" + initDate + ", exchangeId=" + exchangeId + ", exchangeMarketType="
				+ exchangeMarketType + ", productCategoryId=" + productCategoryId + ", productCode=" + productCode
				+ ", moneyType=" + moneyType + ", settlePrice=" + settlePrice + "]";
	}

	public BigDecimal getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

}
