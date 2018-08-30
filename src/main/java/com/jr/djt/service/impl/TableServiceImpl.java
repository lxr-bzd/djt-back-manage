package com.jr.djt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.TableBean;
import com.jr.djt.dao.ITableMapper;
import com.jr.djt.service.ITableService;
@Service
public class TableServiceImpl implements ITableService {
	@Autowired
	private ITableMapper itm;
	
	@Override
	public List<TableBean> getusetable() {
		// TODO Auto-generated method stub
		return itm.getusetable();
	}

	@Override
	public void usetableById(Integer tableId) {
		// TODO Auto-generated method stub
		itm.usetableById(tableId);
	}
	
}
