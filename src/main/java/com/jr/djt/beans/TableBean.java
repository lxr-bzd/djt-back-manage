package com.jr.djt.beans;

import org.apache.ibatis.type.Alias;

@Alias("dutable")
public class TableBean {
	private Integer dTableId;//id
	private Integer dUseDefault;//是否使用：1使用 2不使用
	private String model;
	public Integer getdTableId() {
		return dTableId;
	}
	public void setdTableId(Integer dTableId) {
		this.dTableId = dTableId;
	}
	public Integer getdUseDefault() {
		return dUseDefault;
	}
	public void setdUseDefault(Integer dUseDefault) {
		this.dUseDefault = dUseDefault;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
