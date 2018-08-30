package com.jr.djt.service;

import java.util.List;

import com.jr.djt.beans.UserDataBean;

public interface ISupAdminService {
	/**
	 * 獲取管理集
	 * @param integer 不包含的結果集
	 * @return 結果集
	 */
	List<UserDataBean> getadmin();
	/**
	 * 添加普通管理
	 * @param ud
	 */
	void insertAdmin(UserDataBean ud);
	/**
	 * 根據普通管理的id添加時間
	 * @param adId 管理的id
	 * @param time 添加的時間
	 */
	void addTime(Integer adId, Long time);
	/**
	 * 根據id查詢管理員信息
	 * @param adId
	 * @return
	 */
	UserDataBean getadminById(Integer adId);
	/**
	 * 插入管理的时间信息
	 * @param ud
	 */
	void insertAdminTime(UserDataBean ud);
	/**
	 * 超管修改普管密碼
	 * @param ad_num
	 * @param new_psw
	 */
	void updateAdminPsw(Integer ad_num, String new_psw);
	
}
