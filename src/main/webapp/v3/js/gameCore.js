var gameCore = {
			HU_NUM:20
			,ITEM_LENGTH:4
		,queue:function(scan,gong){
			var scans = scan.split(",");
			var gongs = gong.split(",");
			
			var tempRet = [];
			for (var i = 0; i < scans.length; i++) {
				for (var j = 0; j < this.HU_NUM; j++) {
					var item = scans[i].substring(j*this.ITEM_LENGTH,j*this.ITEM_LENGTH+4);
					var nv = gongs[i].substring(j,j+1)
					var num = new Number(item.substring(1,4));
					if(num>=11){
						var h = j%2!=0?j/2+0.5:j/2+1;
						if(!tempRet[h])tempRet[h] = [];
						tempRet[h].push({hu:h,
							nv:nv,
							zf:item.substring(0,1),
							num:num-10})
						
					}
				}
			}
			
			var ret = [];
			for (var i = 0; i < tempRet.length; i++) {
				if(tempRet[i])
				ret = ret.concat(tempRet[i]);
			}
			
			return ret;
			
		}
		
	
	,tg:function(queue,gong,rule){
		var info = {
				'老+':{v:0},'少+':{v:0},
				'老-':{v:0},'少-':{v:0},
				'男+':{v:0},'女+':{v:0},
				'男-':{v:0},'女-':{v:0},
		}
		var qs = queue.split(",");
		var gs = gong.split(",");
		var start = new Number(rule.split(",")[0]);
		var end = new Number(rule.split(",")[1]);
		for (var i = 0; i < qs.length; i++) {
			
			for (var j = 0; j < this.HU_NUM; j++) {
				var q1 = qs[i].substring(j*this.ITEM_LENGTH,j*this.ITEM_LENGTH+4);
				var g1 = gs[i].substring(j,j+1);
				info[g1+q1.substring(0,1)].v+=getJg(new Number(q1.substring(1,this.ITEM_LENGTH)),start,end);
			}
			
		}
		
		var lsb1 = getHz(info['老+'],info['少+'],true);
		var lsb2 = getHz(info['老-'],info['少-'],false);
		var nnb1 = getHz(info['男+'],info['女+'],true);
		var nnb2 = getHz(info['男-'],info['女-'],false);
		
		var lsbg = {k:lsb1+lsb2>0?'老':(lsb1+lsb2<0?'少':''),v:Math.abs(lsb1+lsb2)}
		var nnbg = {k:nnb1+nnb2>0?'男':(nnb1+nnb2<0?'女':''),v:Math.abs(nnb1+nnb2)}
		
		
		/**
		返回正反报
		*/
		function getHz(q1,q2,isQf){
			if(q1.v>q2.v){
				q1.hz=q1.v-q2.v;
				return isQf?-q1.hz:q1.hz;
			}
			if(q2.v>q1.v){
				q2.hz=q2.v-q1.v;
				return isQf?q2.hz:-q2.hz;
			}
			
			return 0;
			
		}
		
		function getJg(length,start,end) {
			var isMinus = false;
			if(length>=start&&length<end) {
				var l = (length*Math.pow(2,length-start));
				
				return isMinus?-l:l;
			}
				
			else return 0;
			
		}
		
		
		return {lsbg:lsbg,nnbg:nnbg,info:info};
		
		
	}
	
			
			
	}