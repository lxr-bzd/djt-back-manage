package com.jr.djt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jr.djt.beans.WorkerBean;

public interface WorkManageMapper {
	/**
	 * 
	 * @return 所有用户结果集
	 */
	List<WorkerBean> selectAllWorker();
	/**
	 * 通过id锁定用户
	 * @param uid
	 */
	void lockWorkerById(@Param("uid")Integer uid,@Param("lock_status")Integer lock_status);
	/**
	 * 通过id查询用户详细信息
	 * @param uid
	 * @return
	 */
	WorkerBean selectWorkerById(@Param("uid")Integer uid);
	/**
	 * 插入新用戶
	 * @param worker
	 */
	void insertUser(WorkerBean worker);
	/**
	 * 修改用戶對應的系統狀態
	 * @param syslock
	 */
	void locksys(@Param("syslock")Integer syslock);
	/**
	 * 根据用户名测试是否存在这个名字的人,存在返回true,否则返回false
	 * @param uname
	 * @return
	 */
	Boolean getboolByUname(String uname);
	/**
	 * 根据id修改用户密码
	 * @param user_num
	 * @param new_password
	 */
	void updateuserpassword(@Param("user_num")Integer user_num, @Param("new_password")String new_password,@Param("name")String name);
	/**
	 * 刪除
	 * @param list
	 */
	void deleteworkerByIds(@Param("list")String list);
	/**
	 * 按名字模糊查詢用戶
	 * @param name
	 * @return
	 */
	List<WorkerBean> getWorkerByName(@Param("name")String name);
	/**
	 * 根據id單個刪除
	 * @param unum
	 */
	void deleteOneById(Integer unum);
}
