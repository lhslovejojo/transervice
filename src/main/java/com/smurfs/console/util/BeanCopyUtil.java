package com.smurfs.console.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

public class BeanCopyUtil {
	public static <T> T cvtDozer(Object infVo, Class<T> clazz) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(infVo, clazz);
	}

	/**
	 * 转成Map对象
	 * 
	 * @param source
	 * @param ignorePropertys
	 *            忽略的属性
	 * @return
	 */
	public static HashMap<String, String> toMap(Object source, List<String> ignorePropertys) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			Map<String, String> map = BeanUtils.describe(source);
			for (String key : map.keySet()) {
				if ("class".equals(key)) // 过滤掉class属性
					continue;
				if (ignorePropertys != null && ignorePropertys.contains(key)) {
					continue;
				}
				// Value不能空，并且 字符串非空，才能加入
				if (map.get(key) != null && StringUtils.isNotEmpty(map.get(key).toString()))
					resultMap.put(key, map.get(key));
				
			}
			if (resultMap.get("accountID")==null)
			{
				resultMap.put("accountID", map.get("memCode"));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}
	/**
	 * 转成Map对象
	 * 
	 * @param source
	 * @param ignorePropertys
	 *            忽略的属性
	 * @return
	 */
	public static HashMap<String, String> toMap(Object source, List<String> ignorePropertys,String accountIdFromFiled) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			Map<String, String> map = BeanUtils.describe(source);
			for (String key : map.keySet()) {
				if ("class".equals(key)) // 过滤掉class属性
					continue;
				if (ignorePropertys != null && ignorePropertys.contains(key)) {
					continue;
				}
				// Value不能空，并且 字符串非空，才能加入
				if (map.get(key) != null && StringUtils.isNotEmpty(map.get(key).toString()))
					resultMap.put(key, map.get(key));
				
			}
			if (resultMap.get("accountID")==null)
			{
				resultMap.put("accountID", map.get(accountIdFromFiled));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}
}
