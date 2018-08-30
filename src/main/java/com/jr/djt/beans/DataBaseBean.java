package com.jr.djt.beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.type.Alias;

/**
 * 工作基础数据
 * @author qiuchen
 *
 */
@Alias("database")
public class DataBaseBean {
	//数据行数(共162行)
	private Integer d_id;
	//102组,每组6户(1.每组数据用,隔开;2.每组数据直接拼接)
	private String d_data;
	//属于哪一组数据
	private Integer d_tabl_num;
	public Integer getD_tabl_num() {
		return d_tabl_num;
	}
	public void setD_tabl_num(Integer d_tabl_num) {
		this.d_tabl_num = d_tabl_num;
	}
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public String getD_data() {
		return d_data;
	}
	public void setD_data(String d_data) {
		this.d_data = d_data;
	}

	public static void main(String[] args) {
		System.out.print(System.currentTimeMillis());
	
		
	}
}
