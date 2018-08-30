<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
						<input type="text" placeholder="請輸入登錄密碼" name="u_password"/>
					</div>
					<a href="#" class="login_btn" id="login_mg">進入調整</a>
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
						window.location.href="${pageContext.request.contextPath}/"+data.map.to_page;
					}else{
						var error = $("<div></div>").addClass("error").append($("<span></span>").append("用戶名或者密碼錯誤"));
						$(".login_input_box").append(error);
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
	</script>
</body>
</html>