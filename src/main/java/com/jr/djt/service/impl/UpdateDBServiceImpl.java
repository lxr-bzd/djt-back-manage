package com.jr.djt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.TableBean;
import com.jr.djt.dao.UpdateDBMapper;
import com.jr.djt.service.UpdateDBService;
@Service
public class UpdateDBServiceImpl implements UpdateDBService {
	@Autowired
	private UpdateDBMapper udbm;
	@Override
	public List<DataBaseBean> getAllDB(Integer tableNum) {
		// TODO Auto-generated method stub
		return udbm.selectAllDB(tableNum);
	}
	@Override
	public DataBaseBean getDById(Integer pageNum) {
		// TODO Auto-generated method stub
		return udbm.selectDById(pageNum);
	}
	@Override
	public void saveData(DataBaseBean dbb) {
		// TODO Auto-generated method stub
		udbm.saveData(dbb);
	}
	@Override
	public int getDataCount() {
		// TODO Auto-generated method stub
		return udbm.getDataCount();
	}
	@Override
	public List<TableBean> getableDB() {
		// TODO Auto-generated method stub
		return udbm.getableDB();
	}
	@Override
	public void update(Integer num, List list) {
		
		udbm.update(num, list);
	}
	
}
