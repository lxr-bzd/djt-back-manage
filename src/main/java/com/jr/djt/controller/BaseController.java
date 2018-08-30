package com.jr.djt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jr.djt.beans.MessageBean;

public class BaseController {
	
	private final static Logger Log = LoggerFactory.getLogger(BaseController.class);
	@ExceptionHandler
	@ResponseBody
	public MessageBean exceptionHandle(Exception e){
		Log.error("严重异常", e);
		return MessageBean.fail().add("msg", "操作错误");
	}
}
