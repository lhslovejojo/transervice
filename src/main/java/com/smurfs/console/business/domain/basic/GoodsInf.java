package com.smurfs.console.business.domain.basic;

import org.dozer.Mapping;

public class GoodsInf {
  private long id;
  @Mapping("exchangeId")
  private String eId;
  @Mapping("productCode")
  private String pCode;
  @Mapping("productName")
  private String pName;
  @Mapping("productCategoryMaxCode")
  private String pMaxCode;
  @Mapping("productCategoryMaxName")
  private String pMaxName;
  @Mapping("productCategoryInId")
  private String pCatId;
  @Mapping("presentUnit")
  private String unit;
  @Mapping("exchangeMarketType")
  private String eMType;
  @Mapping("bizType")
  private String bizType;
  @Mapping("product_status")
  private String pStatus;

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

  /**
   * @return the eId
   */
  public String geteId() {
    return eId;
  }

  /**
   * @param eId
   *          the eId to set
   */
  public void seteId(String eId) {
    this.eId = eId;
  }

  /**
   * @return the pCode
   */
  public String getpCode() {
    return pCode;
  }

  /**
   * @param pCode
   *          the pCode to set
   */
  public void setpCode(String pCode) {
    this.pCode = pCode;
  }

  /**
   * @return the pName
   */
  public String getpName() {
    return pName;
  }

  /**
   * @param pName
   *          the pName to set
   */
  public void setpName(String pName) {
    this.pName = pName;
  }

  /**
   * @return the pMaxCode
   */
  public String getpMaxCode() {
    return pMaxCode;
  }

  /**
   * @param pMaxCode
   *          the pMaxCode to set
   */
  public void setpMaxCode(String pMaxCode) {
    this.pMaxCode = pMaxCode;
  }

  /**
   * @return the pMaxName
   */
  public String getpMaxName() {
    return pMaxName;
  }

  /**
   * @param pMaxName
   *          the pMaxName to set
   */
  public void setpMaxName(String pMaxName) {
    this.pMaxName = pMaxName;
  }

  /**
   * @return the pCatId
   */
  public String getpCatId() {
    return pCatId;
  }

  /**
   * @param pCatId
   *          the pCatId to set
   */
  public void setpCatId(String pCatId) {
    this.pCatId = pCatId;
  }

  /**
   * @return the unit
   */
  public String getUnit() {
    return unit;
  }

  /**
   * @param unit
   *          the unit to set
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * @return the eMType
   */
  public String geteMType() {
    return eMType;
  }

  /**
   * @param eMType
   *          the eMType to set
   */
  public void seteMType(String eMType) {
    this.eMType = eMType;
  }

  /**
   * @return the bizType
   */
  public String getBizType() {
    return bizType;
  }

  /**
   * @param bizType
   *          the bizType to set
   */
  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  /**
   * @return the pStatus
   */
  public String getpStatus() {
    return pStatus;
  }

  /**
   * @param pStatus
   *          the pStatus to set
   */
  public void setpStatus(String pStatus) {
    this.pStatus = pStatus;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GoodsInf [id=" + id + ", eId=" + eId + ", pCode=" + pCode + ", pName=" + pName + ", pMaxCode=" + pMaxCode
        + ", pMaxName=" + pMaxName + ", pCatId=" + pCatId + ", unit=" + unit + ", eMType=" + eMType + ", bizType="
        + bizType + ", pStatus=" + pStatus + "]";
  }

}
