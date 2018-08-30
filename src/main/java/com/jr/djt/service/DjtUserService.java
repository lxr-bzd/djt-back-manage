package com.jr.djt.service;

import java.util.List;

import com.jr.djt.beans.WorkerBean;
import com.jr.djt.beans.UserDataBean;
/**
 * 后台用户业务层接口
 * @author qiuchen
 *
 */
public interface DjtUserService {
	/**
	 * 登入验证
	 * @param user
	 * @return 登入用户信息
	 */
	UserDataBean getUser(UserDataBean user);
	/**
	 * 查询所有普通用户信息
	 * @return
	 */
	List<WorkerBean> getAllUser();
	/**
	 * 修改密碼
	 * @param u_id
	 * @param newpsw
	 */
	void updagePasswordAdmin(Integer u_id, String newpsw);
	
	
}
