package com.smurfs.console.business.jbcc;

import java.util.List;
import java.util.Map;

public class TDBCScanBean {
	
	private String tableName; //tbc,abc,iact,itx,meta
	private Map<String,String> qualifierMap; //模糊查询过来条件
	private List<String> returnQualifierList;//列名过滤 逗号分隔：   accountID,FUND_RMB,ASSET_CU,....
	private int pageSize;
	private int pageIndex;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, String> getQualifierMap() {
		return qualifierMap;
	}
	public void setQualifierMap(Map<String, String> qualifierMap) {
		this.qualifierMap = qualifierMap;
	}
	public List<String> getReturnQualifierList() {
		return returnQualifierList;
	}
	public void setReturnQualifierList(List<String> returnQualifierList) {
		this.returnQualifierList = returnQualifierList;
	}
	
	
	
}
