package com.jr.djt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.dao.StartupListenerMapper;
import com.jr.djt.dao.WorkManageMapper;
import com.jr.djt.service.IStartupListenerService;

@Service
public class StartupListenerService {
	@Autowired
	private StartupListenerMapper slm;
	@Autowired
	private WorkManageMapper wmm;
	public void test(){
		System.out.println("时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}


	public AdminTimeBean getAdminTimeById(Integer adId) {
		// TODO Auto-generated method stub
		System.out.print("获取");
		test();
		return slm.getAdminTimeById(adId);
	}


	public void updateAdminTimeById(AdminTimeBean adb) {
		// TODO Auto-generated method stub
		slm.updateAdminTimeById(adb);
		wmm.locksys(adb.getT_isclock());
		System.out.print("修改结束");
		test();
	}
}
