<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>後臺管理</title>
<link
	href="${pageContext.request.contextPath }/statis/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/main.css" />
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
<script
	src="${pageContext.request.contextPath }/statis/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/admin_get_s_table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/user/admin_get_user.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/admin_updata_s_table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/admin_usetable.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/supadmin/supadmin.js"></script>
</head>

<body class="layui-layout-body">

	<!-- 修改管理密码模态框 -->
	<div class="modal fade" id="update_admin_password_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="adminupModalLabel">修改用戶密碼</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update_admin_password_form">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">新密碼:</label>
							<div class="col-sm-10">
								<input type="text" name="time" class="form-control"
									id="update_admin_password_input" placeholder="新密碼"><span class="error" style="display:none"></span>
							</div>
						</div>
						<input type="text" style="display:none" id="update_admin_password_num">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					<button type="button" class="btn btn-primary" id="up_admin_password_btn">保存</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 添加管理時間彈框 -->
	<div class="modal fade" id="add_ad_time" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加管理時間</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="insert_new_admin_time">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">添加時間:</label>
							<div class="col-sm-10">
								<input type="text" name="time" class="form-control"
									id="time_add_input" placeholder="添加時間，單位：小時">
							</div>
						</div>
						<input type="text" style="display:none" id="ad_time_tx">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="add_adtime_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 添加管理彈框 -->
	<!-- <div class="modal fade" id="add_admin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myadModalLabel">添加管理</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="insert_new_admin">
						<div class="form-group">
							<label for="admin_add_input" class="col-sm-2 control-label">管理名稱:</label><span class="error_uname" style="display:none">用户名已经存在</span>
							<div class="col-sm-10">
								<input type="text" name="u_name" class="form-control"
									id="admin_add_input" placeholder="管理名稱">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">管理密碼:</label>
							<div class="col-sm-10">
								<input type="text" name="u_password" class="form-control"
									id="admin_add_input" placeholder="管理密碼">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="admin_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div> -->



	<!-- 修改用戶密码模态框 -->
	<div class="modal fade" id="update_u_password_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myupModalLabel">修改用戶密碼</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update_u_password_form">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">新密碼:</label>
							<div class="col-sm-10">
								<input type="text" name="time" class="form-control"
									id="update_u_password_input" placeholder="最多6位"><span class="error" style="display:none"></span>
							</div>
						</div>
						<input type="text" style="display:none" id="update_password_num">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					<button type="button" class="btn btn-primary" id="up_u_password_btn">保存</button>
				</div>
			</div>
		</div>
	</div>



	<!-- 添加用戶彈框 -->
	<div class="modal fade" id="add_emp" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加用戶</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="insert_new_user">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">用戶名:</label>
							<div class="col-sm-10">
								<input type="text" name="u_name" class="form-control"
									id="uname_add_input" placeholder="用戶名"><span class="error" style="display:none"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">用戶密碼:</label>
							<div class="col-sm-10">
								<input type="text" name="u_password" class="form-control"
									id="password_add_input" placeholder="最多6位"><span class="error" style="display:none"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="sex_add_input" class="col-sm-2 control-label">是否鎖定:</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="u_islock" value="1" checked="checked"/>
									否
								</label> <label class="radio-inline"> <input type="radio"
									name="u_islock" value="2"/> 是
								</label>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="worker_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">計算表後台超級管理系統</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">歡迎，<span class="user_name">${userService.u_name }</span>
					!
				</li>
				<li class="layui-nav-item"><a
					href="${pageContext.request.contextPath }/exit.do">退出登录</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed user_admin"><a class="" href="javascript:;">用戶管理</a></li>
					<c:if test="${isSu}">
					<li class="layui-nav-item layui-nav-itemed crud_admin"><a class="" href="javascript:;">管理員管理</a></li>
					</c:if>
					<li class="layui-nav-item backs_admin"><a href="javascript:;">後台管理</a>
					
						<dl class="layui-nav-child">
							<dd class="backs_item">
								<a id="back_admin_gets" href="../base_table.html"  target="backs_admin_iframe">修改生表數據</a>
							</dd>
							<dd class="backs_item">
								<a id="back_admin_usetable" href="../table_use.html"  target="backs_admin_iframe">生表數據使用狀況</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;" class="con_box">
			<!-- 用户管理 -->
				<div class="con_item" id="user_admin">
					<div class="query_worker_box">
						<label id="query_box_name">用戶名： <input type="text" name="username">
						<button class="query_btn" id="query_box_name_btn">查詢</button><button class="query_btn" id="query_box_all_btn">查詢全部</button>
						<input type="text" id="sl_input" style="display:none">
					</div>
					<div class="col-md-4 col-md-offset-0">
						<button class="btn btn-primary" id="add_workerModel">
							<b>+</b>新增用戶
						</button> <button class="btn btn-primary" id="delete_workerModel">
							<b>-</b>刪除用戶
						</button>
					</div>
					<table class="layui-table">
						<thead>
							<tr>
								<th><input type="checkbox" id="select_all_worker"/> </th>
								<th>用戶名</th>
								<th>是否鎖定</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody>

						</tbody>

					</table>
					<!-- 用户分頁信息 -->
					<div class="row">
						<div class="col-md-6" id="pageInfo_area"></div>
						<div class="col-md-6" id="pageNav_area"></div>
					</div>
				</div>
				
				<!-- crud_admin開始 -->
				<div class="con_item" id="crud_admin">
					
					<table class="layui-table">
						<thead>
							<tr>
								<!-- <th>ID</th> -->
								<th>管理員名</th>
								<th>充值總時間</th>
								<th>使用時間</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody>

						</tbody>

					</table>
					<!-- 管理分頁信息 -->
					<div class="row">
						<div class="col-md-6" id="admin_pageInfo_area"></div>
						<div class="col-md-6" id="admin_pageNav_area"></div>
					</div>
				</div>
				
				<!-- 查看生成表 -->
				<div class="con_item" id="user_detail">
					<iframe name="user_detail_iframe" ></iframe>
				</div>
				
				<!-- 查看生成表 -->
				<div class="con_item" id="backs_admin">
					<iframe name="backs_admin_iframe" ></iframe>
				</div>
				
					
					
			</div>
		</div>
	<input type="text" value="${pageContext.request.contextPath }" id="e_heard" style="dispaly: none">
	<script>
		/* $("#backs-table").load("backstage-table.html .backstage_wrap");
		$("#admin-table").load("admin-table.html .backstage_wrap"); */
		layui.use('element', function() {
			var element = layui.element;
		});
		$("#user_admin").show();
		$(".user_admin").click(function() {
			$("#sl_input").val("");
			$("#query_box_name input[name='username']:eq(0)").val("");
			get_users_page(1);
			$("#user_admin").show().siblings(".con_item").hide();
		});
		//管理員管理
		$(".crud_admin").click(function() {
			$("#crud_admin").show().siblings(".con_item").hide();
		});
		
		//后台管理点击事件
		 $(".backs_item").click(function(e) {
			$("#backs_admin").show().siblings(".con_item").hide();
			var index = $(this).index();
			$(".backs_list").hide().eq(index).show();
		});
		

		$(".detail_head span").click(function() {
			$("#user_admin").show().siblings(".con_item").hide();
		});

		$.ajaxSetup({
					type : 'POST',
					complete : function(xhr, status) {
						var sessionStatus = xhr
								.getResponseHeader('sessionstatus');
						if (sessionStatus == 'timeout') {
							var top = getTopWinow();
							var yes = confirm('由於您長時間未操作,登入超时, 請重新登入.');
							if (yes) {
								/*location.href ---如果后面没跟值 那么就是获取当前页面的url
								    至于top, 表示是顶层页面， 因为页面之中可能嵌入了 frame 等子页面，top表示最外面一层
								    top.location.href  -- 当前页面地址
								 */
								top.location.href = '${pageContext.request.contextPath}/backstage-login.jsp';
							} else {
								window.opener = null;
								window
										.open(
												'${pageContext.request.contextPath}/backstage-login.jsp',
												'_self');
								//window.close();
							}
						} else if (sessionStatus == 'timeout') {
							var top = getTopWinow();
							var yes = confirm('由於您時間耗盡,請儘快補充時間.');
							if (yes) {
								/*location.href ---如果后面没跟值 那么就是获取当前页面的url
								    至于top, 表示是顶层页面， 因为页面之中可能嵌入了 frame 等子页面，top表示最外面一层
								    top.location.href  -- 当前页面地址
								 */
								top.location.href = '${pageContext.request.contextPath}/backstage-login.jsp';
							} else {
								window.opener = null;
								window
										.open(
												'${pageContext.request.contextPath}/backstage-login.jsp',
												'_self');
								//window.close();
							}
						}
					}
				});

		/**
		 * 在页面中任何嵌套层次的窗口中获取顶层窗口
		 * @return 当前页面的顶层窗口对象
		 */
		function getTopWinow() {
			var p = window;
			while (p != p.parent) {
				p = p.parent;
			}
			return p;
		}
	</script>
</body>

</html>