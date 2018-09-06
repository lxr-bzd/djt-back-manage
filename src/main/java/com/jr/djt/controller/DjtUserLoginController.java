package com.jr.djt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.WorkerBean;
import com.jr.djt.constant.INum_Enum;
import com.jr.djt.constant.IStr_Enum;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.service.DjtUserService;
import com.jr.djt.service.impl.StartupListenerService;

/**
 * 后台管理登入/退出登入
 * 
 * @author qiuchen
 *
 */
@Controller
public class DjtUserLoginController extends BaseController {
	@Autowired
	private DjtUserService userService;

	@Autowired
	private StartupListenerService sls;

	/**
	 * 后台登入
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public MessageBean UserLogin(UserDataBean user, HttpSession session) {
		System.out.println(user.getU_name() + ":" + user.getU_password());
		UserDataBean u = userService.getUser(user);
		if (u == null) {
			return MessageBean.fail().add("msg", "用戶名或者密碼有誤!");
		}
		AdminTimeBean admin = sls.getAdminTimeById(u.getU_id());
		if(admin == null || admin.getT_isclock() == 1){
			session.setAttribute("userService", u);
			session.setMaxInactiveInterval(60 * 60 * 24);
			String page;
			/*if (u.getU_id() == INum_Enum.IAD_NUM_ONE) {*/
				page = IStr_Enum.ISUP_NAMESPACE + "/" + IStr_Enum.ISUP_LOGIN_PAGE + IStr_Enum.IS_SUFFIX;
			/*} else {
				page = IStr_Enum.IAD_LOGIN_PAGE + IStr_Enum.IS_SUFFIX;
			}*/
			return MessageBean.success().add("to_page", page);
		}else{
			return MessageBean.fail().add("msg", "您的可用時間已使用完，請儘快充入時間，以免耽誤您的使用!");
		}
	}

	/**
	 * 去客服管理系统
	 * 
	 * @return
	 */
	@RequestMapping("/toadminpage")
	public String toAdminPage() {
		return "admin";
	}

	/**
	 * 后台退出登入,返回登入界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit")
	public String exitLogin(HttpSession session) {
		session.invalidate();
		return "../../backstage-login";
	}

	@RequestMapping(value = "upasswordadmin", method = RequestMethod.POST)
	@ResponseBody
	public MessageBean updagePasswordAdmin(String oldpsw, String newpsw, HttpServletRequest req) {
		UserDataBean u = (UserDataBean) req.getSession().getAttribute("userService");
		if (u.getU_password().equals(oldpsw)) {
			userService.updagePasswordAdmin(u.getU_id(), newpsw);
			/*Object obj = req.getAttribute("time");
			if(obj!=null){
				return MessageBean.timeWarn().add("msg", "您的時間已經不滿"+(long)obj+"小時,為了不耽誤您的使用,請儘快加時間");
			}*/
			return MessageBean.success();
		}
		return MessageBean.fail();
	}
}
