//用戶信息內容js
$(function(){
	get_users_page(1);
	cr_user_tb();
	//cr_user_nav_btn();
	//点击新增按钮弹出模态框
	$("#add_workerModel").click(function() {
		//弹出模态框add_workerModel
		$('#add_emp').modal({
			backdrop : "static"
		});
		$("#insert_new_user .error").css("display","none");
		$("#insert_new_user input").not($("input[type='radio']")).val("");
		$("#password_add_input").val("");
	});

	//全選
	$("#select_all_worker").click(function(){
		if($(this).prop("checked")==true){
			$(".worker_ck").prop("checked",true);
		}else{
			$(".worker_ck").prop("checked",false);
		}
	});
	//刪除綁定
	$("#delete_workerModel").click(function(){
		var num=[];
		var checked_del = $(".worker_ck:checked");
		$.each(checked_del,function(index_del,item_del){
			num[index_del]=$(item_del).attr("num");
		});
		$.ajax({
			url:$("#e_heard").val()+"/deleteworkerlist.do",
			data:"list="+num,
			type:"post",
			success:function(result){
				if(result.code==100){
					var page = $(".active a").eq(0).text();
					var like_name = $("#sl_input").val().trim();
					if(like_name == ""){
						get_users_page(page);
					}else{
						$("#query_box_name input[name='username']").eq(0).val(like_name);
						get_like_user_result(page,like_name);
					}
					alert("ok!");
					$("#select_all_worker").prop("checked",false);
				}else{
					alert(result.map.msg);
				}
			}
		});
	});
	
	//查询绑定
	$("#query_box_name_btn").click(function(){
		var u_name = $("#query_box_name input[name='username']").eq(0).val().trim();
		if(u_name==""){
			alert("請輸入正確的查詢名字");
			return false;
		}
		$("#sl_input").val(u_name);
		get_like_user_result(1,u_name);
	});
	//查询全部绑定
	$("#query_box_all_btn").click(function(){
		$("#sl_input").val("");
		$("#query_box_name input[name='username']").eq(0).val("");
		get_users_page(1);
	});
	
	$("#worker_save_btn").click(function(){
		var uname=$("#insert_new_user input[name=u_name]").val();
		var password=$("#insert_new_user input[name=u_password]").val();
		if(uname.replace(/\s+/g, "")=='' || uname.replace(/\s+/g, "").length<1){
			var name_error = $("#insert_new_user .error").eq(0).empty();
			name_error.append('請輸入正確的用戶名');
			name_error.css("display","block");
			$("#insert_new_user input[name=u_name]").focus(function(){
				$("#insert_new_user .error").eq(0).css("display","none");
			});
			return false;
		}
		var flag = check_uname(uname);
		if(flag){
			$("#insert_new_user .error").eq(0).empty().append('用戶名已存在').css("display","block");
			$("#insert_new_user input[name=u_name]").focus(function(){
				$("#insert_new_user .error").eq(0).css("display","none");
			});
			return false;
		}
		if(password.replace(/\s+/g, "")=='' || password.replace(/\s+/g, "").length>6){
			var password_error = $("#insert_new_user .error").eq(1).empty();
			password_error.append('請輸入正確的用戶密碼，最多6位').css("display","block");
			$("#insert_new_user input[name=u_password]").focus(function(){
				$("#insert_new_user .error").eq(1).css("display","none");
			});
			return false;
		}
		var user_data = $("#insert_new_user").serialize();
		$.ajax({
			url:$("#e_heard").val()+"/insert_user.do",
			data:user_data,
			type:"POST",
			success:function(result){
				if(result.code==100){
					$('#add_emp').modal("hide");
					get_users_page(1);
					
				}
			}
		});
		$("#password_add_input").val("");
	});
	
	//修改密码按钮检测，通过了保存，否则提醒用户密码不符合规则
	$("#up_u_password_btn").click(function(){
		var user_num = $("#update_password_num").val();
		var new_password = $("#update_u_password_input").val();
		if(new_password.replace(/\s+/g, "")=='' || new_password.replace(/\s+/g, "").length>6){
			var password_error = $("#update_u_password_form .error").eq(0).empty();
			password_error.append("請輸入正確的密碼，最多6位").css("display","block");
			$("#update_u_password_input").focus(function(){
				password_error.css("display","none");
			});
			return false;
		}
		//通过，提交保存
		update_user_password(user_num,new_password);
	});
});
function update_user_password(user_num,new_password){
	$.ajax({
		url:$("#e_heard").val()+"/updateuserpassword.do",
		data:{"user_num":user_num,"new_password":new_password},
		type:"post",
		success:function(result){
			if(result.code==100){
				alert("修改成功");
				$('#update_u_password_modal').modal("hide");
			}
		}
	});
}
/**
 * 單個刪除
 * @param u_num
 * @returns
 */
