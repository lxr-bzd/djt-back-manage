package com.jr.djt.beans;

import org.apache.ibatis.type.Alias;
/**
 * 用戶數據表
 * @author qiuchen
 *
 */
@Alias("crow")
public class Crow {

	private Integer id;
	
	private Integer uid;//int(11)	 
	private Integer crow;//int(11)	 
	private String sheng;//varchar(713)	 '102组生组合'
	private String pei;//varchar(713)	 '102组配组合'
	private String dui;//varchar(1225)	 '102组兑组合，1：勾，2叉'
	private String gong;//varchar(1225)	 '102组供组合，1：白老，2：白少，3：白男，4：白女，5：红老，6：红少，7：红男，8：红女'
	private String gong_col;//102组供组合颜色，1：白，2：红
	private String count;//varchar(1225)	 '统计'
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCrow() {
		return crow;
	}
	public void setCrow(Integer crow) {
		this.crow = crow;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getPei() {
		return pei;
	}
	public void setPei(String pei) {
		this.pei = pei;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}
	public String getGong() {
		return gong;
	}
	public void setGong(String gong) {
		this.gong = gong;
	}
	public String getGong_col() {
		return gong_col;
	}
	public void setGong_col(String gong_col) {
		this.gong_col = gong_col;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public static void main(String[] args) {
		
	}
	
}
