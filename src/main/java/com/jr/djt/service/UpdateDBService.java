package com.jr.djt.service;

import java.util.List;

import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.TableBean;

public interface UpdateDBService {
	/**
	 * 获取所有的生的数据结果集
	 * @return
	 */
	List<DataBaseBean> getAllDB(Integer tableNum);
	/**
	 * 展示相应页码的生的数据
	 * @param pageNum
	 * @return
	 */
	DataBaseBean getDById(Integer pageNum);
	/**
	 * 更新/保存新的数据
	 * @param dbb
	 * @return 
	 */
	void saveData(DataBaseBean dbb);
	/**
	 * 獲取數據總數量
	 * @return
	 */
	int getDataCount();
	/**
	 * 
	 * @return
	 */
	List<TableBean> getableDB();	
}
