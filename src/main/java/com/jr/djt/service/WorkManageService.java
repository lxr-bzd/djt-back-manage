package com.jr.djt.service;

import java.util.List;
import java.util.Map;

import com.jr.djt.beans.WorkerBean;

public interface WorkManageService {
	/**
	 * 
	 * @return 所有普通工作者的信息
	 */
	List<WorkerBean> getAllWorker();
	/**
	 * 通过id锁定/解锁用户
	 * @param uid
	 * @return
	 */
	void lockWorker(Integer uid,Integer lock_status);
	
	/**
	 * 通过id查询用户详细信息
	 * @param uid
	 * @return
	 */
	WorkerBean getWorkerById(Integer uid);
	/**
	 * 插入新用戶
	 * @param worker
	 */
	void insertUser(WorkerBean worker);
	/**
	 * 修改用戶對應系統狀態
	 * @param syslock
	 */
	void locksys(Integer syslock);
	/**
	 * 根据用户名测试是否存在这个名字的人,存在返回true,否则返回false
	 * @param uname
	 * @return
	 */
	Boolean getboolByUname(String uname);
	/**
	 * 根据用户id修改用户密码
	 * @param user_num
	 * @param new_password
	 */
	void updateuserpassword(Integer user_num, String new_password,String new_setting_pwd,String name);
	/**
	 * 刪除
	 * @param list
	 */
	void deleteworkerByIds(String list);
	/**
	 * 按名字模糊查詢用戶
	 * @param name
	 * @return
	 */
	List<WorkerBean> getWorkerByName(String name);
	/**
	 * 根據id單獨刪除
	 * @param unum
	 */
	void deleteOneById(Integer unum);
	
	
	Map<String, Object> history(String uid);
	
	List<Map<String, Object>> workData(String hid);
	
	
	List<Map<String, Object>> workCount(String hid);
	
	List<Map<String, Object>> workCounts(String uid);
	
}
