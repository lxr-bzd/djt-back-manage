package com.jr.djt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.DataListBean;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.TableBean;
import com.jr.djt.service.UpdateDBService;
/**
 * 修改基础数据控制类，對保存數據進行更新方法
 * @author qiuchen
 *
 */
@Controller
@RequestMapping("/new")
public class UpDataController extends UpdateDBController {
	@Autowired
	private  UpdateDBService udbs;
	/**
	 * 保存數據
	 * @param dblist
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws Exception 
	 */
	@RequestMapping(value="savedata_new",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean saveData(HttpServletRequest request, @RequestBody String myDomain) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();  
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, DataBaseBean.class);  
		//獲取前端傳進來的對象集
		List<DataBaseBean> list = objectMapper.readValue(myDomain, javaType); 
		System.out.println("屬於第:"+list.get(0).getD_tabl_num()+"張表");
		//初始化錯誤的結果
		int error = 0;
		//設置正則
		String regEx = "1|2|3|4";
		Pattern pattern = Pattern.compile(regEx);
		//遍曆對象集
		for (DataBaseBean dataBaseBean : list) {
			//設置一個保存點
			boolean flag = true;
			//判斷對象中的數據是否符合保存機制
			String d_data = dataBaseBean.getD_data();
			if("".equals(d_data) || d_data==null){
				//不符合保存機制，錯誤結果加一次，結束檔次循環，開始下一次循環，當錯誤為3次後返回錯誤信息，結束保存
				error++;
				if(error==3){
					return MessageBean.fail().add("msg", "請至少輸入一組完整數據");
				}
				continue;
			}
			//拆分對象數據檢查數據內容
			String[] data_arr = d_data.split(",");
			if(data_arr.length!=162){
				//內容長度不符合要求，錯誤結果加一次，結束這次循環，開始下一次循環
				error++;
				continue;
			}
			//對象數據通過兩道檢查，繼續新檢查,如果數據長度或者數據規則不正確，保存點改變值
			aa:for (String str : data_arr) {
				char[] str_arr = str.toCharArray();
				if(str_arr.length==6){
					//數據符合長度值,進入正則檢測
					for (char c : str_arr) {
						String ctr = String.valueOf(c);
						Matcher matcher = pattern.matcher(ctr);
						if(!matcher.matches()){
							//正則檢測錯誤，錯誤結果加一次，結束aa循環
							error++;
							flag = false;
							break aa;
						}
					}
				}else{
					//數據不符合長度，錯誤結果加一次，結束本循環
					error++;
					flag = false;
					break;
				}
			}
			if(flag){
				//正則檢測正確，保存修改當前對象信息
				udbs.saveData(dataBaseBean);
			}
		}
		int save_num = 3-error;
		return MessageBean.success().add("msg", save_num);
	}
	
	
	@RequestMapping(value="saveData2",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean saveData2(HttpServletRequest request,Integer tableNum,Integer grp, String data) throws Exception{
		
		if(data==null||"".equals(data))
			throw new RuntimeException("错误参数");
		
		udbs.update(tableNum, grp,data);
		
		return MessageBean.success().add("msg", "");
		
	}
	
	
	
	/**
	 * 分頁展示數據
	 * @return
	 */
	@RequestMapping("findAll")
	@ResponseBody
	public MessageBean getAllDB2(Integer tableNum,String grp){
		
		
		return MessageBean.success().add("tb", udbs.getAll(tableNum,grp));
	}
	/**
	 * 查生表所有張數
	 * @return
	 */
	@RequestMapping("getable")
	@ResponseBody
	public MessageBean getableDB(){
		List<TableBean> list = udbs.getableDB();
		return MessageBean.success().add("table", list);
	}
}
