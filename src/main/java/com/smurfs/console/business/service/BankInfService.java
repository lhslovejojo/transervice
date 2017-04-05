package com.smurfs.console.business.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smurfs.console.business.dal.basic.BankInfDal;
import com.smurfs.console.business.domain.basic.BankInf;

@Service
public class BankInfService {
  public static Logger log = LoggerFactory.getLogger(BankInfService.class);
  @Autowired
  private BankInfDal bankInfDal;

  public List<BankInf> getBankInfListPage(String bId, int page, int rows) {
    return null;
  }

  public BankInf getBankInf(long id) {
    BankInf info = bankInfDal.getBankInfById(id);
    if (info != null) {
      return info;
    } else {
      log.info("can not fetch bank message by id {}", id);
      return null;
    }
  }

  public boolean createBankInf(BankInf info) {
    BankInf exist = getBankInf(info.getId());
    if (exist != null) {
      int num = bankInfDal.modifyBankInf(info);
      return num > 0;
    } else {
      return bankInfDal.createBankInf(info);
    }

  }

}
