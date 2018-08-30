package com.jr.djt.beans;

import org.apache.ibatis.type.Alias;

/**
 * 后台用户表
 * @author qiuchen
 *
 */
@Alias("s_userData")
public class UserDataBean {
	//用户id
	private Integer u_id;
	/*private Integer dSuId;
	public Integer getdSuId() {
		return dSuId;
	}

	public void setdSuId(Integer dSuId) {
		this.dSuId = dSuId;
	}*/

	//用户名
	private String u_name;
	//用户密码
	private String u_password;
	//對應的管理數據
	private AdminTimeBean atb;
	//時間通知
	private String timeInfo;
	
	public String getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(String timeInfo) {
		this.timeInfo = timeInfo;
	}

	public AdminTimeBean getAtb() {
		return atb;
	}

	public void setAtb(AdminTimeBean atb) {
		this.atb = atb;
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

}
