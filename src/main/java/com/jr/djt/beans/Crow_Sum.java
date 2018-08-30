package com.jr.djt.beans;

public class Crow_Sum extends Crow {
	private Integer id;
	
	private Integer uid;//int(11)	 
	private Integer crow;//int(11)	 
	private String sheng;//varchar(713)	 '102组生组合'
	private String pei;//varchar(713)	 '102组配组合'
	private String dui;//varchar(1225)	 '102组兑组合，1：勾，2叉'
	private String gong;//varchar(1225)	 '102组供组合，1：白老，2：白少，3：白男，4：白女，5：红老，6：红少，7：红男，8：红女'
	private String gong_col;//102组供组合颜色，1：白，2：红
	private String count;//varchar(1225)	 '统计'
	private String count_sum;//统计和
	private String actual_sum;//实计和
	public String getCount_sum() {
		return count_sum;
	}
	public void setCount_sum(String count_sum) {
		this.count_sum = count_sum;
	}
	public String getActual_sum() {
		return actual_sum;
	}
	public void setActual_sum(String actual_sum) {
		this.actual_sum = actual_sum;
	}
	public static void main(String[] args) {
		Integer i = 10;
		Long j = 11l;
		System.out.println(i+j);
	}
}
