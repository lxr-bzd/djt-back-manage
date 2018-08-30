$(function(){
	$("#update_myadmin_password").click(function(){
		$('#update_myadmin_password_modal').modal({
			backdrop : "static"
		});
	});
	
	$("#up_myadmin_password_btn").click(function(){
		var old_psw = $("#update_myadmin_old_password_input").val();
		var new_psw = $("#update_myadmin_new_password_input").val();
		$.ajax({
			url:$("#e_heard").val()+"/upasswordadmin.do",
			data:{"oldpsw":old_psw,"newpsw":new_psw},
			type:"post",
			success:function(result){
				if(result.code==100 || result.code==200){
					$('#update_myadmin_password_modal').modal("hide");
					var yes = confirm("修改成功，請重新登入");
					if(yes){
						window.location.href="backstage-login.jsp";
					}
				}else{
					alert("密碼不正確，請重新登入或者聯繫超管");
				}
			}
		});
	});
});