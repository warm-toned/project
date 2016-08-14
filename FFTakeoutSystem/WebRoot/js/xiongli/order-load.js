$(function(){
	var uuid = $("#infos").attr("uuid");
	$.post("pay!loadOrderInfo.action?uuid="+uuid,function(data){
		var json = eval(data);
		$(".orderInfo").remove();
		var main = $("#main");
		var sum = 0;
		for ( var i = 0; i < json.length; i++) {
			var lsum = json[i].muprice*json[i].ocount;
			if(i==0){
				if(json[0].ostatus==1){
					$("#orderStatus").text("付款超时，订单关闭");
					$("#close").empty().append("<span class='bgBorder'>交易未完成</span>");
				}else if(json[0].ostatus<=3){
					$("#orderStatus").text("等待商家接单");
					$(".statusSpan").removeClass("bgBorder").removeClass("bgNormalBorder");
					$(".statusSpan:eq(0)").addClass("bgBorder");
					$(".statusSpan:not(:eq(0))").addClass("bgNormalBorder");
				}else if(json[0].ostatus==4){
					$("#orderStatus").text("配送中...");
					$(".statusSpan").removeClass("bgBorder").removeClass("bgNormalBorder");
					$(".statusSpan:eq(1)").addClass("bgBorder");
					$(".statusSpan:not(:eq(1))").addClass("bgNormalBorder");
				}else if(json[0].ostatus==5){
					$("#orderStatus").text("已送达");
					$(".statusSpan").removeClass("bgBorder").removeClass("bgNormalBorder");
					$(".statusSpan:eq(2)").addClass("bgBorder");
					$(".statusSpan:not(:eq(2))").addClass("bgNormalBorder");
				}
				$("#orderDate").text(json[0].ordertime);
				$("#rtpic").attr("src","/ffts/"+json[0].rtpic);
				$("#rtname").text(json[0].rtname);
			}
			var span1 = $("<span class='pull-right' style='padding-right:10px;width:60px;text-align:right;'>￥ : "+lsum+"</span>");
			var span2 = $("<span class='pull-right' style='padding-right:10px;'>"+json[i].ocount+"</span>");
			var span3 = $("<span class='pull-left' style='padding-left:10px;'>"+json[i].muname+"</span>");
			var td = $("<td></td>").append(span3).append(span1).append(span2);
			var tr = $("<tr class='orderInfo'></tr>").append(td);
			main.append(tr);
			sum = sum + lsum;
		}
		var span1 = $("<span class='pull-right' style='padding-right:10px;width:60px;text-align:right;'>￥ : "+sum+"</span>");
		var span3 = $("<span class='pull-left' style='padding-left:10px;'>总计</span>");
		var td = $("<td></td>").append(span3).append(span1);
		var tr = $("<tr class='orderInfo' style='border-bottom:1px #ddd solid;'></tr>").append(td);
		main.append(tr);
	});
});