function delete_one(u_num){
	$.ajax({
		url:$("#e_heard").val()+"/deleteOneById.do",
		data:"unum="+u_num,
		type:"get",
		success:function(result){
			if(result.code==100){
				var page = $(".active a").eq(0).text();
				var like_name = $("#sl_input").val().trim();
				if(like_name == ""){
					get_users_page(page);
				}else{
					$("#query_box_name input[name='username']").eq(0).val(like_name);
					get_like_user_result(page,like_name);
				}
				alert("刪除成功");
			}
		}
	});
}
/**
 * 模糊查询
 * @param uname
 * @returns
 */
function get_like_user_result(pageNum,uname){
	$.ajax({
		url:$("#e_heard").val()+"/getWorkerByName.do",
		data:{"pageNum":pageNum,"name":uname},
		type:"get",
		success:function(result){
			if(result.code==100){
				//解析模糊查询的结果集
				var users = result.map.pageInfo.list;
				cr_user_admin(users);
				var pageInfo = result.map.pageInfo;
				get_users_pageInfo(pageInfo);
				get_users_nav(pageInfo);
			}else{
				$("#sl_input").val("");
				alert(result.map.msg);
			}
		}
	});
}

/**
 * 检测用户名是否存在,存在返回false,否则返回true
 * @param uname 一个名字
 * @returns true/false
 */
function check_uname(uname){
	var flag=false;
	$.ajax({
		url:$("#e_heard").val()+"/checkuname.do",
		data:"uname="+uname,
		type:"get",
		async:false,
		success:function(result){
			if(result.code==200){
				flag=true;
			}
		}
	});
	return flag;
}

