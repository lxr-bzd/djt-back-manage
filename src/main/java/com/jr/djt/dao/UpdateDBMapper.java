package com.jr.djt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.TableBean;

public interface UpdateDBMapper {
	/**
	 * 获取所有生的数据的结果集
	 * @return
	 */
	List<DataBaseBean> selectAllDB(@Param("tableNum")Integer tableNum);
	/**
	 * 展示相应页码的生的数据
	 * @param pageNum
	 * @return
	 */
	DataBaseBean selectDById(@Param("pageNum")Integer pageNum);
	/**
	 * 保存/修改新数据
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
	 * 獲得表結果集
	 * @return
	 */
	List<TableBean> getableDB();
	
	
	void update(@Param("tableNum")Integer tb,@Param("list")List list);

}
