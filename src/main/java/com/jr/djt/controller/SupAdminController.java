package com.jr.djt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.constant.INum_Enum;
import com.jr.djt.constant.IStr_Enum;
import com.jr.djt.service.ISupAdminService;

/**
 * 超級管理
 * 該命名空間需要被攔截器攔截驗證身份
 * @author qiuchen
 *
 */
@Controller
@RequestMapping(IStr_Enum.ISUP_NAMESPACE)
public class SupAdminController extends BaseController {
	@Autowired
	private ISupAdminService isas;
	@RequestMapping(IStr_Enum.ISUP_LOGIN_PAGE)
	public String toSupPage(){
		return IStr_Enum.ISUP_PAGE;
	}
	/**
	 * 獲取所有的普通管理數據集
	 * @param pn 頁數
	 * @return 當頁的普通管理信息
	 */
	@RequestMapping("getadmin")
	@ResponseBody
	public MessageBean getadmin(@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageHelper.startPage(pn,INum_Enum.IAD_PAGE_SIZE);//制定分頁規則，當前顯示第幾頁pn ，每頁顯示多少條
		List<UserDataBean> ad_list = isas.getadmin();//
		PageInfo<UserDataBean> page = new PageInfo<>(ad_list);
		return MessageBean.success().add("pageInfo", page);
	}
	/**
	 * 添加管理
	 * @param ud
	 * @return
	 */
	@RequestMapping(IStr_Enum.ISUP_INSERT_ADMIN)
	@ResponseBody
	public MessageBean insertAdmin(UserDataBean ud){
		isas.insertAdmin(ud);
		isas.insertAdminTime(ud);
		return MessageBean.success();
	}
	/**
	 * 給管理添加時間
	 * @param adId
	 * @param time
	 * @return
	 */
	@RequestMapping(IStr_Enum.IS_ADD_ADMIN_TIME)
	@ResponseBody
	public MessageBean addTime(Integer adId,Integer time){
		UserDataBean udb = isas.getadminById(adId);
		isas.addTime(adId,time+udb.getAtb().getT_val());
		return MessageBean.success();
	}
	@RequestMapping(value="updateAdminPsw",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean updateAdminPsw(Integer ad_num, String new_psw){
		isas.updateAdminPsw(ad_num,new_psw);
		return MessageBean.success();
	}
}
