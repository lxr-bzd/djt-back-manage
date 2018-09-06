
Date.prototype.format = function (format) 
{
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(),    //day 
        "h+": this.getHours(),   //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
        "S": this.getMilliseconds() //millisecond 
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
      RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}



var $app = (function(){
	var App = {
		loginUrl:'/login.html'
		,indexUrl:'/index.html'
		,logoutUrl:''
			};

	App.logout = function(){
		var indexurl = this.indexUrl;
		this.request(this.logoutUrl,function(){
			
			window.location.href = indexurl;
		});
		
	}
	
	App.getContext = function(){
		
		var context = window;	
		if(window.top)context = window.top;
		return context;
	}

	App.request = function(url,call,param){
		
		var p = param.param;
		
		$.ajax({ 
			type: "post", 
			dataType:"json",
			url: url, 
			data:p,	
			beforeSend: function(XMLHttpRequest){ 
			
			}, 
			success: function(data, status){
				
				call(data,status);
			}, 
			complete: function(XMLHttpRequest, textStatus){ 
			if(param&&param.complete)
				param.complete(XMLHttpRequest, textStatus);
			}, 
			error: function(){ 
				if(param&&param.error)param.error();
			} 
			}); 
		
	}
	
App.ajaxSubmit = function(form,url,call,param){
		form = $(form);
		var p = getReqParam(form.serializeObject(),1);
		
		
		 form.ajaxSubmit({ 
			type: "post", 
			dataType:"json",
			url: url, 
			data:p,	
			beforeSend: function(XMLHttpRequest){ 
			
			}, 
			success: function(data, status){
				if(data&&data.code==5)window.location.href = $app.loginUrl+"?from="+encodeURIComponent(window.location.href);
				if(data.sid)setCookie("sysSid",data.sid);
				call(data,status);
			}, 
			complete: function(XMLHttpRequest, textStatus){ 
			if(param&&param.complete)
				param.complete(XMLHttpRequest, textStatus);
			}, 
			error: function(){ 
				if(param&&param.error)param.error();
			} 
			}); 
		
	}
	
	

	
	
	function getCookie(name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	}
	
	function setCookie(name,value)
	{
	var Days = 3;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
	}
	
	
	
	App.fmtDate = function(t,f){
		if(isNaN(t))return "";
		if(!f)f = "yyyy-MM-dd"
		return new Date(t).format(f);
		
	}
	
	App.getQueryStr = function(name){ 
		var vars = [], hash; 
		var i = window.location.href.indexOf('?');
		if(i<0)return;
		var ii = window.location.href.indexOf('#');
		if(ii<0)ii = undefined;
		var hashes = window.location.href.slice(i+1,ii).split('&'); 
		for(var i = 0; i < hashes.length; i++) { 
		hash = hashes[i].split('='); 
		vars.push(hash[0]); 
		vars[hash[0]] = hash[1]; 
		} 
		return vars[name]; 
		}
	
	return App;
	
})();


/* 表单序列化 */
$.fn.serializeObject = function() {  
    var o = {};  
    var a = this.serializeArray();  
    $.each(a, function() {  
        if (o[this.name]) {  
            if (!o[this.name].push) {  
                o[this.name] = [ o[this.name] ];  
            }  
            o[this.name].push(this.value || '');  
        } else {  
            o[this.name] = this.value || '';  
        }  
    });  
    return o;  
} 
