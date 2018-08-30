package com.jr.djt.service;

import java.util.List;

import com.jr.djt.beans.TableBean;

public interface ITableService {
	/**
	 * 查詢所有table表
	 * @return
	 */
	List<TableBean> getusetable();
	/**
	 * 根據id修改哪張表被使用
	 * @param tableId
	 */
	void usetableById(Integer tableId);

}
