package com.jr.djt.constant;

public interface IStr_Enum {
	/* 通用常量 */
	String IS_LOGIN = "/login";//管理登入url
	String IS_SUFFIX = ".do";//訪問後最
	String ISESSION_USER = "userService";//存放登入用戶的session中添加的屬性
	/* 普通管理常量開始 */
	String IAD_LOGIN_PAGE = "/toadminpage";//普通管理進入的頁面的url
	/* 超級管理常量開始 */
	String ISUP_NAMESPACE = "/supadmin";//超管處理類的命名空間
	String ISUP_LOGIN_PAGE = "to_supage";//超級管理進入的超管頁面的url
	String ISUP_PAGE = "supadmin/supadmin";//超管進入的jsp頁面
	String ISUP_INSERT_ADMIN = "insertadmin";//添加普通管理
	String IS_ADD_ADMIN_TIME = "addadmintime";//添加普通管理的時間
	
	
	
}
