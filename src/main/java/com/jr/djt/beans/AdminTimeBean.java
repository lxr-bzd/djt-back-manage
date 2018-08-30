package com.jr.djt.beans;

import org.apache.ibatis.type.Alias;

@Alias("admintime")
public class AdminTimeBean {
	private Integer t_id; //id
	private Integer t_admin_id;//对应管理id
	private long t_val;//给予的时间
	private long t_c_val;//花费的事件
	private int t_isclock;//是否锁定  1：未锁定  2：锁定
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public Integer getT_admin_id() {
		return t_admin_id;
	}
	public void setT_admin_id(Integer t_admin_id) {
		this.t_admin_id = t_admin_id;
	}
	public long getT_val() {
		return t_val;
	}
	public void setT_val(long t_val) {
		this.t_val = t_val;
	}
	public long getT_c_val() {
		return t_c_val;
	}
	public void setT_c_val(long t_c_val) {
		this.t_c_val = t_c_val;
	}
	public int getT_isclock() {
		return t_isclock=t_val>t_c_val?1:2;
	}
	public void setT_isclock(int t_isclock) {
		this.t_isclock = t_isclock;
	}
	
}
