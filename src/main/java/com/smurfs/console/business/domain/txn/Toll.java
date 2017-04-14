package com.smurfs.console.business.domain.txn;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收费单
 * 
 * @author lihongsong
 *
 */
public class Toll {
	/** 请求日期 */
	private Date requestTime;
	/** 请求流水号 */
	private String requestId;
	/** 业务单号 */
	private String BusiNo;
	/** 交易所代码 */
	private String exchangeId;
	/** 交易所市场编码 */
	private String exchangeMarketType;
	/** 交易市场业务类型 */
	private String bizType;
	/** 收费类型 */
	private String exchangeFeesType;
	/** 收费金额 */
	private BigDecimal feesBalance;
	/** 付费会员编码（交易所内部） */
	private String payerMemCode;
	/** 付款资金账号（交易所会员签约账号） */
	private String payerFundAccount;
	/** 收款会员编码（交易所内部） */
	private String payeeMemCode;
	/** 收款资金账号（交易所会员签约账号） */
	private String payeeFundAccount;
	/** 单据类型 */
	private String relatedBillType;
	/** 关联单据编号 */
	private String relatedBillNo;
	/** 业务发生日期(YYYYMMDD) */
	private String busiDate;
	/** 业务发生时间YYYYMMDDHHmmdd */
	private String busiTime;
	/** 说明，备注 */
	private String requestDesc;
	/** 事件类型，事件ID，用于区分在区块链查询事件种类 */
	private String eventType; // 时间类型
	private String eventId; // 事件ID

	/**
	 * @return the requestTime
	 */
	public Date getRequestTime() {
		return requestTime;
	}

	/**
	 * @param requestTime
	 *            the requestTime to set
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the busiNo
	 */
	public String getBusiNo() {
		return BusiNo;
	}

	/**
	 * @param busiNo
	 *            the busiNo to set
	 */
	public void setBusiNo(String busiNo) {
		BusiNo = busiNo;
	}

	/**
	 * @return the exchangeId
	 */
	public String getExchangeId() {
		return exchangeId;
	}

	/**
	 * @param exchangeId
	 *            the exchangeId to set
	 */
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	/**
	 * @return the exchangeMarketType
	 */
	public String getExchangeMarketType() {
		return exchangeMarketType;
	}

	/**
	 * @param exchangeMarketType
	 *            the exchangeMarketType to set
	 */
	public void setExchangeMarketType(String exchangeMarketType) {
		this.exchangeMarketType = exchangeMarketType;
	}

	/**
	 * @return the bizType
	 */
	public String getBizType() {
		return bizType;
	}

	/**
	 * @param bizType
	 *            the bizType to set
	 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	/**
	 * @return the exchangeFeesType
	 */
	public String getExchangeFeesType() {
		return exchangeFeesType;
	}

	/**
	 * @param exchangeFeesType
	 *            the exchangeFeesType to set
	 */
	public void setExchangeFeesType(String exchangeFeesType) {
		this.exchangeFeesType = exchangeFeesType;
	}

	/**
	 * @return the payerMemCode
	 */
	public String getPayerMemCode() {
		return payerMemCode;
	}

	/**
	 * @param payerMemCode
	 *            the payerMemCode to set
	 */
	public void setPayerMemCode(String payerMemCode) {
		this.payerMemCode = payerMemCode;
	}

	/**
	 * @return the payerFundAccount
	 */
	public String getPayerFundAccount() {
		return payerFundAccount;
	}

	/**
	 * @param payerFundAccount
	 *            the payerFundAccount to set
	 */
	public void setPayerFundAccount(String payerFundAccount) {
		this.payerFundAccount = payerFundAccount;
	}

	/**
	 * @return the payeeMemCode
	 */
	public String getPayeeMemCode() {
		return payeeMemCode;
	}

	/**
	 * @param payeeMemCode
	 *            the payeeMemCode to set
	 */
	public void setPayeeMemCode(String payeeMemCode) {
		this.payeeMemCode = payeeMemCode;
	}

	/**
	 * @return the payeeFundAccount
	 */
	public String getPayeeFundAccount() {
		return payeeFundAccount;
	}

	/**
	 * @param payeeFundAccount
	 *            the payeeFundAccount to set
	 */
	public void setPayeeFundAccount(String payeeFundAccount) {
		this.payeeFundAccount = payeeFundAccount;
	}

	/**
	 * @return the relatedBillType
	 */
	public String getRelatedBillType() {
		return relatedBillType;
	}

	/**
	 * @param relatedBillType
	 *            the relatedBillType to set
	 */
	public void setRelatedBillType(String relatedBillType) {
		this.relatedBillType = relatedBillType;
	}

	/**
	 * @return the relatedBillNo
	 */
	public String getRelatedBillNo() {
		return relatedBillNo;
	}

	/**
	 * @param relatedBillNo
	 *            the relatedBillNo to set
	 */
	public void setRelatedBillNo(String relatedBillNo) {
		this.relatedBillNo = relatedBillNo;
	}

	/**
	 * @return the busiDate
	 */
	public String getBusiDate() {
		return busiDate;
	}

	/**
	 * @param busiDate
	 *            the busiDate to set
	 */
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

	/**
	 * @return the busiTime
	 */
	public String getBusiTime() {
		return busiTime;
	}

	/**
	 * @param busiTime
	 *            the busiTime to set
	 */
	public void setBusiTime(String busiTime) {
		this.busiTime = busiTime;
	}

	/**
	 * @return the requestDesc
	 */
	public String getRequestDesc() {
		return requestDesc;
	}

	/**
	 * @param requestDesc
	 *            the requestDesc to set
	 */
	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TollRequest [requestTime=" + requestTime + ", requestId=" + requestId + ", BusiNo=" + BusiNo
				+ ", exchangeId=" + exchangeId + ", exchangeMarketType=" + exchangeMarketType + ", bizType=" + bizType
				+ ", exchangeFeesType=" + exchangeFeesType + ", feesBalance=" + feesBalance + ", payerMemCode="
				+ payerMemCode + ", payerFundAccount=" + payerFundAccount + ", payeeMemCode=" + payeeMemCode
				+ ", payeeFundAccount=" + payeeFundAccount + ", relatedBillType=" + relatedBillType + ", relatedBillNo="
				+ relatedBillNo + ", busiDate=" + busiDate + ", busiTime=" + busiTime + ", requestDesc=" + requestDesc
				+ "]";
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

	public BigDecimal getFeesBalance() {
		return feesBalance;
	}

	public void setFeesBalance(BigDecimal feesBalance) {
		this.feesBalance = feesBalance;
	}

}
