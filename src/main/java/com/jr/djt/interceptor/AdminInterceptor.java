package com.jr.djt.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.constant.INum_Enum;
import com.jr.djt.constant.IStr_Enum;
import com.jr.djt.service.impl.StartupListenerService;

/**
 * 攔截驗證用戶,監聽普通管理員的狀態
 * 
 * @author qiuchen
 *
 */
 
public class AdminInterceptor implements HandlerInterceptor {
	private static final String INGORE_URI = "/login";
	@Autowired
	private StartupListenerService sls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getServletPath();
		System.out.println("進入的uri:"+uri);
		if (uri.contains(INGORE_URI)) {
			return true;
		}
		
		UserDataBean admin = (UserDataBean) request.getSession().getAttribute("userService");
		if (admin == null) {
			//判斷是否是ajax請求，如果是，設置用戶超時
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.setHeader("sessionstatus", "timeout");
				response.sendError(508, "session timeout");
			}
			if(uri.contains(IStr_Enum.ISUP_NAMESPACE)){
				//不允許通行
				response.sendRedirect("../backstage-login.jsp");
			}else{
				response.sendRedirect("backstage-login.jsp");
			}
			return false;
		}
		//用戶有效未超時
		//進入的uri地址是否包含超管路徑
		if(uri.contains(IStr_Enum.ISUP_NAMESPACE) && admin.getU_id() != 1){
			response.sendRedirect("../backstage-login.jsp");
			request.getSession().invalidate();
			return false;
		}
		//判斷用戶鎖定與否
		AdminTimeBean ad_user = sls.getAdminTimeById(admin.getU_id());
		if(ad_user == null || ad_user.getT_isclock() == 1){
			if(ad_user!=null){
				long time = ad_user.getT_val() -  ad_user.getT_c_val();
				if(time<=3 && !"3".equals(admin.getTimeInfo())){
					admin.setTimeInfo("3");
					request.setAttribute("time", time);
				}else if(time>3 && time<=6 && !"6".equals(admin.getTimeInfo())){
					admin.setTimeInfo("6");
					request.setAttribute("time", time);
				}else if(time>6 && time<=12 && !"12".equals(admin.getTimeInfo())){
					admin.setTimeInfo("12");
					request.setAttribute("time", time);
				}else if(time>12 && time<=24 && !"24".equals(admin.getTimeInfo())){
					admin.setTimeInfo("12");
					request.setAttribute("time", time);
				}
			}
			//主管理/未鎖定管理
			return true;
		}
		//判斷是否是ajax請求，如果是，設置用戶時間已經用完，儘快充值
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			ObjectMapper map = new ObjectMapper();
			String msg = map.writeValueAsString(MessageBean.fail().add("msg", "您的可用時間已使用完，請儘快充入時間，以免耽誤您的使用!"));
			response.getWriter().print(msg);
		}else{
			request.getRequestDispatcher("backstage-login.jsp").forward(request,response);
		}
		request.getRequestDispatcher("backstage-login.jsp").forward(request,response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
