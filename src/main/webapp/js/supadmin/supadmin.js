$(function(){
	get_admin_news(1);
	//点击新增按钮弹出模态框
	$("#add_adminModel").click(function() {
		//弹出模态框add_workerModel
		$('#add_admin').modal({
			backdrop : "static"
		});
	});
	
	$("#admin_save_btn").click(function(){
		var admin_data = $("#insert_new_admin").serialize();
		$.ajax({
			url:$("#e_heard").val()+"/supadmin/insertadmin.do",
			data:admin_data,
			type:"POST",
			success:function(result){
				if(result.code==100){
					$('#add_admin').modal("hide");
					get_admin_news(1);
				}
			}
		});
	});
	
	$("#add_adtime_btn").click(function(){
		var admin_num = $("#ad_time_tx").val();
		var time = $("#time_add_input").val().trim();
		var reg = /^[0-9]+.?[0-9]*$/;
		//檢測時間是否符合要求
		if(time=="" || time<=0 || !reg.test(time)){
			alert("請輸入一個正整數時間");
			return false;
		}
		var page = $(".add_time_a:eq(0) a").eq(0).text();
		$.ajax({
			url:$("#e_heard").val()+"/supadmin/addadmintime.do",
			data:{"adId":admin_num,"time":time},
			type:"POST",
			success:function(result){
				if(result.code==100){
					$('#add_ad_time').modal("hide");
					get_admin_news(page);
					$("#time_add_input").val("");
				}
			}
		});
	});
});


//獲取管理員信息功能
function get_admin_news(pageNum){
	var heard = $("#e_heard").val();
	$.ajax({
		url:heard+"/supadmin/getadmin.do",
		data:"pn="+pageNum,
		type:"GET",
		success:function(result){
			if(result.code==100){
				var ad_data = result.map.pageInfo.list;
				var ad_pageInfo = result.map.pageInfo;
				//創建管理員信息表
				cr_admin_tbody(ad_data);
				//創建分頁數據信息
				cr_admin_pageInfo(ad_pageInfo);
				//創建導航頁面
				get_admin_nav(ad_pageInfo);
			}
		}
	});
}

//創建管理員信息表
function cr_admin_tbody(result){
	$("#crud_admin tbody").empty();
	$.each(result,function(re_index,re_item){
		var tr = $("<tr></tr>")
		//tr.append($("<td></td>").append(re_item.u_id));
		tr.append($("<td></td>").append(re_item.u_name)).append("<input type='text' value='"+re_item.u_id+"' style='display:none'></input>");
		tr.append($("<td></td>").append(re_item.atb.t_val));
		tr.append($("<td></td>").append(re_item.atb.t_c_val));
		tr.append($("<td></td>").append($("<button>添加時間</button>").addClass("add_time_btn")).append(" | ").append($("<button>修改密碼</button>").addClass("update_admin_password_btn")));
		tr.appendTo($("#crud_admin tbody"));
	});
	$(".update_admin_password_btn").click(function(){
		$("#update_admin_password_input").val("");
		$("#update_admin_password_num").val("");
		//彈出模態框
		$('#update_admin_password_modal').modal({
			backdrop : "static"
		});
		var admin_num = $(this).parents("tr").find("input").eq(0).val();
		$("#update_admin_password_num").val(admin_num);
	});
	
	
	
	$(".add_time_btn").click(function(){
		$("#time_add_input").val("");
		$("#ad_time_tx").val("");
		$('#add_ad_time').modal({
			backdrop : "static"
		});
		var admin_num = $(this).parents("tr").find("input").eq(0).val();
		$("#ad_time_tx").val(admin_num);
	});
	
	$("#up_admin_password_btn").click(function(){
		var new_psw = $("#update_admin_password_input").val();
		var admin_num = $("#update_admin_password_num").val();
		update_admin_psw(admin_num,new_psw);
	});
}

//修改管理 密碼
function update_admin_psw(admin_num,new_psw){
	$.ajax({
		url:$("#e_heard").val() + "/supadmin/updateAdminPsw.do",
		data:{"ad_num":admin_num,"new_psw":new_psw},
		type:"post",
		success:function(result){
			if(result.code==100){
				alert("修改成功");
				$('#update_admin_password_modal').modal("hide");
			}else{
				alert("操作錯誤");
			}
		}
	});
}


//創建分頁數據信息
function cr_admin_pageInfo(result){
	$("#admin_pageInfo_area").empty();
	$("#admin_pageInfo_area").append(
			"當前第" + result.pageNum + "頁,總共"
			+ result.pages + "頁,總共"
			+ result.total + "條記錄");
}

//創建分頁信息
function get_admin_nav(result){
	$("#admin_pageNav_area").empty();
	var nav = $("<nav></nav>");
	var ul = $("<ul></ul>").addClass("pagination");
	var firstPageLi = $("<li></li>").append(
			$("<a></a>").append("首頁").attr("href", "javascript:;"));
	var lastPageLi = $("<li></li>").append(
			$("<a></a>").append("末頁").attr("href", "javascript:;"));
	var prePageLi = $("<li></li>").append(
			$("<a></a>").append("&laquo;").attr("href", "javascript:;"));
	var nextPageLi = $("<li></li>").append(
			$("<a></a>").append("&raquo;").attr("href", "javascript:;"));
	if (result.isFirstPage == true) {
		firstPageLi.addClass("disabled");
		prePageLi.addClass("disabled");
	} else {
		firstPageLi.click(function() {
			get_admin_news(1);
		});
		prePageLi.click(function() {
			get_admin_news(result.pageNum - 1);
		});
	}
	if (result.isLastPage == true) {
		lastPageLi.addClass("disabled");
		nextPageLi.addClass("disabled");
	} else {
		nextPageLi.click(function() {
			get_admin_news(result.pageNum + 1);
		});
		lastPageLi.click(function() {
			get_admin_news(result.pages);
		});
	}
	ul.append(firstPageLi).append(prePageLi);
	$.each(result.navigatepageNums, function(index, item) {
		var numLi = $("<li></li>").append(
				$("<a></a>").append(item).attr("href", "javascript:;"));
		if (result.pageNum == item) {
			numLi.addClass("active add_time_a");
		}
		numLi.click(function() {
			get_admin_news(item);
		});
		ul.append(numLi);
	});
	ul.append(nextPageLi).append(lastPageLi);
	nav.append(ul).appendTo("#admin_pageNav_area");
}
