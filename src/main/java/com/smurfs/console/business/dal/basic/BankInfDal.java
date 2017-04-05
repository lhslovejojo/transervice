package com.smurfs.console.business.dal.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smurfs.console.business.domain.basic.BankInf;


@Service("bankInfDal")
public class BankInfDal {
  public static Logger log = LoggerFactory.getLogger(BankInfDal.class);

  public BankInf getBankInfById(long id) {
    return null;
  }

  public boolean createBankInf(BankInf info) {
    return false;
  }

  public int modifyBankInf(BankInf info) {
    return 0;
  }

}
