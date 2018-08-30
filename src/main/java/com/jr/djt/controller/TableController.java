package com.jr.djt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.TableBean;
import com.jr.djt.service.ITableService;

@Controller
@RequestMapping("/usetable")
public class TableController extends BaseController {
	@Autowired
	private ITableService its;
	/**
	 * 查詢所有table表
	 * @return
	 */
	@RequestMapping("getusetable")
	@ResponseBody
	public MessageBean getusetable(HttpServletRequest req){
		List<TableBean> list = its.getusetable();
		if(list!=null){
			return MessageBean.success().add("list", list);
		}
		return MessageBean.fail();
	}
	/**
	 * 根據id修改表的使用情況
	 * @param tableId
	 * @return
	 */
	@RequestMapping("usetableById")
	@ResponseBody
	public MessageBean usetableById(Integer tableId, HttpServletRequest req){
		System.out.println("傳入的id:"+tableId);
		its.usetableById(tableId);
		Object obj = req.getAttribute("time");
		if(obj!=null){
			return MessageBean.timeWarn().add("msg", "您的時間已經不滿"+(long)obj+"小時,為了不耽誤您的使用,請儘快加時間");
		}
		return MessageBean.success();
	}
}
