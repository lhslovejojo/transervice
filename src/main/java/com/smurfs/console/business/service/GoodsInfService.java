package com.smurfs.console.business.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smurfs.console.business.dal.basic.GoodsInfDal;
import com.smurfs.console.business.domain.basic.GoodsInf;

@Service
public class GoodsInfService {
  public static Logger log = LoggerFactory.getLogger(GoodsInfService.class);
  @Autowired
  private GoodsInfDal goodsInfDal;

  public List<GoodsInf> getGoodsInfListPage(String bId, int page, int rows) {
    return null;
  }

  public GoodsInf getGoodsInf(long id) {
    GoodsInf info = goodsInfDal.getGoodsInfById(id);
    if (info != null) {
      return info;
    } else {
      log.info("can not fetch bank message by id {}", id);
      return null;
    }
  }

  public boolean createGoodsInf(GoodsInf info) {
    GoodsInf exist = getGoodsInf(info.getId());
    if (exist != null) {
      int num = goodsInfDal.modifyGoodsInf(info);
      return num > 0;
    } else {
      return goodsInfDal.createGoodsInf(info);
    }

  }

}
