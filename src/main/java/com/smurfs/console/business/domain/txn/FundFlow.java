package com.smurfs.console.business.domain.txn;

import java.util.Date;

import org.dozer.Mapping;

public class FundFlow {
  private Long id;
  private Date reqTime;
  private String reqId;
  private String serialNo;
  private String tradeSerialNo;
  private String orderId;
  private String systemCode;
  private String busiDate;
  private String busiTime;
  private String productType;
  private String productCode;
  private String busiType;
  private String memCode;
  private String fundAccountClear;
  private String moneyType;
  private Double orderAmt;
  @Mapping("outPoundage")
  private Double feeAmt;
  private String bankProCode;
  private String accountName;
  private String bankAccount;
  private String bankNO;
  private String crossFlag;
  private String largeBankId;
  private String unionBankId;
  private String outAcctIdBankName;
  private String memberMainType;
  private String fullName;
  private String idKind;
  private String idNo;
  private String requestDesc;
  /** 1-入金,2-出金,3-冻结 */
  private int optType;
  /**事件类型，事件ID，用于区分在区块链查询事件种类*/
  private String eventType; //时间类型
  private String eventId; //事件ID

  /**
   * @return the reqTime
   */
  public Date getReqTime() {
    return reqTime;
  }

  /**
   * @param reqTime
   *          the reqTime to set
   */
  public void setReqTime(Date reqTime) {
    this.reqTime = reqTime;
  }

  /**
   * @return the reqId
   */
  public String getReqId() {
    return reqId;
  }

  /**
   * @param reqId
   *          the reqId to set
   */
  public void setReqId(String reqId) {
    this.reqId = reqId;
  }

  /**
   * @return the serialNo
   */
  public String getSerialNo() {
    return serialNo;
  }

