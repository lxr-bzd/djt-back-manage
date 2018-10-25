$(function(){
	cr_null_up_table();
	//cr_nav_up_btn();
	cr_select_box();
	$("#back_admin_update").click(function(){
		to_page_get_data(1,null);
		var table = $("#up_query_box_m");
		up_s_num(table);
	});
	$("#save_data").click(function(){
		save_data();
	});
	$("#save_data_foot").click(function(){
		save_data();
	});
	$("#up_query_box_btn").click(function(){
		to_select_num();
	});
})

//查詢方法
function to_select_num(){
	var tb_num = $("#up_query_box_z select").eq(0).val();
	var row_num = $("#up_query_box_x select").eq(0).val();
	var line_num = $("#up_query_box_h select").eq(0).val();
	var table = $("#up_query_box_m").eq(0).val();
	/*var table_   插入一個表號*/
	to_page_get_data(Math.ceil(tb_num/3),table);
	var tbody = $("#backs_update_table table tbody").eq((tb_num-1)%3);
	tbody.find("tr").eq(row_num-1).find("td").eq(line_num).find("input").eq(0).focus();
}

//给查詢欄植入相應數字
function cr_select_box(){
	for(var up_z=1;up_z<=102;up_z++){
		$("#up_query_box_z select").eq(0).append($("<option value='"+up_z+"'>"+up_z+"組</option>"));
	}
	for(var up_x=1;up_x<=162;up_x++){
		$("#up_query_box_x select").eq(0).append($("<option value='"+up_x+"'>"+up_x+"行</option>"));
	}
	for(var up_h=1;up_h<=6;up_h++){
		$("#up_query_box_h select").eq(0).append($("<option value='"+up_h+"'>"+up_h+"戶</option>"));
	}
}

//查詢表張數方法
function up_s_num(table){
	$.ajax({
		url: $("#e_heard").val()+"/new/getable.do",
		data:"",
		type:"GET",
		success:function(result){
			if(result.code==100){
				//var table = $("#s_table_num");
				table.empty();
				$.each(result.map.table,function(index_table,item_table){
					//alert(item_table);
					$("<option></option>").append("表"+(index_table+1)).prop("value",item_table.dTableId).appendTo(table)
				});
				table.change(function(){
					//alert($(this).eq(0).val());
					to_page_get_data(1,$(this).eq(0).val());
					$("#up_query_box_z select").eq(0).val(1);
				});
			}
		}
	});
}

//生成空表格
function cr_null_up_table(){
	var tbody_arr = $("#backs_update_table table tbody");
	$.each(tbody_arr,function(tbody_num,tbody_item){
		for(var tr_num = 0;tr_num<162;tr_num++){
			var update_tr = $("<tr></tr>").addClass("g_int");
			var num = ""+(tr_num+1);
			var num_td;
			switch(num.length){
			case 1:
				num_td = "00"+num;
				break;
			case 2:
				num_td = "0"+num;
				break;
			default:
				num_td = num;
			}
			update_tr.append($("<td></td>").append(num_td));
			for(var td_num = 0;td_num<6;td_num++){
				var td = $("<td></td>").append("<input type='text' maxlength='1'></input>");
				update_tr.append(td);
			}
			$(tbody_item).append(update_tr);
		}
		$(".g_int input").on('input propertychange', function() {
			this.value = this.value.replace(/[^1234]/g, '')
		});
	});
}

//創建訪問，獲取數據
function to_page_get_data(pageNum,tableNum){
	$.ajax({
		url:$("#e_heard").val()+"/new/selectData.do",
		data:{"pageNum":pageNum,"tableNum":tableNum},
		type:"POST",
		success:function(result){
			if(result.code==100){
				//將獲取的數據填入對應的表中
				var data = result.map.pageInfo.list;
				insert_data_to_table(data);
			}
		}
	});
}