//創建用戶表基礎信息內容
	function cr_user_admin(result){
		//清空tobdy內容
		$("#user_admin table tbody").empty();
		//創建tbody內容
		$.each(result, function(index, item){
			var u_check_box = $("<td></td>").append($("<input type='checkbox' class='worker_ck' num='"+item.u_id+"'></input>"));
			//var u_id_td = $("<td></td>").append(item.u_id);
			var u_name_td = $("<td></td>").append(item.u_name);
			var u_lock_btn = $("<td></td>").append($("<button></button>").append(item.u_islock == 1 ? "锁定" : "解锁").addClass(item.u_islock == 1 ? "locking_btn" : "locking_btn active").attr("status",item.u_islock == 1 ? "1":"2"));
			var useTable = $("<td>"+(item.isSu == 1 ? item.u_use_table : "-")+"</td>");
			var isSu = $("<td></td>").append($("<button></button>").append(item.isSu == 1 ? "是" : "否"));
			
			var u_sel_btn = $("<td></td>").append($("<button>修改密碼</button>").addClass("up_password_btn"))
			.append(" | ").append($("<button>刪除</button>").addClass("delete_one_btn"));
			if($("#data_group").attr("data-is-su"))
			u_sel_btn.append(" | ").append($("<button data-url='../v3/game_info.html?uid="+item.u_id+"'>查看后台表</button>").addClass("look_btn"))
			.append(" | ").append($("<button data-url='../v3/game_history.html?uid="+item.u_id+"'>查看历史记录汇总表</button>").addClass("look_btn"))
			.append(" | ").append($("<button data-url='../v3/game_total.html?uid="+item.u_id+"'>查看汇总表</button>").addClass("look_btn"));
			var u_tr = $("<tr></tr>").append(u_check_box).append(u_name_td).append(u_lock_btn).append(useTable)
			.append(isSu).append(u_sel_btn);
			$("#user_admin table tbody").append(u_tr);
		});
		$(".worker_ck").click(function(){
			$(".worker_ck:checked").length==$(".worker_ck").length?$("#select_all_worker").prop("checked",true):$("#select_all_worker").prop("checked",false)
		})
		//刪除綁定
		$(".delete_one_btn").click(function(){
			var u_num = $(this).parents("tr").find(".worker_ck").eq(0).attr("num");
			delete_one(u_num);
		});
		
		//鎖定綁定
		$(".locking_btn").click(function () {
			get_user_lock($(this));
		});
		//修改密碼綁定
		$(".up_password_btn").click(function(){
			var u_num = $(this).parents("tr").find(".worker_ck").eq(0).attr("num");
			//修改密码模态框
			$("#update_u_password_modal").modal({
				backdrop : "static"
			});
			$("#update_u_password_input").val("");
			$("#update_u_password_form .error").eq(0).css("display","none");
			$("#update_password_num").val(u_num);
		});
		//查看信息綁定
		$(".look_btn").click(function(e){
			$("#user_detail").show().siblings(".con_item").hide();
			$("iframe[name=user_detail_iframe]").attr("src",$(e.target).attr("data-url"));
			/*var user_num = $(this).parents("tr").find(".worker_ck").eq(0).attr("num");
			check_user_data(user_num,1);*/
		});
		
	}
	

	//查詢某個用戶數據
	function check_user_data(num,pageNum){
		var model = $("#user_data_details select").val();
		$.ajax({
			url:$("#e_heard").val()+"/workerById.do",
			data:{"u_num":num,"pageNum":pageNum,"model":model},
			type:"POST",
			success:function(result){
				if(result.code==100){
					var user_data = result.map.list;
					var u_num = result.map.worker.u_id;
					var u_name = result.map.worker.u_name;
					var u_table_num = result.map.worker.u_use_table;
					//創建表
					$("#check_user_news span:eq(0)").empty().append(u_num);
					$("#check_user_news span:eq(1)").empty().append(u_name);
					$("#check_user_news span:eq(2)").empty().append(u_table_num);
					$("#check_table_one_title span").empty().append((pageNum-1)*3+1);
					$("#check_table_two_title span").empty().append((pageNum-1)*3+2);
					$("#check_table_three_title span").empty().append((pageNum-1)*3+3);
					//将用户信息解析到表中
					insert_user_data(user_data,model);
				}
			},
		});
	}

	//插入用戶工作內容
	function insert_user_data(result,model){
		var tbody_arr = $("#user_data_details tbody");
		var tr_arr = tbody_arr.find("tr");
		$.each(tr_arr,function(tr_index,tr_item){
			if(tr_index%4==0){
				//sheng
				$(tr_item).find("td").slice(2,$(tr_item).find("td").length).empty();
			}else if(tr_index%4==1){
				//pei
				$(tr_item).find("td").slice(1,$(tr_item).find("td").length).empty();
			}else if(tr_index%4==2){
				//dui
				$(tr_item).find("td").slice(1,$(tr_item).find("td").length).empty().removeClass();
			}else if(tr_index%4==3){
				//gong
				$(tr_item).find("td").slice(2,$(tr_item).find("td").length).empty().removeClass();
			}
		});
		$.each(result,function(re_index,re_item){
			var tr_arr = tbody_arr.eq(re_index).find("tr");
			//解析生數據
			var s_data = re_item.sheng;
			if(s_data!=null){
				var s_data_arr = s_data.split(",");
				$.each(s_data_arr,function(s_index,s_item){
					var td_s_arr = tr_arr.eq(s_index*4).find("td");
					for(var s_td_num=0;s_td_num<s_item.length;s_td_num++){
						td_s_arr.eq(s_td_num+2).text(s_item.substring(s_td_num,s_td_num+1));
					}
				});
			}
			var p_data = re_item.pei;
			if(p_data!=null){
				var p_data_arr = p_data.split(",");
				$.each(p_data_arr,function(p_index,p_item){
					var td_p_arr = tr_arr.eq(p_index*4+1).find("td");
					for(var p_td_num=0;p_td_num<p_item.length;p_td_num++){
						td_p_arr.eq(p_td_num+1).text(p_item.substring(p_td_num,p_td_num+1));
					}
				});
			}
			var d_data = re_item.dui;
			if(d_data!=null){
				var d_data_arr = d_data.split(",");
				$.each(d_data_arr,function(d_index,d_item){
					var td_d_arr = tr_arr.eq(d_index*4+2).find("td");
					for(var d_td_num=0;d_td_num<d_item.length;d_td_num++){
						td_d_arr.eq(d_td_num+1).text(d_item.substring(d_td_num,d_td_num+1)==1?"√":"×");
					}
				});
			}
			var g_data = re_item.gong;
			if(g_data!=null){
				var g_data_arr = g_data.split(",");
				$.each(g_data_arr,function(g_index,g_item){
					var td_g_arr = tr_arr.eq((g_index+1)*4+3).find("td");
					for(var g_td_num=0;g_td_num<g_item.length;g_td_num++){
						td_g_arr.eq(g_td_num+2).text(g_item.substring(g_td_num,g_td_num+1));
					}
				});
			}
			var g_col_data = re_item.gong_col;
			if(g_col_data!=null){
				var g_col_data_arr = g_col_data.split(",");
				$.each(g_col_data_arr,function(g_col_index,g_col_item){
					var td_g_col_arr = tr_arr.eq((g_col_index+1)*4+3).find("td");
					for(var g_col_td_num=0;g_col_td_num<g_col_item.length;g_col_td_num++){
						td_g_col_arr.eq(g_col_td_num+2).addClass(g_col_item.substring(g_col_td_num,g_col_td_num+1)==1?"":"bg_r");
					}
				});
			}
			var c_data = re_item.count;
			var c_sum_data = re_item.count_sum;
			var a_sum_data = re_item.actual_sum;
			if(c_data!=null){
				var c_sum_data_arr = c_sum_data.split(",");
				var a_sum_data_arr = a_sum_data.split(",")
				var c_data_arr = c_data.split(",");
				$.each(c_data_arr,function(c_index,c_item){
					tr_arr.eq((c_index+1)*4).find("td").eq(8).append(c_sum_data_arr[c_index]);
					tr_arr.eq((c_index+1)*4).find("td").eq(9).append(a_sum_data_arr[c_index]);
					var td_c_arr = tr_arr.eq((c_index+1)*4+2).find("td");
					td_c_arr.eq(13).append(c_item);
					if(c_index==0){
						td_c_arr.eq(14).append(c_item);
					}else{
						if(model==1){
							if(tr_arr.eq((c_index)*4+2).find("td").eq(13).text()<=0){
								td_c_arr.eq(14).addClass("bg_g");
							}else{
								td_c_arr.eq(14).append(c_item);
							}
						}else{
							if(tr_arr.eq((c_index)*4+2).find("td").eq(13).text()>=0){
								td_c_arr.eq(14).addClass("bg_g");
							}else{
								td_c_arr.eq(14).append(c_item);
							}
						}
					}
				});
			}
		});
	}

