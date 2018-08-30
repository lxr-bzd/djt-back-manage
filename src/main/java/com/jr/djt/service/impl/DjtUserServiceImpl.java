package com.jr.djt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.WorkerBean;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.dao.DjtUserMapper;
import com.jr.djt.service.DjtUserService;
@Service
public class DjtUserServiceImpl implements DjtUserService {
	@Autowired
	private DjtUserMapper userMapper;
	
	@Override
	public UserDataBean getUser(UserDataBean user) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(user);
	}
	@Override
	public List<WorkerBean> getAllUser() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUser();
	}
	@Override
	public void updagePasswordAdmin(Integer u_id, String newpsw) {
		// TODO Auto-generated method stub
		userMapper.updagePasswordAdmin(u_id,newpsw);
	}
}
