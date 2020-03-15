package com.jr.djt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.WorkerBean;
import com.jr.djt.dao.WorkManageMapper;
import com.jr.djt.service.WorkManageService;
@Service
public class WorkManageServiceImpl implements WorkManageService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private WorkManageMapper wmm;
	@Override
	public List<WorkerBean> getAllWorker() {
		return wmm.selectAllWorker();
	}
	@Override
	public void lockWorker(Integer uid,Integer lock_status) {
		wmm.lockWorkerById(uid,lock_status);
	}
	@Override
	public WorkerBean getWorkerById(Integer uid) {
		// TODO Auto-generated method stub
		return wmm.selectWorkerById(uid);
	}
	@Override
	public void insertUser(WorkerBean worker) {
		System.out.println("進入添加");
		wmm.insertUser(worker);
	}
	@Override
	public void locksys(Integer syslock) {
		wmm.locksys(syslock);
	}
	@Override
	public Boolean getboolByUname(String uname) {
		return wmm.getboolByUname(uname);
	}
	@Override
	public void updateuserpassword(Integer user_num, String new_password,String new_setting_pwd,String name) {
		wmm.updateuserpassword(user_num,new_password,new_setting_pwd,name);
	}
	@Override
	public void deleteworkerByIds(String list) {
		wmm.deleteworkerByIds(list);
	}
	@Override
	public List<WorkerBean> getWorkerByName(String name) {
		return wmm.getWorkerByName(name);
	}
	@Override
	public void deleteOneById(Integer unum) {
		wmm.deleteOneById(unum);
	}
	@Override
	public Map<String, Object> history(String uid) {
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> user = jdbcTemplate.queryForMap("select * from djt_user WHERE djt_u_id = ?",uid);
		if(user==null)throw new RuntimeException("uid="+uid+" 用户不存在");
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from game_history WHERE uid=? ORDER BY createtime DESC",uid);
		
		ret.put("user", user);
		ret.put("history", list);
		return ret;	
		}
	@Override
	public List<Map<String, Object>> workData(String hid) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from game_runing WHERE hid=? ORDER BY row"
				,hid);
		return list;
	}
	@Override
	public List<Map<String, Object>> workCount(String hid) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from game_runing_count WHERE hid=? limit 1"
				,hid);
		return list;
	}
	@Override
	public List<Map<String, Object>> workCounts(String uid) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id,a.focus_row,b.queue,b.queue_count from game_history a LEFT JOIN game_runing_count b ON a.id=b.hid WHERE uid=? ORDER BY a.id DESC"
				,uid);
		return list;
	}
	
}
