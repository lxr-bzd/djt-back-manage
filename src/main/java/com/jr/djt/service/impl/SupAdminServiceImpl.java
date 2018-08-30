package com.jr.djt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.dao.ISupAdminMapper;
import com.jr.djt.service.ISupAdminService;
@Service
public class SupAdminServiceImpl implements ISupAdminService {
	@Autowired
	private ISupAdminMapper isam;
	@Override
	public List<UserDataBean> getadmin() {
		// TODO Auto-generated method stub
		return isam.getadmin();
	}
	@Override
	public void insertAdmin(UserDataBean ud) {
		// TODO Auto-generated method stub
		isam.insertAdmin(ud);
	}
	@Override
	public void addTime(Integer adId, Long time) {
		// TODO Auto-generated method stub
		isam.addTime(adId,time);
	}
	@Override
	public UserDataBean getadminById(Integer adId) {
		// TODO Auto-generated method stub
		return isam.getadminById(adId);
	}
	@Override
	public void insertAdminTime(UserDataBean ud) {
		// TODO Auto-generated method stub
		isam.insertAdminTime(ud);
	}
	@Override
	public void updateAdminPsw(Integer ad_num, String new_psw) {
		// TODO Auto-generated method stub
		isam.updateAdminPsw(ad_num, new_psw);
	}
	
}
