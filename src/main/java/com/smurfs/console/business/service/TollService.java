package com.smurfs.console.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smurfs.console.business.dal.txn.TollDal;
import com.smurfs.console.business.domain.txn.Toll;

@Service
public class TollService {
	public static Logger log = LoggerFactory.getLogger(TollService.class);
	@Autowired
	private TollDal tollDal;

	public String createToll(Toll toll) {
		return tollDal.createToll(toll);
	}

}
