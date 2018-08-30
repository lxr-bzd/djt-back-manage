package com.jr.djt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jr.djt.beans.UserDataBean;

public interface ISupAdminMapper {
	/**
	 * 獲取不包含interger 的結果
	 * @param integer 
	 * @return
	 */
	List<UserDataBean> getadmin();
	/**
	 * 添加普通管理
	 * @param ud
	 */
	void insertAdmin(UserDataBean ud);
	/**
	 * 根據用戶插入時間表
	 * @param ud
	 */
	void insertAdminTime(UserDataBean ud);
	/**
	 * 根據管理id添加時間
	 * @param adId
	 * @param time
	 */
	void addTime(@Param("adId")Integer adId, @Param("time")Long time);
	/**
	 * 根據id查詢管理的數據
	 * @param adId
	 * @return
	 */
	UserDataBean getadminById(@Param("adId")Integer adId);
	/**
	 * 超管修改普管密碼
	 * @param ad_num
	 * @param new_psw
	 */
	void updateAdminPsw(@Param("adnum")Integer ad_num, @Param("newpsw")String new_psw);
	
}
