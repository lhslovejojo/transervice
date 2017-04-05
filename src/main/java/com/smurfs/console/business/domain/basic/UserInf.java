package com.smurfs.console.business.domain.basic;

public class UserInf {
  private long id;
  private String exchangeId;
  private String serialNo;
  private String busiDate;
  private String memCode;
  private String exchangeMemberStatus;
  private String fullName;
  private String shortName;
  private String enFullName;
  private String enShortName;
  private String gender;
  private String nationality;
  private String idKind;
  private String idNo;
  private String tel;
  private String upMemCode;
  private String brokerCode;
  private String memCodeClear;
  private String fundAccountClear;
  private String memberMainType;
  private String memberType;
  private String exchangeFundAccount;
  private String tradeAccount;
  private String legalPerson;
  private String businessCert;
  private String orgCode;
  private String taxCert;
  private String taxCertType;
  private String regAddr;
  private String comAddr;
  private String contactName;
  private String contactTel;
  private String contactFax;
  private String contactEmail;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

public String getExchangeId() {
	return exchangeId;
}

public void setExchangeId(String exchangeId) {
	this.exchangeId = exchangeId;
}

public String getSerialNo() {
	return serialNo;
}

public void setSerialNo(String serialNo) {
	this.serialNo = serialNo;
}

public String getMemCode() {
	return memCode;
}

public void setMemCode(String memCode) {
	this.memCode = memCode;
}

public String getExchangeMemberStatus() {
	return exchangeMemberStatus;
}

public void setExchangeMemberStatus(String exchangeMemberStatus) {
	this.exchangeMemberStatus = exchangeMemberStatus;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getShortName() {
	return shortName;
}

public void setShortName(String shortName) {
	this.shortName = shortName;
}

public String getEnFullName() {
	return enFullName;
}

public void setEnFullName(String enFullName) {
	this.enFullName = enFullName;
}

public String getEnShortName() {
	return enShortName;
}

public void setEnShortName(String enShortName) {
	this.enShortName = enShortName;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getNationality() {
	return nationality;
}

public void setNationality(String nationality) {
	this.nationality = nationality;
}

public String getIdKind() {
	return idKind;
}

public void setIdKind(String idKind) {
	this.idKind = idKind;
}

public String getIdNo() {
	return idNo;
}

public void setIdNo(String idNo) {
	this.idNo = idNo;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getUpMemCode() {
	return upMemCode;
}

public void setUpMemCode(String upMemCode) {
	this.upMemCode = upMemCode;
}

public String getBrokerCode() {
	return brokerCode;
}

public void setBrokerCode(String brokerCode) {
	this.brokerCode = brokerCode;
}

public String getMemCodeClear() {
	return memCodeClear;
}

public void setMemCodeClear(String memCodeClear) {
	this.memCodeClear = memCodeClear;
}

public String getFundAccountClear() {
	return fundAccountClear;
}

public void setFundAccountClear(String fundAccountClear) {
	this.fundAccountClear = fundAccountClear;
}

public String getMemberMainType() {
	return memberMainType;
}

public void setMemberMainType(String memberMainType) {
	this.memberMainType = memberMainType;
}

public String getMemberType() {
	return memberType;
}

public void setMemberType(String memberType) {
	this.memberType = memberType;
}

public String getExchangeFundAccount() {
	return exchangeFundAccount;
}

public void setExchangeFundAccount(String exchangeFundAccount) {
	this.exchangeFundAccount = exchangeFundAccount;
}

public String getTradeAccount() {
	return tradeAccount;
}

public void setTradeAccount(String tradeAccount) {
	this.tradeAccount = tradeAccount;
}

public String getLegalPerson() {
	return legalPerson;
}

public void setLegalPerson(String legalPerson) {
	this.legalPerson = legalPerson;
}

public String getBusinessCert() {
	return businessCert;
}

public void setBusinessCert(String businessCert) {
	this.businessCert = businessCert;
}

public String getOrgCode() {
	return orgCode;
}

public void setOrgCode(String orgCode) {
	this.orgCode = orgCode;
}

public String getTaxCert() {
	return taxCert;
}

public void setTaxCert(String taxCert) {
	this.taxCert = taxCert;
}

public String getTaxCertType() {
	return taxCertType;
}

public void setTaxCertType(String taxCertType) {
	this.taxCertType = taxCertType;
}

public String getRegAddr() {
	return regAddr;
}

public void setRegAddr(String regAddr) {
	this.regAddr = regAddr;
}

public String getComAddr() {
	return comAddr;
}

public void setComAddr(String comAddr) {
	this.comAddr = comAddr;
}

public String getContactName() {
	return contactName;
}

public void setContactName(String contactName) {
	this.contactName = contactName;
}

public String getContactTel() {
	return contactTel;
}

public void setContactTel(String contactTel) {
	this.contactTel = contactTel;
}

public String getContactFax() {
	return contactFax;
}

public void setContactFax(String contactFax) {
	this.contactFax = contactFax;
}

public String getContactEmail() {
	return contactEmail;
}

public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
}

@Override
public String toString() {
	return "UserInf [id=" + id + ", exchangeId=" + exchangeId + ", serialNo=" + serialNo + ", memCode=" + memCode
			+ ", exchangeMemberStatus=" + exchangeMemberStatus + ", fullName=" + fullName + ", shortName=" + shortName
			+ ", enFullName=" + enFullName + ", enShortName=" + enShortName + ", gender=" + gender + ", nationality="
			+ nationality + ", idKind=" + idKind + ", idNo=" + idNo + ", tel=" + tel + ", upMemCode=" + upMemCode
			+ ", brokerCode=" + brokerCode + ", memCodeClear=" + memCodeClear + ", fundAccountClear=" + fundAccountClear
			+ ", memberMainType=" + memberMainType + ", memberType=" + memberType + ", exchangeFundAccount="
			+ exchangeFundAccount + ", tradeAccount=" + tradeAccount + ", legalPerson=" + legalPerson
			+ ", businessCert=" + businessCert + ", orgCode=" + orgCode + ", taxCert=" + taxCert + ", taxCertType="
			+ taxCertType + ", regAddr=" + regAddr + ", comAddr=" + comAddr + ", contactName=" + contactName
			+ ", contactTel=" + contactTel + ", contactFax=" + contactFax + ", contactEmail=" + contactEmail + "]";
}

public String getBusiDate() {
	return busiDate;
}

public void setBusiDate(String busiDate) {
	this.busiDate = busiDate;
}



}