//	生成空表結構
	function cr_user_tb() {
		//獲取tbody
		var tbody_arr = $("#user_data_details table tbody");
		/* 創建tbody開始 */
		//ps：第一張表的前3場是有字的
		$.each(tbody_arr, function(index_tbody, tobdy) {
			//創建出162行的表
			for (var tr_num = 1; tr_num <= 162; tr_num++) {
				//創建生的tr_s
				var tr_s = $("<tr></tr>").addClass("g_int");
				/* 生成tr_s內容開始 */
				for (var td_s_num = 0; td_s_num < 10; td_s_num++) {
					var td_s;
					if (index_tbody == 0 && tr_num == 1 && td_s_num == 0) {
						td_s = $("<td></td>").prop("rowspan", "3")
						.addClass("cell_row").addClass("bg_y")
						.append("全不計");
					} else if (index_tbody == 0 && tr_num == 2
							&& td_s_num == 0) {
						td_s = $("<td></td>").prop("rowspan", "3")
						.addClass("cell_row").addClass("bg_y")
						.append("單組計");
					} else if (index_tbody == 0 && tr_num == 3
							&& td_s_num == 0) {
						td_s = $("<td></td>").prop("rowspan", "3")
						.addClass("cell_row").addClass("bg_y")
						.append("實統計");
					} else if (td_s_num == 0) {
						td_s = $("<td></td>").prop("rowspan", "3")
						.addClass("cell_row");
					} else if (td_s_num == 1) {
						td_s = $("<td></td>").append("生");
					} else if (td_s_num == 8 || td_s_num == 9) {
						td_s = $("<td></td>").prop("rowspan", "2");
					} else {
						td_s = $("<td></td>").prop("colspan", "2")
						.addClass("td_s");
					}
					tr_s.append(td_s);
				}
				/* 生成tr_s內容結束 */
				//創建配tr_p
				var tr_p = $("<tr></tr>");
				/* 生成tr_p內容開始   */
				for (var td_p_num = 0; td_p_num < 7; td_p_num++) {
					var td_p;
					if (td_p_num == 0) {
						td_p = $("<td></td>").append("配");
					} else {
						td_p = $("<td></td>").prop("colspan", "2");
					}
					tr_p.append(td_p);
				}
				/* 生成tr_p內容結束  */

				//創建兌tr_d
				var tr_d = $("<tr></tr>");
				/* 生成tr_d內容開始 
				 */
				for (var td_d_num = 0; td_d_num < 15; td_d_num++) {
					var td_d;
					if (td_d_num == 0) {
						td_d = $("<td></td>").append("兌");
					} else if (td_d_num == 13 || td_d_num == 14) {
						td_d = $("<td></td>").prop("rowspan", "2");
					} else {
						td_d = $("<td></td>");
					}
					tr_d.append(td_d);
				}
				/* 生成tr_d內容結束 */
				//創建供tr_g
				var tr_g = $("<tr></tr>");
				/* 生成tr_d內容開始
				 */
				//編號生成
				var seq = "" + tr_num;
				var seq_td;
				switch (seq.length) {
				case 1:
					seq_td = "00" + seq;
					break;
				case 2:
					seq_td = "0" + seq;
					break;
				default:
					seq_td = seq;
				}
				for (var td_g_num = 0; td_g_num < 14; td_g_num++) {
					var td_g;
					if (td_g_num == 0) {
						td_g = $("<td></td>").addClass("g_col_num").append(
								seq_td);
					} else if (td_g_num == 1) {
						td_g = $("<td></td>").append("供");
					} else {
						td_g = $("<td></td>");
					}
					tr_g.append(td_g);
				}
				$(tobdy).append(tr_s).append(tr_p).append(tr_d)
				.append(tr_g);
			}
		});
	}

