package com.smurfs.console.business.jbcc;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author dyq
 * 2017.03.26
 * 为查询原始交易、原子交易分页封装的bean
 */

public class BusinessReturnResultBean<T> implements Serializable {
	
	private static final long serialVersionUID = 4695627546411078831L;
	int totalCount;            //总记录数
	int pageCount;             //总页数
	int pageSize;              //每页记录数量 
	int pageIndex;             //当前页
	T   data;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
