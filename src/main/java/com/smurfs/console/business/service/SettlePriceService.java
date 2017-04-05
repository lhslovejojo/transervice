package com.smurfs.console.business.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smurfs.console.business.dal.txn.SettlePriceDal;
import com.smurfs.console.business.domain.txn.SettlePrice;
import com.smurfs.console.util.NumberUtil;

@Service
public class SettlePriceService {
	public static Logger log = LoggerFactory.getLogger(SettlePriceService.class);
	@Autowired
	private SettlePriceDal settlePriceDal;

	public String updateSettlePrice(SettlePrice settlePrice) {
		return settlePriceDal.updateSettlePrice(settlePrice);
	}

	public Double getLastSettlePrice(String productCode) {
		String jsonMsg = settlePriceDal.getLastSettlePrice(productCode);
		Map<String, Map<String, String>> map = JSON.parseObject(jsonMsg, HashMap.class);

		if (map != null)
			if (map.values().toArray().length > 0) {
				String settlePriceStr = (String) map.values().toArray()[0];
				return NumberUtil.getDoubleFromStr(settlePriceStr);
			} else {
				return 0d;
			}
		else {
			return 0d;
		}

	}

}