//	創建導航條
	function cr_user_nav_btn() {
		//發送請求獲取導航信息,總共多少條數據
		$.ajax({
			url : $("#e_heard").val()+"/getDataCount.do",
			success : function(result) {
				//console.log(result);
				//解析並生成導航按鈕
				var num = result.map.data_count;
				for (var nav_num = 1; nav_num <= num; nav_num++) {
					var nav_a_text_head = "" + ((nav_num - 1) * 3 + 1);
					var nav_a_text_foot = "" + nav_num * 3;
					var nav_a_text = (nav_a_text_head.length < 2 ? "0"
							+ nav_a_text_head : nav_a_text_head)
							+ "-"
							+ (nav_a_text_foot.length < 2 ? "0"
									+ nav_a_text_foot : nav_a_text_foot);
					var nav_a = $("<a href='javascript:;'></a>").append(
							nav_a_text).addClass("check_nav_btn").attr("page",
									nav_num);
					$("#check_nav_box").append(nav_a).append(" ");
				}
				$("#check_nav_box .check_nav_btn:eq(0)").addClass("check_cr_page");
				//給導航按鈕綁定事件
				$("#check_nav_box .check_nav_btn").on("click", function() {
					$("#check_nav_box .check_nav_btn").removeClass("check_cr_page");
					$(this).addClass("check_cr_page");
					var pageNum = $(this).attr("page");
					var u_num = $("#check_user_news span:eq(0)").text();
					check_user_data(u_num,pageNum);
					//滾動條事件
					$('.layui-body').animate({scrollTop: '0px'}, 100);
				});
			}
		});
	}


