package com.smurfs.console.business.domain.basic;

import org.dozer.Mapping;

public class BankInf {
  private long id;
  @Mapping("bankNo")
  private String bId;
  @Mapping("exchangeId")
  private String eId;
  @Mapping("bankName")
  private String bNm;
  @Mapping("bankProductCode")
  private String bpcd;

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
   * @return the bId
   */
  public String getbId() {
    return bId;
  }

  /**
   * @param bId
   *          the bId to set
   */
  public void setbId(String bId) {
    this.bId = bId;
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
   * @return the bNm
   */
  public String getbNm() {
    return bNm;
  }

  /**
   * @param bNm
   *          the bNm to set
   */
  public void setbNm(String bNm) {
    this.bNm = bNm;
  }

  /**
   * @return the bpcd
   */
  public String getBpcd() {
    return bpcd;
  }

  /**
   * @param bpcd
   *          the bpcd to set
   */
  public void setBpcd(String bpcd) {
    this.bpcd = bpcd;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "BankInfVo [id=" + id + ", bId=" + bId + ", eId=" + eId + ", bNm=" + bNm + ", bpcd=" + bpcd + "]";
  }

}
