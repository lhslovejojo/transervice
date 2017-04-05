package com.smurfs.console.business.jbcc;

public class TDBCQueryBean {
	
	private String tableName; //tbc,abc,iact,itx,meta
	private String rowId; //block_id,accountID,TxID
	private String preQulifierFilter; //列名的前置过滤条件:   FUND_
	private String qualifiers;//列名过滤 逗号分隔：   accountID,FUND_RMB,ASSET_CU,....
	private long startTS;     //开始时间
	private long endTS;      //结束时间
	private int version;             //查询版本号，最大值Integer.MAX
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getPreQulifierFilter() {
		return preQulifierFilter;
	}
	public void setPreQulifierFilter(String preQulifierFilter) {
		this.preQulifierFilter = preQulifierFilter;
	}
	public String getQualifiers() {
		return qualifiers;
	}
	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
	}
	public long getStartTS() {
		return startTS;
	}
	public void setStartTS(long startTS) {
		this.startTS = startTS;
	}
	public long getEndTS() {
		return endTS;
	}
	public void setEndTS(long endTS) {
		this.endTS = endTS;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