//	給解/鎖的方法
	function get_user_lock(ele){
		var user_status = ele.attr("status");
		var u_num = ele.parents("tr").find(".worker_ck:eq(0)").attr("num");
		$.ajax({
			url:$("#e_heard").val()+"/lockWorker.do",
			data:{"uid":u_num,"lock_status":user_status},
			type:"POST",
			success:function(result){
				if(result.code==100){
					if (ele.hasClass("active")) {
						ele.removeClass("active").attr("status","1").text("锁定")
					} else{
						ele.addClass("active").attr("status","2").text("解锁")
					}
				}
			},
		});
	}

//	解析分頁信息
	function get_users_pageInfo(result){
		$("#pageInfo_area").empty();
		$("#pageInfo_area").append(
				"當前第" + result.pageNum + "頁,總共"
				+ result.pages + "頁,總共"
				+ result.total + "條記錄");
	}

//	解析分頁條
	function get_users_nav(result){
		var name = $("#sl_input").val().trim();
		$("#pageNav_area").empty();
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
				if(name==""){
					get_users_page(1);
				}else{
					$("#query_box_name input[name=username]:eq(0)").val(name);
					get_like_user_result(1,name);
				}
					
			});
			prePageLi.click(function() {
				if(name==""){
					get_users_page(result.pageNum - 1);
				}else{
					$("#query_box_name input[name=username]:eq(0)").val(name);
					get_like_user_result(result.pageNum - 1,name);
				}
			});
		}
		if (result.isLastPage == true) {
			lastPageLi.addClass("disabled");
			nextPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				if(name==""){
					get_users_page(result.pageNum + 1);
				}else{
					$("#query_box_name input[name=username]:eq(0)").val(name);
					get_like_user_result(result.pageNum + 1,name);
				}
					
			});
			lastPageLi.click(function() {
				if(name=""){
					get_users_page(result.pages);
				}else{
					$("#query_box_name input[name=username]:eq(0)").val(name);
					get_like_user_result(result.pages + 1,name);
				}
					
			});
		}
		ul.append(firstPageLi).append(prePageLi);
		$.each(result.navigatepageNums, function(index, item) {
			var numLi = $("<li></li>").append(
					$("<a></a>").append(item).attr("href", "javascript:;"));
			if (result.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				if(name==""){
					get_users_page(item);
				}else{
					$("#query_box_name input[name=username]:eq(0)").val(name);
					get_like_user_result(item,name);
				}
			});
			ul.append(numLi);
		});
		ul.append(nextPageLi).append(lastPageLi);
		nav.append(ul).appendTo("#pageNav_area");
	}

//	發送請求查詢用戶數據,跳轉到某一頁的方法
	function get_users_page(pageNum){
		$.ajax({
			url:$("#e_heard").val()+"/allWorker.do",
			data:"pn="+pageNum,
			type:"GET",
			success:function(result){
				if(result.code == 100){
					var users = result.map.pageInfo.list;
					cr_user_admin(users);
					var pageInfo = result.map.pageInfo;
					get_users_pageInfo(pageInfo);
					get_users_nav(pageInfo);
					totalNum = result.map.pageInfo.total;
				}
			}
		});

		//格式时间方法
		Date.prototype.Format = function(fmt) {
			var o = {
					"M+" : this.getMonth() + 1, //月份 
					"d+" : this.getDate(), //日 
					"h+" : this.getHours(), //小时 
					"m+" : this.getMinutes(), //分 
					"s+" : this.getSeconds(), //秒 
					//季度
					"q+" : Math.floor((this.getMonth() + 3) / 3),
					"S" : this.getMilliseconds()
					//毫秒 
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
							: (("00" + o[k]).substr(("" + o[k]).length)));
			return fmt;
		}
		
		$("#user_data_details select").on("change",function(){
			var h_text = $(this).find("option:selected").text();
			$("#user_data_details h2").eq(0).empty().append(h_text);
			var num = $("#user_data_details p span").eq(0).text();
			var pageNum = $(".check_cr_page").eq(0).attr("page");
			check_user_data(num,pageNum);
		});
	}
