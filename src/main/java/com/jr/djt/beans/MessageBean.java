package com.jr.djt.beans;
/**
 * 通用的返回类
 * @author qiuchen
 *
 */

import java.util.HashMap;
import java.util.Map;

public class MessageBean {
	//状态码  100-成功,時間充足  200-成功,時間不足一天  300-失败
	private int code;
	//提示信息
	private String message;
	//用户要返回给浏览器的数据
	private Map<String, Object> map = new HashMap<String,Object>();

	//定义个最快捷的成功方法,時間充裕
	public static MessageBean success(){
		MessageBean result = new MessageBean();
		result.setCode(100);
		result.setMessage("处理成功");
		return result;
	}
	//定义个最快捷的成功方法,時間不充裕
	public static MessageBean timeWarn(){
		MessageBean result = new MessageBean();
		result.setCode(200);
		result.setMessage("处理成功");
		return result;
	}

	//定义个最快捷的失败方法
	public static MessageBean fail(){
		MessageBean result = new MessageBean();
		result.setCode(300);
		result.setMessage("处理失败");
		return result;
	}
	//定义个最快捷的失败方法
	public static MessageBean fail(String msg){
			MessageBean result = new MessageBean();
			result.setCode(300);
			result.setMessage(msg);
			return result;
		}
	
	//一个快捷的方法添加用户要的并返回给浏览器的数据
	public MessageBean add(String key, Object val){
		this.getMap().put(key, val);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
