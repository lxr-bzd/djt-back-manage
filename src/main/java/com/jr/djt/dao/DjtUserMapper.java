package com.jr.djt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jr.djt.beans.WorkerBean;
import com.jr.djt.beans.UserDataBean;
/**
 * 后台用户访问层接口
 * @author qiuchen
 *
 */
public interface DjtUserMapper {
	/**
	 * 登入验证
	 * @param user
	 * @return 后台用户信息
	 */
	UserDataBean selectUser(UserDataBean user);
	/**
	 * 查询所有普通用户
	 * @return 所有普通用户结果集
	 */
	List<WorkerBean> selectAllUser();
	/**
	 * 根據id修改密碼
	 * @param u_id
	 * @param newpsw
	 */
	void updagePasswordAdmin(@Param("uid")Integer u_id, @Param("newpsw")String newpsw);
	
}
