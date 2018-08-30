package com.jr.djt.service;

import com.jr.djt.beans.AdminTimeBean;

public interface IStartupListenerService {
	/**
	 * 通过id获取管理时间对象
	 * @param adId 传入id
	 * @return
	 */
	AdminTimeBean getAdminTimeById(Integer adId);
	/**
	 * 修改管理的花费时间或者锁定管理
	 * @param adId
	 */
	void updateAdminTimeById(AdminTimeBean adb);
}
