package com.jr.djt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.WorkerBean;
import com.jr.djt.dao.WorkManageMapper;
import com.jr.djt.service.WorkManageService;
@Service
public class WorkManageServiceImpl implements WorkManageService {
	@Autowired
	private WorkManageMapper wmm;
	@Override
	public List<WorkerBean> getAllWorker() {
		// TODO Auto-generated method stub
		return wmm.selectAllWorker();
	}
	@Override
	public void lockWorker(Integer uid,Integer lock_status) {
		// TODO Auto-generated method stub
		wmm.lockWorkerById(uid,lock_status);
	}
	@Override
	public WorkerBean getWorkerById(Integer uid) {
		// TODO Auto-generated method stub
		return wmm.selectWorkerById(uid);
	}
	@Override
	public void insertUser(WorkerBean worker) {
		// TODO Auto-generated method stub
		System.out.println("進入添加");
		wmm.insertUser(worker);
	}
	@Override
	public void locksys(Integer syslock) {
		// TODO Auto-generated method stub
		wmm.locksys(syslock);
	}
	@Override
	public Boolean getboolByUname(String uname) {
		// TODO Auto-generated method stub
		return wmm.getboolByUname(uname);
	}
	@Override
	public void updateuserpassword(Integer user_num, String new_password) {
		// TODO Auto-generated method stub
		wmm.updateuserpassword(user_num,new_password);
	}
	@Override
	public void deleteworkerByIds(String list) {
		// TODO Auto-generated method stub
		wmm.deleteworkerByIds(list);
	}
	@Override
	public List<WorkerBean> getWorkerByName(String name) {
		// TODO Auto-generated method stub
		return wmm.getWorkerByName(name);
	}
	@Override
	public void deleteOneById(Integer unum) {
		// TODO Auto-generated method stub
		wmm.deleteOneById(unum);
	}
	
}