  /**
   * @param serialNo
   *          the serialNo to set
   */
  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }


  /**
   * @return the orderId
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * @param orderId
   *          the orderId to set
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * @return the systemCode
   */
  public String getSystemCode() {
    return systemCode;
  }

  /**
   * @param systemCode
   *          the systemCode to set
   */
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }

  /**
   * @return the busiDate
   */
  public String getBusiDate() {
    return busiDate;
  }

  /**
   * @param busiDate
   *          the busiDate to set
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
   *          the busiTime to set
   */
  public void setBusiTime(String busiTime) {
    this.busiTime = busiTime;
  }

  /**
   * @return the productType
   */
  public String getProductType() {
    return productType;
  }

  /**
   * @param productType
   *          the productType to set
   */
  public void setProductType(String productType) {
    this.productType = productType;
  }

  /**
   * @return the productCode
   */
  public String getProductCode() {
    return productCode;
  }

  /**
   * @param productCode
   *          the productCode to set
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  /**
   * @return the busiType
   */
  public String getBusiType() {
    return busiType;
  }

  /**
   * @param busiType
   *          the busiType to set
   */
  public void setBusiType(String busiType) {
    this.busiType = busiType;
  }

  /**
   * @return the memCode
   */
  public String getMemCode() {
    return memCode;
  }

  /**
   * @param memCode
   *          the memCode to set
   */
  public void setMemCode(String memCode) {
    this.memCode = memCode;
  }

  /**
   * @return the fundAccountClear
   */
  public String getFundAccountClear() {
    return fundAccountClear;
  }

  /**
   * @param fundAccountClear
   *          the fundAccountClear to set
   */
  public void setFundAccountClear(String fundAccountClear) {
    this.fundAccountClear = fundAccountClear;
  }

  /**
   * @return the moneyType
   */
  public String getMoneyType() {
    return moneyType;
  }

  /**
   * @param moneyType
   *          the moneyType to set
   */
  public void setMoneyType(String moneyType) {
    this.moneyType = moneyType;
  }

  /**
   * @return the bankProCode
   */
  public String getBankProCode() {
    return bankProCode;
  }

  /**
   * @param bankProCode
   *          the bankProCode to set
   */
  public void setBankProCode(String bankProCode) {
    this.bankProCode = bankProCode;
  }

  /**
   * @return the accountName
   */
  public String getAccountName() {
    return accountName;
  }

  /**
   * @param accountName
   *          the accountName to set
   */
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  /**
   * @return the bankAccount
   */
  public String getBankAccount() {
    return bankAccount;
  }

  /**
   * @param bankAccount
   *          the bankAccount to set
   */
  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  /**
   * @return the bankNO
   */
  public String getBankNO() {
    return bankNO;
  }

  /**
   * @param bankNO
   *          the bankNO to set
   */
  public void setBankNO(String bankNO) {
    this.bankNO = bankNO;
  }

  /**
   * @return the crossFlag
   */
  public String getCrossFlag() {
    return crossFlag;
  }

  /**
   * @param crossFlag
   *          the crossFlag to set
   */
  public void setCrossFlag(String crossFlag) {
    this.crossFlag = crossFlag;
  }

  /**
   * @return the largeBankId
   */
  public String getLargeBankId() {
    return largeBankId;
  }

  /**
   * @param largeBankId
   *          the largeBankId to set
   */
  public void setLargeBankId(String largeBankId) {
    this.largeBankId = largeBankId;
  }

  /**
   * @return the unionBankId
   */
  public String getUnionBankId() {
    return unionBankId;
  }

  /**
   * @param unionBankId
   *          the unionBankId to set
   */
  public void setUnionBankId(String unionBankId) {
    this.unionBankId = unionBankId;
  }

  /**
   * @return the outAcctIdBankName
   */
  public String getOutAcctIdBankName() {
    return outAcctIdBankName;
  }

  /**
   * @param outAcctIdBankName
   *          the outAcctIdBankName to set
   */
  public void setOutAcctIdBankName(String outAcctIdBankName) {
    this.outAcctIdBankName = outAcctIdBankName;
  }

  /**
   * @return the memberMainType
   */
  public String getMemberMainType() {
    return memberMainType;
  }

  /**
   * @param memberMainType
   *          the memberMainType to set
   */
  public void setMemberMainType(String memberMainType) {
    this.memberMainType = memberMainType;
  }

  /**
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * @param fullName
   *          the fullName to set
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * @return the idKind
   */
  public String getIdKind() {
    return idKind;
  }

  /**
   * @param idKind
   *          the idKind to set
   */
  public void setIdKind(String idKind) {
    this.idKind = idKind;
  }

  /**
   * @return the idNo
   */
  public String getIdNo() {
    return idNo;
  }

  /**
   * @param idNo
   *          the idNo to set
   */
  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  /**
   * @return the requestDesc
   */
  public String getRequestDesc() {
    return requestDesc;
  }

  /**
   * @param requestDesc
   *          the requestDesc to set
   */
  public void setRequestDesc(String requestDesc) {
    this.requestDesc = requestDesc;
  }

  /**
   * @return the optType
   */
  public int getOptType() {
    return optType;
  }

  /**
   * @param optType
   *          the optType to set
   */
  public void setOptType(int optType) {
    this.optType = optType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "FundFlow [id=" + id + ", reqTime=" + reqTime + ", reqId=" + reqId + ", serialNo=" + serialNo
        + ", tradeSerialNO=" + tradeSerialNo + ", orderId=" + orderId + ", systemCode=" + systemCode + ", busiDate="
        + busiDate + ", busiTime=" + busiTime + ", productType=" + productType + ", productCode=" + productCode
        + ", busiType=" + busiType + ", memCode=" + memCode + ", fundAccountClear=" + fundAccountClear + ", moneyType="
        + moneyType + ", orderAmt=" + orderAmt + ", feeAmt=" + feeAmt + ", bankProCode=" + bankProCode
        + ", accountName=" + accountName + ", bankAccount=" + bankAccount + ", bankNO=" + bankNO + ", crossFlag="
        + crossFlag + ", largeBankId=" + largeBankId + ", unionBankId=" + unionBankId + ", outAcctIdBankName="
        + outAcctIdBankName + ", memberMainType=" + memberMainType + ", fullName=" + fullName + ", idKind=" + idKind
        + ", idNo=" + idNo + "]";
  }

public Double getOrderAmt() {
	return orderAmt;
}

public void setOrderAmt(Double orderAmt) {
	this.orderAmt = orderAmt;
}

public Double getFeeAmt() {
	return feeAmt;
}

public void setFeeAmt(Double feeAmt) {
	this.feeAmt = feeAmt;
}

public void setId(Long id) {
	this.id = id;
}

public String getTradeSerialNo() {
	return tradeSerialNo;
}

public void setTradeSerialNo(String tradeSerialNo) {
	this.tradeSerialNo = tradeSerialNo;
}

public Long getId() {
	return id;
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
