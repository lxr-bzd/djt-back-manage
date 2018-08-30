package com.jr.djt.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.service.UpdateDBService;

/**
 * 修改基础数据控制类
 * @author qiuchen
 *
 */
@Controller
public class UpdateDBController extends BaseController {
	@Autowired
	private UpdateDBService udbs;
	/**
	 * 去數據預覽頁面
	 * @return
	 */
	@RequestMapping("/datapage_preview")
	public String toPreview(){
		return "admin/backstage_table";
	}
	/**
	 * 獲取數據總數量
	 * @return
	 */
	@RequestMapping("/getDataCount")
	@ResponseBody
	public MessageBean getDataCount(){
		int data_Count = udbs.getDataCount();
		if(data_Count>34)
			data_Count = 34;
		return MessageBean.success().add("data_count", data_Count);
	}
	/**
	 * 去修改數據表頁面
	 * @return 返回修改數據頁面
	 */
	@RequestMapping("/to_up_datapage")
	public String to_Up_DataPage(){
		return "admin/basic_data";
	}
	/**
	 * 保存更新信息
	 * @param dbb
	 * @return
	 */
	@RequestMapping("/saveData")
	@ResponseBody
	public MessageBean saveData(DataBaseBean dbb){
		String[] split = dbb.getD_data().split("-");
		boolean flag = true;
		if(split.length==3){
			//存储了3组的数据
			for (String string : split) {
				String[] split2 = string.split(",");
				if(split2.length==3){
					//有3行数据
					for (String string2 : split2) {
						char[] charArray = string2.toCharArray();
						if(charArray.length==6){
							//正则判断字符串中是否有特殊字符或者不在范围内的字符
							String regEx = "1|2|3|4";
							Pattern pattern = Pattern.compile(regEx);
							for (char c : charArray) {
								String ctr = String.valueOf(c);
								Matcher matcher = pattern.matcher(ctr);
								if(!matcher.matches()){
									//有非法字符或者数字
									flag = false;
									//跳出当前循环
									break;
								}
							}
						}else{
							flag = false;
							break;
						}
						if(!flag){
							break;
						}
					}
				}else{
					//没有3行数据
					flag = false;
				}
				if(!flag){
					break;
				}
			}
		}else{
			//没有存储3组数据
			flag = false;
		}
		//判断最后的到的条件
		if(flag){
			//满足条件
			udbs.saveData(dbb);
			return MessageBean.success();
		}
		//不满足条件
		return MessageBean.fail().add("msg", "您填写的信息有误");
	}

	/**
	 * 展示相应页码的生的数据
	 * @return
	 */
	@RequestMapping("/selectData")
	@ResponseBody
	public MessageBean getAllDB(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,Integer tableNum){
		System.out.println("传来的页面是:"+pageNum);
		DataBaseBean dbb = udbs.getDById(pageNum);
		if(dbb!=null){
			return MessageBean.success().add("data", dbb);
		}
		return MessageBean.fail();
	}
}