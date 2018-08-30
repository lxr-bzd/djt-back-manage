<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 强制使用ie7模式解析网页代码 -->
<html xmlns=http://www.w3.org/1999/xhtml xmlns:bd=http://www.baidu.com/2010/xbdml>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>后台登入系统</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<!-- 引入jquery -->
<script type="text/javascript" src="statis/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<!--后台登录页-->
	<section class="login_bg">
		<div class="login_wrap">
			<div class="login_box">
				<h1 class="login_tilte">計 算 表 後 台 管 理 系 統</h1>
				<div class="login_input_box">
					<div class="login_input">
						<input type="text" placeholder="請輸入用戶名" name="u_name"/>
					</div>
					<div class="login_input">
						<input type="password" placeholder="請輸入登錄密碼" name="u_password"/>
					</div>
					<a href="javascript:;" class="login_btn" id="login_mg">進入調整</a>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		$("#login_mg").click(function() {
			if($("div").hasClass("error")){
				re_enter();
			}
			var u_name = $("input[name='u_name']").val();
			var u_password = $("input[name='u_password']").val();
			$.ajax({
				url:"login.do",
				data:{"u_name":u_name,
					"u_password":u_password	
					},
				dataType:"JSON",
				type:"POST",
				success:function(data){
					if(data.code==100){
						window.location.href="${pageContext.request.contextPath}"+data.map.to_page;
						//alert(data.map.to_page);
					}else{
						alert(data.map.msg);
					}
				}
			})
		})
		//点击进入时移除错误信息
		function re_enter(){
			$("div").remove(".error");
		}
		
		$("input[name='u_name']").on("focus",function(){
			if($("div").hasClass("error")){
				re_enter();
			}
			
		});
		
		$("input[name='u_password']").on("focus",function(){
			if($("div").hasClass("error")){
				re_enter();
			}
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
	</script>
</body>
</html>