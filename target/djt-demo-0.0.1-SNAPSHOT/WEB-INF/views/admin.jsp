<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title></title>
<link
	href="${pageContext.request.contextPath }/statis/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/main.css" important />
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
</head>

<body class="layui-layout-body">
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
						class="" href="#user_admin">用戶管理</a></li>
					<li class="layui-nav-item backs_admin"><a href="#backs_admin">後台管理</a>
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
					<table class="layui-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>用戶名</th>
								<th>時間</th>
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
				<div class="con_item" id="backs_admin">
					<div class="backs_list">
						<div id="backs-table">
							<!--后台表-->
							<section class="backstage_wrap">
							<div class="backstage_box">
								<h2>計算表 A</h2>
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
								<!--修改查询框-->
								<div class="query_box">
									<label id="up_query_box_z">組號： <select></select></label>
									<label id="up_query_box_x">序號：<select></select></label>
									<label id="up_query_box_h">戶號：<select></select></label>
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
							<p class="user_info" id="check_user_news">
								ID :<span>001</span> 用户名 :<span>小花</span>
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
		$(".backs_item").click(function() {
			$("#backs_admin").show().siblings(".con_item").hide();
			var index = $(this).index();
			$(".backs_list").hide().eq(index).show();
		});
		
		$(".detail_head span").click(function () {
			$("#user_admin").show().siblings(".con_item").hide();
		})
	</script>
</body>

</html>