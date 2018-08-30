package com.jr.djt.beans;

import java.util.ArrayList;
import java.util.List;

public class DataListBean {
	private List<DataBaseBean> dblist = new ArrayList<>();

	public List<DataBaseBean> getDblist() {
		return dblist;
	}

	public void setDblist(List<DataBaseBean> dblist) {
		this.dblist = dblist;
	}
	
}
