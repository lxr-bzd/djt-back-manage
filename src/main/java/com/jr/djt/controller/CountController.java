package com.jr.djt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CountController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private void history() {
		
		String uid = null;
		
		jdbcTemplate.queryForList("SELECT a.id,a.focus_row,a.state,b.queue_count FROM game_history a LEFT JOIN game_runing_count b ON a.id=b.hid game_historyWHERE uid=6 ORDER BY a.id", uid);

	}
	
}
