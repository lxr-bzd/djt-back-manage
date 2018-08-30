<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>後臺超级管理</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/supadmin/supadmin.js"></script>
</head>

<body class="layui-layout-body">
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
	<div class="modal fade" id="add_admin" tabindex="-1" role="dialog"
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
									id="empName_add_input" placeholder="用戶名"><span class="error" style="display:none"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">用戶密碼:</label>
							<div class="col-sm-10">
								<input type="text" name="u_password" class="form-control"
									id="email_add_input" placeholder="用戶密碼"><span class="error" style="display:none"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="sex_add_input" class="col-sm-2 control-label">是否鎖定:</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="u_islock" id="sex1_add_input" value="1" checked="checked">
									否
								</label> <label class="radio-inline"> <input type="radio"
									name="u_islock" id="sex2_add_input" value="2"> 是
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
			<div class="layui-logo">計算表後台管理系統</div>
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
					<li class="layui-nav-item layui-nav-itemed user_admin"><a
						class="" href="javascript:;">用戶管理</a></li>
					<li class="layui-nav-item layui-nav-itemed crud_admin"><a
						class="" href="javascript:;">管理員管理</a></li>
					<li class="layui-nav-item backs_admin"><a href="javascript:;">後台管理</a>
						<dl class="layui-nav-child">
							<dd class="backs_item">
								<a href="javascript:;" id="back_admin_gets">查看生表數據</a>
							</dd>
							<dd class="backs_item">
								<a href="javascript:;" id="back_admin_update">修改生表數據</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;" class="con_box">
				<div class="con_item" id="user_admin">
					<div class="col-md-4 col-md-offset-0">
						<button class="btn btn-primary" id="add_workerModel">+新增用戶</button>
					</div>
					<table class="layui-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>用戶名</th>
								<!-- <th>創建時間</th> -->
								<th>是否鎖定</th>
								<th>操作</th>
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
				<!-- 用戶信息結束 -->
				<!-- crud_admin開始 -->
				<div class="con_item" id="crud_admin">
					<div class="col-md-4 col-md-offset-0">
						<button class="btn btn-primary" id="add_adminModel">+新增管理員</button>
					</div>
					<table class="layui-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>管理員名</th>
								<th>充值總時間</th>
								<th>使用時間</th>
								<th>操作</th>
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
				
				
				
				<div class="con_item" id="backs_admin">
					<div class="backs_list">
						<div id="backs-table">
							<!--后台表-->
							<section class="backstage_wrap">
							<div class="backstage_box">
								<h2>計算表生數據預覽表 </h2>
								<!-- 選擇表 -->
								<select id="s_table_num"><option value="1">表1</option>
									<option value="2">表2</option></select>
								<!--数据表-->
								<div class="backtable_wrap clearfix">
									<!--第一組-->
									<table border="1" class="fl" id="table_one">
										<thead>
											<tr class="group_num">
												<th colspan="16" id="table_one_title">第<span></span>組
												</th>
											</tr>
											<tr class="group_class">
												<th colspan="2" rowspan="2" class="">序號</th>
												<th colspan="2">第 一 戶</th>
												<th colspan="2">第 二 戶</th>
												<th colspan="2">第 三 戶</th>
												<th colspan="2">第 四 戶</th>
												<th colspan="2">第 五 戶</th>
												<th colspan="2">第 六 戶</th>
												<th rowspan="2">統計</th>
												<th rowspan="2">實計</th>
											</tr>
											<tr class="group_class">
												<!--分类-->
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
											</tr>
										</thead>
										<tbody>

										</tbody>

									</table>

									<!--第二組-->
									<table border="1" class="table_mid fl" id="table_two">
										<thead>
											<tr class="group_num">
												<th colspan="16" id="table_two_title">第<span></span>組
												</th>
											</tr>
											<tr class="group_class">
												<th colspan="2" rowspan="2" class="">序號</th>
												<th colspan="2">第 一 戶</th>
												<th colspan="2">第 二 戶</th>
												<th colspan="2">第 三 戶</th>
												<th colspan="2">第 四 戶</th>
												<th colspan="2">第 五 戶</th>
												<th colspan="2">第 六 戶</th>
												<th rowspan="2">統計</th>
												<th rowspan="2">實計</th>
											</tr>
											<tr class="group_class">
												<!--分类-->
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
											</tr>
										</thead>
										<tbody>

										</tbody>

									</table>
									<!--第三組-->
									<table border="1" class="fl" id="table_three">
										<thead>
											<tr class="group_num">
												<th colspan="16" id="table_three_title">第<span></span>組
												</th>
											</tr>
											<tr class="group_class">
												<th colspan="2" rowspan="2" class="">序號</th>
												<th colspan="2">第 一 戶</th>
												<th colspan="2">第 二 戶</th>
												<th colspan="2">第 三 戶</th>
												<th colspan="2">第 四 戶</th>
												<th colspan="2">第 五 戶</th>
												<th colspan="2">第 六 戶</th>
												<th rowspan="2">統計</th>
												<th rowspan="2">實計</th>
											</tr>
											<tr class="group_class">
												<!--分类-->
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
												<td>老少</td>
												<td>男女</td>
											</tr>
										</thead>
										<tbody></tbody>

									</table>
								</div>
							</div>
							<!--生表预览分組按钮-->
							<div class="group_btn_wrap">
								<div class="group_btn_box" id="nav_box">
									<!-- 放入導航按鈕處 -->
								</div>
							</div>
							</section>
						</div>
					</div>
					<div class="backs_list">
						<div id="backs_update_table">
							<section class="backstage_wrap">
							<div class="backstage_box">
								<h2 class="clearfix">
									生数据表
									<div class="keep_btn fr">
										<a href="javascript:;" id="save_data">保存</a>
									</div>
								</h2>
								<!-- ===================修改表組號================ -->
								<select id="up_query_box_m"><option value="1">表1</option>
									<option value="2">表2</option></select>
								<!--修改查询框-->
								<div class="query_box">
									<label id="up_query_box_z">組號： <select></select></label> <label
										id="up_query_box_x">序號：<select></select></label> <label
										id="up_query_box_h">戶號：<select></select></label>
									<button class="query_btn" id="up_query_box_btn">查詢</button>
								</div>
								<!--数据表-->
								<div class="backtable_wrap clearfix">
									<!--第一組-->
									<table border="1" class="fl w_33" id="up_table_one">
										<thead>
											<tr class="group_num tb_bg t_blue">
												<th colspan="16" id="up_table_one_title">第<span></span>組
												</th>
											</tr>
											<tr class="admin_class tb_bg t_blue">
												<th colspan="1">序號</th>
												<th colspan="1">第 一 戶</th>
												<th colspan="1">第 二 戶</th>
												<th colspan="1">第 三 戶</th>
												<th colspan="1">第 四 戶</th>
												<th colspan="1">第 五 戶</th>
												<th colspan="1">第 六 戶</th>
											</tr>
										</thead>

										<tbody>
										</tbody>
									</table>

									<!--第二組-->
									<table border="1" class="table_mid fl w_33" id="up_table_two">
										<thead>
											<tr class="group_num tb_bg t_blue">
												<th colspan="16" id="up_table_two_title">第<span></span>組
												</th>
											</tr>
											<tr class="admin_class tb_bg t_blue">
												<th colspan="1">序號</th>
												<th colspan="1">第 一 戶</th>
												<th colspan="1">第 二 戶</th>
												<th colspan="1">第 三 戶</th>
												<th colspan="1">第 四 戶</th>
												<th colspan="1">第 五 戶</th>
												<th colspan="1">第 六 戶</th>
											</tr>
										</thead>

										<tbody>
											<!--生-->
											<!--数据行--001-->
										</tbody>
									</table>
									<!--第三組-->
									<table border="1" class="fl w_33" id="up_table_three">
										<thead>
											<tr class="group_num tb_bg t_blue">
												<th colspan="16" id="up_table_three_title">第<span></span>組
												</th>
											</tr>
											<tr class="admin_class tb_bg t_blue">
												<th colspan="1">序號</th>
												<th colspan="1">第 一 戶</th>
												<th colspan="1">第 二 戶</th>
												<th colspan="1">第 三 戶</th>
												<th colspan="1">第 四 戶</th>
												<th colspan="1">第 五 戶</th>
												<th colspan="1">第 六 戶</th>
											</tr>
										</thead>

										<tbody>
											<!--生-->
											<!--数据行--001-->
										</tbody>
									</table>

									<div class="keep_btn fr">
										<a href="javascript:;" id="save_data_foot">保存</a>
									</div>

								</div>
							</div>

							<!--生表修改分組按钮-->
							<div class="group_btn_wrap">
								<div class="group_btn_box" id="up_nav_box">
									<!-- 放入導航按鈕處 -->
								</div>
							</div>

							</section>
						</div>
					</div>
				</div>
				<!--查看详情页-->
				<div class="con_item" id="user_detail">
					<h3 class="detail_head">
						<span>返回</span>
					</h3>
					<div id="user_data_details">
						<!-- 用戶數據詳情 -->
						<section class="backstage_wrap">
						<div class="backstage_box">
							<h2>計算表 A</h2>
							<select>
								<option value="1">計算表A</option>
								<option value="2">計算表B</option>
							</select>
							<p class="user_info" id="check_user_news">
								ID :<span>001</span> 用户名 :<span>小花</span> 正在使用的工作表 : 第<span>0</span>張
							</p>
							<!--数据表-->
							<div class="backtable_wrap clearfix">
								<!--第一組-->
								<table border="1" class="fl" id="check_table_one">
									<thead>
										<tr class="group_num">
											<th colspan="16" id="check_table_one_title">第<span></span>組
											</th>
										</tr>
										<tr class="group_class">
											<th colspan="2" rowspan="2" class="">序號</th>
											<th colspan="2">第 一 戶</th>
											<th colspan="2">第 二 戶</th>
											<th colspan="2">第 三 戶</th>
											<th colspan="2">第 四 戶</th>
											<th colspan="2">第 五 戶</th>
											<th colspan="2">第 六 戶</th>
											<th rowspan="2">統計</th>
											<th rowspan="2">實計</th>
										</tr>
										<tr class="group_class">
											<!--分类-->
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>

								<!--第二組-->
								<table border="1" class="table_mid fl" id="check_table_two">
									<thead>
										<tr class="group_num">
											<th colspan="16" id="check_table_two_title">第<span></span>組
											</th>
										</tr>
										<tr class="group_class">
											<th colspan="2" rowspan="2" class="">序號</th>
											<th colspan="2">第 一 戶</th>
											<th colspan="2">第 二 戶</th>
											<th colspan="2">第 三 戶</th>
											<th colspan="2">第 四 戶</th>
											<th colspan="2">第 五 戶</th>
											<th colspan="2">第 六 戶</th>
											<th rowspan="2">統計</th>
											<th rowspan="2">實計</th>
										</tr>
										<tr class="group_class">
											<!--分类-->
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>
								<!--第三組-->
								<table border="1" class="fl" id="check_table_three">
									<thead>
										<tr class="group_num">
											<th colspan="16" id="check_table_three_title">第<span></span>組
											</th>
										</tr>
										<tr class="group_class">
											<th colspan="2" rowspan="2" class="">序號</th>
											<th colspan="2">第 一 戶</th>
											<th colspan="2">第 二 戶</th>
											<th colspan="2">第 三 戶</th>
											<th colspan="2">第 四 戶</th>
											<th colspan="2">第 五 戶</th>
											<th colspan="2">第 六 戶</th>
											<th rowspan="2">統計</th>
											<th rowspan="2">實計</th>
										</tr>
										<tr class="group_class">
											<!--分类-->
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
											<td>老少</td>
											<td>男女</td>
										</tr>
									</thead>
									<tbody></tbody>

								</table>
							</div>
						</div>
						<!--查看详情分組按钮-->
						<div class="group_btn_wrap">
							<div class="group_btn_box" id="check_nav_box">
								<!-- 放入導航按鈕處 -->
							</div>
						</div>
						</section>
					</div>
				</div>

			</div>
		</div>
	</div>
	<input type="text" value="${pageContext.request.contextPath }" id="e_heard" style="dispaly:none">
	<script>
		/* $("#backs-table").load("backstage-table.html .backstage_wrap");
		$("#admin-table").load("admin-table.html .backstage_wrap"); */
		layui.use('element', function() {
			var element = layui.element;
		});
		$("#user_admin").show();
		$(".user_admin").click(function() {
			$("#user_admin").show().siblings(".con_item").hide();
		});
		$(".crud_admin").click(function() {
			$("#crud_admin").show().siblings(".con_item").hide();
		});
		$(".backs_item").click(function() {
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
				var sessionStatus = xhr.getResponseHeader('sessionstatus');
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
						window.open('${pageContext.request.contextPath}/backstage-login.jsp', '_self');
						//window.close();
					}
				}else if(sessionStatus == 'timeout'){
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
						window.open('${pageContext.request.contextPath}/backstage-login.jsp', '_self');
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