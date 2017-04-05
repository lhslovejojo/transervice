package com.smurfs.console.business.domain.txn;

import java.io.Serializable;
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
  private Double orderPrice;
  /** 持仓价格 */
  private Double holdPrice;
  /** 成交数量 */
  private Double orderQuantity;
  /** 剩余数量 */
  private Double leftQuantity;
  /** 数量单位 */
  private String presentUnit;
  /** 定金率 */
  private Double depositRate;
  /** 定金率是否比率(0-固定 1-比率) */
  private String depositRatioType;
  /** 定金收取方式(0-开仓价 1-持仓价) */
  private String depositType;
  /** 定金金额或履约准备金 */
  private Double depositBalance;
  /** 手续费 */
  private Double openPoundage;
  /**滞纳金*/
  private Double delayFees;
  /** 平仓盈亏 */
  private Double squareLoss;
  /** 结算盈亏 */
  private Double settleLoss;
  /**结算价格*/
  private Double settlePrice;
  /** 建仓单号 */
  private String depotOrderNo;
  /** 说明，备注 */
  private String requestDesc;

  /**
   * @return the requestTime
   */
  public Date getRequestTime() {
    return requestTime;
  }

  /**
   * @param requestTime
   *          the requestTime to set
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
   *          the requestId to set
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
   *          the busiNo to set
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
   *          the exchangeId to set
   */
  public void setExchangeId(String exchangeId) {
    this.exchangeId = exchangeId;
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
   * @return the openTradeAccount
   */
  public String getOpenTradeAccount() {
    return openTradeAccount;
  }

  /**
   * @param openTradeAccount
   *          the openTradeAccount to set
   */
  public void setOpenTradeAccount(String openTradeAccount) {
    this.openTradeAccount = openTradeAccount;
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
   * @return the productCategoryId
   */
  public String getProductCategoryId() {
    return productCategoryId;
  }

  /**
   * @param productCategoryId
   *          the productCategoryId to set
   */
  public void setProductCategoryId(String productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  /**
   * @return the tradeType
   */
  public String getTradeType() {
    return tradeType;
  }

  /**
   * @param tradeType
   *          the tradeType to set
   */
  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
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
   * @return the tradeDir
   */
  public String getTradeDir() {
    return tradeDir;
  }

  /**
   * @param tradeDir
   *          the tradeDir to set
   */
  public void setTradeDir(String tradeDir) {
    this.tradeDir = tradeDir;
  }

  /**
   * @return the dealType
   */
  public String getDealType() {
    return dealType;
  }

  /**
   * @param dealType
   *          the dealType to set
   */
  public void setDealType(String dealType) {
    this.dealType = dealType;
  }

  /**
   * @return the oppDealType
   */
  public String getOppDealType() {
    return oppDealType;
  }

  /**
   * @param oppDealType
   *          the oppDealType to set
   */
  public void setOppDealType(String oppDealType) {
    this.oppDealType = oppDealType;
  }

  /**
   * @return the depositWay
   */
  public String getDepositWay() {
    return depositWay;
  }

  /**
   * @param depositWay
   *          the depositWay to set
   */
  public void setDepositWay(String depositWay) {
    this.depositWay = depositWay;
  }

 
 

  /**
   * @return the depositRate
   */
  public Double getDepositRate() {
    return depositRate;
  }

  /**
   * @param depositRate
   *          the depositRate to set
   */
  public void setDepositRate(Double depositRate) {
    this.depositRate = depositRate;
  }

  /**
   * @return the depositRatioType
   */
  public String getDepositRatioType() {
    return depositRatioType;
  }

  /**
   * @param depositRatioType
   *          the depositRatioType to set
   */
  public void setDepositRatioType(String depositRatioType) {
    this.depositRatioType = depositRatioType;
  }

  /**
   * @return the depositType
   */
  public String getDepositType() {
    return depositType;
  }

  /**
   * @param depositType
   *          the depositType to set
   */
  public void setDepositType(String depositType) {
    this.depositType = depositType;
  }


  /**
   * @return the depotOrderNo
   */
  public String getDepotOrderNo() {
    return depotOrderNo;
  }

  /**
   * @param depotOrderNo
   *          the depotOrderNo to set
   */
  public void setDepotOrderNo(String depotOrderNo) {
    this.depotOrderNo = depotOrderNo;
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

public String getPresentUnit() {
	return presentUnit;
}

public void setPresentUnit(String presentUnit) {
	this.presentUnit = presentUnit;
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

public Double getDelayFees() {
	return delayFees;
}

public void setDelayFees(Double delayFees) {
	this.delayFees = delayFees;
}

public Double getSquareLoss() {
	return squareLoss;
}

public void setSquareLoss(Double squareLoss) {
	this.squareLoss = squareLoss;
}

public Double getSettleLoss() {
	return settleLoss;
}

public void setSettleLoss(Double settleLoss) {
	this.settleLoss = settleLoss;
}

public Double getSettlePrice() {
	return settlePrice;
}

public void setSettlePrice(Double settlePrice) {
	this.settlePrice = settlePrice;
}

@Override
public String toString() {
	return "PositionRequest [requestTime=" + requestTime + ", requestId=" + requestId + ", BusiNo=" + BusiNo
			+ ", exchangeId=" + exchangeId + ", memCode=" + memCode + ", openTradeAccount=" + openTradeAccount
			+ ", productCode=" + productCode + ", productCategoryId=" + productCategoryId + ", tradeType=" + tradeType
			+ ", busiDate=" + busiDate + ", busiTime=" + busiTime + ", tradeDir=" + tradeDir + ", dealType=" + dealType
			+ ", oppDealType=" + oppDealType + ", depositWay=" + depositWay + ", orderPrice=" + orderPrice
			+ ", holdPrice=" + holdPrice + ", orderQuantity=" + orderQuantity + ", leftQuantity=" + leftQuantity
			+ ", presentUnit=" + presentUnit + ", depositRate=" + depositRate + ", depositRatioType=" + depositRatioType
			+ ", depositType=" + depositType + ", depositBalance=" + depositBalance + ", openPoundage=" + openPoundage
			+ ", delayFees=" + delayFees + ", squareLoss=" + squareLoss + ", settleLoss=" + settleLoss
			+ ", settlePrice=" + settlePrice + ", depotOrderNo=" + depotOrderNo + ", requestDesc=" + requestDesc + "]";
}

public Double getLeftQuantity() {
	return leftQuantity;
}

public void setLeftQuantity(Double leftQuantity) {
	this.leftQuantity = leftQuantity;
}

}
