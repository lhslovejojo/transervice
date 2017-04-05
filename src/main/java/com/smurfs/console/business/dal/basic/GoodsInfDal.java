package com.smurfs.console.business.dal.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.smurfs.console.business.domain.basic.GoodsInf;


@Service("goodsInfDal")
public class GoodsInfDal {
  public static Logger log = LoggerFactory.getLogger(GoodsInfDal.class);

  private String ip;
  
  private String path;

  public GoodsInf getGoodsInfById(long id) {
    return null;
  }

  public boolean createGoodsInf(GoodsInf info) {
    return false;
  }

  public int modifyGoodsInf(GoodsInf info) {
    return 0;
  }

}
