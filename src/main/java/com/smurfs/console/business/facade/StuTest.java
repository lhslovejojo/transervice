package com.smurfs.console.business.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blockchain.service.StudentAdd;
import com.smurfs.console.business.dal.test.StuDal;
import com.smurfs.console.business.service.UserInfService;

@Service
public class StuTest implements StudentAdd {
  @Autowired
  private StuDal stuDal;
  
  @Autowired
  private UserInfService userInfService;
  
  @Override
  public String process(String arg0, String arg1) {
    System.out.println(arg0);
    System.out.println(arg1);
    boolean success = stuDal.creteStu(Integer.parseInt(arg0), arg1);
    System.out.println(userInfService);
    return String.valueOf(success);
  }

  @Override
  public String getName(String studid) {
    return stuDal.getName(Integer.parseInt(studid));
  }
  
}