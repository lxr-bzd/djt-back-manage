package com.jr.djt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jr.djt.beans.TableBean;
import com.jr.djt.dao.ITableMapper;
import com.jr.djt.service.ITableService;
@Service
public class TableServiceImpl implements ITableService {
	@Autowired
	private ITableMapper itm;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TableBean> getusetable() {
		// TODO Auto-generated method stub
		return itm.getusetable();
	}

	@Override
	public void usetableById(Integer tableId) {
		String sql = "update djt_user set djt_use_table=? where djt_u_id=(select djt_u_id from (select djt_u_id from djt_user where is_su=1) a)";
		jdbcTemplate.update(sql,tableId);
		itm.usetableById(tableId);
		
	}
	
}
