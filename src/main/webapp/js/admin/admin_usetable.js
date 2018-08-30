$(function(){
	$("#back_admin_usetable").click(function(){
		cr_use_table();
	});
});
//創建使用的table
function cr_use_table(){
	var table = $("#usetable_admin tbody");
	$.ajax({
		url:$("#e_heard").val()+"/usetable/getusetable.do",
		type:"get",
		success:function(result){
			if(result.code==100){
				table.empty();
				$.each(result.map.list,function(){
					var tr = $("<tr></tr>");
					var td_id = $("<td></td>").append(this.dTableId);
					var td_use_status = $("<td></td>").append(this.dUseDefault==1?'使用中':'準備中');
					var td_button = $("<td></td>").append($("<button>"+(this.dUseDefault==1?'使用中':'使用')+"</button>").addClass(this.dUseDefault==1?'locking_btn active':'prepare'));
					table.append(tr.append(td_id).append(td_use_status).append(td_button));
				});
				$(".prepare").click(function(){
					var table_id = $(this).parents("tr").find("td").eq(0).text();
					change_use_table(table_id);
				});
			}
		}
	});
}

//修改使用的表
function change_use_table(table_id){
	$.ajax({
		url:$("#e_heard").val()+"/usetable/usetableById.do",
		data:"tableId="+table_id,
		type:"get",
		success:function(result){
			alert(result.code);
			if(result.code==100 || result.code==200){
				cr_use_table();
				if(result.code==200){
					alert(result.map.msg);
				}
			}
		}
	});
}