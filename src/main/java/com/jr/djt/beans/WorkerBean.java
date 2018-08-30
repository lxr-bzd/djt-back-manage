package com.jr.djt.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 普通用户表
 * @author qiuchen
 *
 */
@Alias("worker")
public class WorkerBean {
	
	private Integer u_id;//编号
	
	private String u_name;//用户名
	
	private String u_password;//密码
	
	private Integer u_islock;//锁定? 1未锁定:2锁定
	
	private Long login_time;//登入时间
	
	private Integer sys_islock;//系統鎖定?1未鎖定:2鎖定
	
	private Integer u_use_table;//使用哪一组工作表
	
	public Integer getU_use_table() {
		return u_use_table;
	}

	public void setU_use_table(Integer u_use_table) {
		this.u_use_table = u_use_table;
	}

	private List<Crow> cr = new ArrayList<>();//连工作表

	public Integer getSys_islock() {
		return sys_islock;
	}

	public void setSys_islock(Integer sys_islock) {
		this.sys_islock = sys_islock;
	}

	public List<Crow> getCr() {
		return cr;
	}

	public void setCr(List<Crow> cr) {
		this.cr = cr;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public Integer getU_islock() {
		return u_islock;
	}

	public void setU_islock(Integer u_islock) {
		this.u_islock = u_islock;
	}

	public Long getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Long login_time) {
		this.login_time = login_time;
	}

	
}
