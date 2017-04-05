package com.smurfs.console.business.dal.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("stuDal")
public class StuDal {
  public static Logger log = LoggerFactory.getLogger(StuDal.class);
  
  private String ip;
 
  private String path;

  public boolean creteStu (int stuId, String name) {
   return true;
  }
  
  public String getName (int stuId) {
    return "";
  }

}
