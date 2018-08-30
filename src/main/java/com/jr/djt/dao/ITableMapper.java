package com.jr.djt.dao;

import java.util.List;

import com.jr.djt.beans.TableBean;

public interface ITableMapper {
	/**
	 * 查詢所有table表
	 * @return
	 */
	List<TableBean> getusetable();
	/**
	 * 根據id修改表的使用狀態
	 * @param tableId
	 */
	void usetableById(Integer tableId);
	
}
