$(function(){
	cr_get_s_tb();
	cr_nav_get_s_btn()
	$("#back_admin_gets").click(function(){
		to_page(1,null);
		//插入表的張數
		var table = $("#s_table_num");
		insert_s_num(table);
	});
	/*$("#s_table_num").change(function(){
		var tableNum = $(this).val();
		alert(tableNum);
	});*/
});

//查詢表張數方法
function insert_s_num(table){
	$.ajax({
		url: $("#e_heard").val()+"/new/getable.do",
		data:"",
		type:"GET",
		success:function(result){
			if(result.code==100){
				table.empty();
				$.each(result.map.table,function(index_table,item_table){
					$("<option></option>").append("表"+(index_table+1)).prop("value",item_table.dTableId).appendTo(table)
				});
				table.change(function(){
					var num = $(this).eq(0).val();
					to_page(1,num);
				});
			}
		}
	});
	
}
//生成空表結構
function cr_get_s_tb() {
	//獲取tbody
	var tbody_arr = $("#backs-table table tbody");
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

//創建導航條
function cr_nav_get_s_btn() {
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
						nav_a_text).addClass("nav_btn").attr("page",
								nav_num);
				$("#nav_box").append(nav_a).append(" ");
			}
			$("#nav_box .nav_btn:eq(0)").addClass("cr_page");
			//給導航按鈕綁定事件
			$("#nav_box .nav_btn").on("click", function() {
				$("#nav_box .nav_btn").removeClass("cr_page");
				$(this).addClass("cr_page");
				var pageNum = $(this).attr("page");
				var tableNum = $("#s_table_num").val();
				//alert(tableNum);
				to_page(pageNum,tableNum);
				$('.layui-body').animate({scrollTop: '0px'}, 100);
			});
		}
	});
}

//加載第pageNum頁的頁面方法
function to_page(pageNum,tableNum) {
	//發送相應的頁面至後臺
	$.ajax({
		url : $("#e_heard").val()+"/new/selectData.do",
		//data : "pageNum=" + pageNum+"&tableNum="+tableNum,
		data : {"pageNum":pageNum,"tableNum":tableNum},
		type : "POST",
		success : function(result) {
			if (result.code == 100) {
				var data = result.map.pageInfo.list;
				//在指定位置上將數據添加進去
				insert_data(data);
			} else {
				alert("所查頁面有誤！");
			}
		}
	});
}



//插入數據進入表方法
function insert_data(result) {
	//分解信息
	$.each(result, function(result_num, item_re) {
		//分析头部信息第幾組
		var head_data = item_re.d_id;
		//alert(head_data);
		//插入頭部信息
		var tr_s_arr;
		switch (result_num) {
		case 0:
			//將頭部信息插入第一張表
			$("#table_one_title span").empty().append(head_data);
			tr_s_arr = $("#backs-table #table_one .g_int");
			break;
		case 1:
			$("#table_two_title span").empty().append(head_data);
			tr_s_arr = $("#backs-table #table_two .g_int");
			break;
		case 2:
			$("#table_three_title span").empty().append(head_data);
			tr_s_arr = $("#backs-table #table_three .g_int");
		}
		//頭部信息結束
		//生數據信息填入開始
		var s_data_table = item_re.d_data.split(",");
		$.each(s_data_table, function(index_tr, s_data_tr) {
			var td_s_arr = tr_s_arr.eq(index_tr).children(".td_s");
			var s_data_td = s_data_tr.split("");
			$.each(s_data_td, function(index_td, s_data_td) {
				td_s_arr.eq(index_td).text(s_data_td);
			});
		});

	});
}

//創建導航條
/*function cr_nav_btn() {
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
						nav_a_text).addClass("nav_btn").attr("page",
								nav_num);
				$("#nav_box").append(nav_a).append(" ");
			}
			$(".nav_btn:eq(0)").addClass("cr_page");
			//給導航按鈕綁定事件
			$(".nav_btn").on("click", function() {
				$(".nav_btn").removeClass("cr_page");
				$(this).addClass("cr_page");
				var pageNum = $(this).attr("page");
				var tableNum = $("#s_table_num").val();
				alert(1111111);
				to_page(pageNum,tableNum);
				$('.layui-body').animate({scrollTop: '0px'}, 100);
			});
		}
	});
}*/