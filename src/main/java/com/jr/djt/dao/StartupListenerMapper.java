package com.jr.djt.dao;

import org.apache.ibatis.annotations.Param;

import com.jr.djt.beans.AdminTimeBean;

public interface StartupListenerMapper {
	/**
	 * 通过id查找管理的时间对象
	 * @param adId
	 * @return
	 */
	AdminTimeBean getAdminTimeById(@Param("adId")Integer adId);
	/**
	 * 修改管理时间或修改状态
	 * @param adb
	 */
	void updateAdminTimeById(AdminTimeBean adb);

}
