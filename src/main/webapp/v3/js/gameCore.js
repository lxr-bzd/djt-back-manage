	var gameCore = {
			HU_NUM:20
			,ITEM_LENGTH:4
		,queue:function(scan,gong){
			var scans = scan.split(",");
			var gongs = gong.split(",");
			var ret = [];
			for (var i = 0; i < scans.length; i++) {
				for (var j = 0; j < this.HU_NUM; j++) {
					var item = scans[i].substring(j*this.ITEM_LENGTH,j*this.ITEM_LENGTH+4);
					var nv = gongs[i].substring(j,j+1)
					if(new Number(item.substring(1,4))>=11){
						ret.push({hu:j%2!=0?j/2+0.5:j/2+1,
							nv:nv,
							zf:item.substring(0,1),
							num:new Number(item.substring(1,3))})
						
					}
				}
			}
			
			return ret;
			
		}	
	}