//將數據填入空表中
function insert_data_to_table(result){
	$.each(result,function(result_index,tbody_data){
		var head_data = tbody_data.d_id;
		//某張表的tr數組
		var insert_tbody_tr_arr;
		switch(result_index){
		case 0:
			$("#up_table_one_title span").empty().append(head_data);
			insert_tbody_tr_arr = $("#up_table_one tbody tr");
			break;
		case 1:
			$("#up_table_two_title span").empty().append(head_data);
			insert_tbody_tr_arr = $("#up_table_two tbody tr");
			break;
		default:
			$("#up_table_three_title span").empty().append(head_data);
			insert_tbody_tr_arr = $("#up_table_three tbody tr");
		}
		var tr_data_arr = tbody_data.d_data.split(",");
		$.each(tr_data_arr,function(tr_index,tr_data){
			var td_data_arr = tr_data.split("");
			var insert_tbody_input_arr = insert_tbody_tr_arr.eq(tr_index).find("input");
			$.each(td_data_arr,function(td_index,td_data){
				insert_tbody_input_arr.eq(td_index).empty().val(td_data);
			});
		});
	});
}
//創建導航條
function cr_nav_up_btn() {
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
						nav_a_text).addClass("up_nav_btn").attr("up_page",
								nav_num);
				$("#up_nav_box").append(nav_a).append(" ");
			}
			$("#up_nav_box .up_nav_btn:eq(0)").addClass("up_cr_page");
			$("#up_nav_box .up_nav_btn").on("click", function() {
				$("#up_nav_box .up_nav_btn").removeClass("up_cr_page");
				$(this).addClass("up_cr_page");
				var pageNum = $(this).attr("up_page");
				//=================== 修改 =========================
				var table = $("#up_query_box_m").eq(0).val();
				to_page_get_data(pageNum,table);
				$('.layui-body').animate({scrollTop: '0px'}, 100);
				$("#up_query_box_z select").eq(0).val((pageNum-1)*3+1);
			});
		}
	});
}

//保存方法1:通過數據集保存數據，每組數據是一個對象
function save_data(){
	//獲取table_one中的input數據
	var table_one = $("#up_table_one input");
	var table_two = $("#up_table_two input");
	var table_three = $("#up_table_three input");
	var table_one_data = get_data(table_one);
	if(table_one_data == ""){
		alert("請正確填寫完數據");
		return false;
	}
	var table_two_data = get_data(table_two);
	if(table_two_data == ""){
		alert("請正確填寫完數據");
		return false;
	}
	var table_three_data = get_data(table_three);
	if(table_three_data==""){
		alert("請正確填寫完數據");
		return false;
	}
	var d_tabl_num = $("#up_query_box_m").eq(0).val();
	//alert(d_tabl_num);
	//生成json對象
	var json_one = {d_id:$("#up_table_one_title span").text(),d_data:table_one_data,d_tabl_num:d_tabl_num};
	var json_two = {d_id:$("#up_table_two_title span").text(),d_data:table_two_data,d_tabl_num:d_tabl_num};
	var json_three = {d_id:$("#up_table_three_title span").text(),d_data:table_three_data,d_tabl_num:d_tabl_num};
	var json_array = new Array();
	json_array.push(json_one);
	json_array.push(json_two);
	json_array.push(json_three);
	var db_list = JSON.stringify(json_array);
	$.ajax({
		url:"new/savedata_new.do",
		data:db_list,
		contentType: "application/json; charset=utf-8", 
		dataType:"json",
		type:"POST",
		success:function(result){
			console.log(result);
			if(result.code==100){
				alert("保存成功！");
			}
		}
	});
}
//提取每組input中數據方法
function get_data(ele){
	var table_data="";
	$.each(ele,function(){
		if($(this).val()==""){
			//alert("空值進入");
			table_data="";
			$(this).focus();
			return false;
		}
		table_data += $(this).val();
		var table_data_format = table_data.replace(new RegExp(",","g"),"");
		if(table_data_format.length%6==0){
			table_data += ",";
		}
	});
	if(table_data==""){
		return table_data;
	}else{
		return table_data.substring(0,table_data.length-1);
	}
}