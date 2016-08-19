$(function() {
	var info = $("#pginfo");

	// 导航
	$("li[name='nav']").click(function() {
		$("li[name='nav']").removeClass("active");
		$(this).addClass("active");
		var index = $(this).attr("index");
		if (index == 0) {
			$("#usable").removeClass("hidden-xs");
			$("#got").addClass("hidden-xs");
			$("#complete").addClass("hidden-xs");
		} else if (index == 1) {
			$("#usable").addClass("hidden-xs");
			$("#got").removeClass("hidden-xs");
			$("#complete").addClass("hidden-xs");
		} else {
			$("#usable").addClass("hidden-xs");
			$("#got").addClass("hidden-xs");
			$("#complete").removeClass("hidden-xs");
		}
	});

	// 查询可接订单信息
	getUsableOrders(info);
/*	setInterval(function() {
		//getUsableOrders(info);
	}, 10000);*/

	// 点击切换页码
	$(".pagereact").click(function() {
		var num = $(this).attr("value");
		var actions = $(".pageaction");
		for ( var i = 0; i < actions.length; i++) {
			$(actions[i]).attr("size", num);
		}
		info.attr("size", num);
		info.attr("page", 1);
		$("#pagecount").text("每页显示 " + num + " 条订单");
		getUsableOrders(info);
	});

	// 点击页码
	$(".pageaction").click(function() {
		info.attr("page", $(this).attr("page"));
		getUsableOrders(info);
	});
	
	// 点击切换页码
	$(".page2react").click(function() {
		var num = $(this).attr("value");
		var actions = $(".page2action");
		for ( var i = 0; i < actions.length; i++) {
			$(actions[i]).attr("size2", num);
		}
		info.attr("size2", num);
		info.attr("page2", 1);
		$("#page2count").text("每页显示 " + num + " 条订单");
		getUsableOrders(info);
	});

	// 点击页码
	$(".page2action").click(function() {
		info.attr("page2", $(this).attr("page2"));
		getUsableOrders(info);
	});
});

// 查询所有订单
function getUsableOrders(info) {
	var page = parseInt(info.attr("page"), 10);
	var page2 = parseInt(info.attr("page2"), 10);
	var userid = $("#infos").attr("userid");
	var size = info.attr("size");
	var size2 = info.attr("size2");
	//alert(page+'>>>>>'+size+'|||||'+page2+'>>>>>'+size2);
	$("#pagecount").text("每页显示 " + size + " 条订单");
	$.post("sender!getUsableOrders.action", {
		"page" : page,
		"page2" : page2,
		"size" : size,
		"size2" : size2,
		"userid":userid
	}, function(data) {
		var json = eval("(" + data + ")");
		info.attr("count", json.count);
		// 第一个页码
		$(".pageaction").attr("href", "#").parent("li").removeClass("disabled");
		$(".pageaction:eq(2)").parent("li").addClass("sr-only");
		$(".pageaction:eq(3)").parent("li").addClass("sr-only");
		$(".pageaction:eq(4)").parent("li").addClass("sr-only");
		$(".pageaction:eq(5)").parent("li").addClass("sr-only");
		$(".pageaction:eq(6)").parent("li").addClass("sr-only");
		// 设置页面页码数
		var pages = Math.ceil(json.count / size);
		var start;
		var end;
		// 设置最后一页页码
		$(".pageaction:last").attr("page", pages);
		// 设置上一页下一页是否激活
		if (pages == page) {
			$(".pageaction:eq(7)").attr("page", "").attr("href",
					"javascript:;").parent("li").addClass("disabled");
		} else {
			$(".pageaction:eq(7)").attr("page", (page + 1));
		}
		if (page > 1) {
			$(".pageaction:eq(1)").attr("page", page - 1);
		} else {
			$(".pageaction:eq(1)").attr("page", "").attr("href",
			"javascript:;").parent("li").addClass("disabled");
		}
		if (pages > 5) {
			$(".pageaction:eq(2)").parent("li").removeClass("sr-only");
			$(".pageaction:eq(3)").parent("li").removeClass("sr-only");
			$(".pageaction:eq(4)").parent("li").removeClass("sr-only");
			$(".pageaction:eq(5)").parent("li").removeClass("sr-only");
			$(".pageaction:eq(6)").parent("li").removeClass("sr-only");
		} else {
			for ( var i = 0; i < pages; i++) {
				$(".pageaction:eq(" + (i + 2) + ")").parent("li").removeClass(
						"sr-only");
			}
		}
		if (pages > 5) {
			end = page + 2;
			start = page - 2;
			if (start < 1) {
				start = 1;
				end = 5;
			}
			if (end > pages) {
				end = pages;
				start = pages - 4;
			}
		} else {
			start = 1;
			end = pages;
		}
		for ( var i = start, j = 2; i <= end; i++, j++) {
			$(".pageaction:eq(" + j + ")").text(i).attr("page", i);
			if (page == i) {
				$(".pageaction:eq(" + j + ")").attr("href", "javascript:;")
						.parent("li").addClass("disabled");
			}
		}
		// 设置内容
		$(".badge").text(json.number);
		var orders = $("#orders").empty();
		for ( var i = 0; i < json.list.length; i++) {
			var h62 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;'><a><span style='font-size:10px;'>订单号 : "+json.list[i].ouuid+"</span></a></h6>");
			var h63 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><a><span style='font-size:10px;'>下单时间 : "+json.list[i].otime+"</span></a></h6>");
			var h6 = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.list[i].rtname+"订单<a class='pull-right' style='margin-top:-5px;'><span style='font-size:9px;padding-right:10px;'>订单金额：￥"+json.list[i].sum+"</span></a></h6>");
			var div2 = $("<div class='media-body'></div>").append(h6).append(h62).append(h63);
			var details = json.list[i].details;
			for ( var j = 0; j < json.list[i].details.length; j++) {
				if(j>3){
					div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'>...</h6>"));
					break;
				}
				div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'><span>"+details[j].muname+"</span><span class='pull-right' style='padding-right:10px;'>×"+details[j].ocount+"</span></h6>"));
			}
			div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix page-header'></h6>"));
			var div1 = $("<div class='media-left'><img src='/ffts/"+json.list[i].rtpic+"' class='img40 img-circle'/></div>");
			var div = $("<div index='"+i+"' class='media border2Circle order' style='margin: 10px 0px 0px 0px;padding:5px 5px 5px 10px;'></div>").append(div1).append(div2);
			// 用户信息
			var uaddress = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><label class='label label-success'>家</label>&nbsp;&nbsp;<span style='font-size:10px;'>"+json.list[i].address+"</span></h6>");
			var sex = "";
			if(json.list[i].gender=="男"){
				sex=" <label class='label label-info' style='margin-left:5px;'>先生</label>";
			}else{
				sex=" <label class='label label-danger' style='margin-left:5px;'>女士</label>";
			}
			var gender = $("<span style='font-size:10px;padding-right:10px;'> "+sex+"</span>");
			var uname = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.list[i].realname+"</h6>").append(gender).append("<a class='pull-right' style='margin-top:-5px;'><span style='font-size:10px;padding-right:10px;'>联系电话 : "+json.list[i].tel+"</span></a>");
			var div3 = $("<div class='media-body'></div>").append(uname).append(uaddress);
			var medialeft = $("<div class='media-left' style='padding:0px 15px 0px 5px;'><img src='/ffts/"+json.list[i].photo+"' class='img30 img-circle'/></div>");
			var media = $("<div class='media' style='padding:0px;margin:5px 0px 0px 0px;'></div>").append(medialeft).append(div3);
			var btn;
			if(json.number<5){
				btn = $("<a type='button' index='"+i+"' uuid='"+json.list[i].ouuid+"' class='borderGreen pull-right' style='padding:3px 10px;margin:5px 0px 0px;'>立即接单</a>");
				btn.click(function(event){
					getOrder($(this).attr("index"),$(this).attr("uuid"),info,event);
				});
			}else{
				btn = $("<button type='button' index='"+i+"' uuid='"+json.list[i].ouuid+"' class='borderGray pull-right' style='padding:3px 10px;margin:5px 0px 0px;' disabled='disabled'>已达上限</button>");
			}
			div.append(media).append(btn);
			orders.append(div);
		}
		
		// 第二个页码
		$(".page2action").attr("href", "#").parent("li").removeClass("disabled");
		$(".page2action:eq(2)").parent("li").addClass("sr-only");
		$(".page2action:eq(3)").parent("li").addClass("sr-only");
		$(".page2action:eq(4)").parent("li").addClass("sr-only");
		$(".page2action:eq(5)").parent("li").addClass("sr-only");
		$(".page2action:eq(6)").parent("li").addClass("sr-only");
		// 设置页面页码数
		var pages2 = Math.ceil(json.count2 / size2);
		var start2;
		var end2;
		// 设置最后一页页码
		$(".page2action:last").attr("page2", pages2);
		// 设置上一页下一页是否激活
		if (pages2 == page2) {
			$(".page2action:eq(7)").attr("page2", "").attr("href",
					"javascript:;").parent("li").addClass("disabled");
		} else {
			$(".page2action:eq(7)").attr("page2", (page2 + 1));
		}
		if (page2 > 1) {
			$(".page2action:eq(1)").attr("page2", page2 - 1);
		} else {
			$(".page2action:eq(1)").attr("page2", "").attr("href",
			"javascript:;").parent("li").addClass("disabled");
		}
		if (pages2 > 5) {
			$(".page2action:eq(2)").parent("li").removeClass("sr-only");
			$(".page2action:eq(3)").parent("li").removeClass("sr-only");
			$(".page2action:eq(4)").parent("li").removeClass("sr-only");
			$(".page2action:eq(5)").parent("li").removeClass("sr-only");
			$(".page2action:eq(6)").parent("li").removeClass("sr-only");
		} else {
			for ( var i = 0; i < pages2; i++) {
				$(".page2action:eq(" + (i + 2) + ")").parent("li").removeClass(
						"sr-only");
			}
		}
		if (pages2 > 5) {
			end2 = page2 + 2;
			start2 = page2 - 2;
			if (start2 < 1) {
				start2 = 1;
				end2 = 5;
			}
			if (end2 > pages2) {
				end2 = pages2;
				start2 = pages2 - 4;
			}
		} else {
			start2 = 1;
			end2 = pages2;
		}
		for ( var i = start2, j = 2; i <= end2; i++, j++) {
			$(".page2action:eq(" + j + ")").text(i).attr("page2", i);
			if (page2 == i) {
				$(".page2action:eq(" + j + ")").attr("href", "javascript:;")
						.parent("li").addClass("disabled");
			}
		}
		// 设置内容
		var orders = $("#sded").empty();
		for ( var i = 0; i < json.send.length; i++) {
			var h62 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;'><a><span style='font-size:10px;'>订单号 : "+json.send[i].ouuid+"</span></a></h6>");
			var h63 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><a><span style='font-size:10px;'>下单时间 : "+json.send[i].otime+"</span></a></h6>");
			var h6 = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.send[i].rtname+"订单<a class='pull-right' style='margin-top:-5px;'><span style='font-size:9px;padding-right:10px;'>订单金额：￥"+json.send[i].sum+"</span></a></h6>");
			var div2 = $("<div class='media-body'></div>").append(h6).append(h62).append(h63);
			var details = json.send[i].details;
			for ( var j = 0; j < json.send[i].details.length; j++) {
				if(j>3){
					div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'>...</h6>"));
					break;
				}
				div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'><span>"+details[j].muname+"</span><span class='pull-right' style='padding-right:10px;'>×"+details[j].ocount+"</span></h6>"));
			}
			div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix page-header'></h6>"));
			var div1 = $("<div class='media-left'><img src='/ffts/"+json.send[i].rtpic+"' class='img40 img-circle'/></div>");
			var div = $("<div index='"+i+"' class='media border2Circle sded' style='margin: 10px 0px 0px 0px;padding:5px 5px 5px 10px;'></div>").append(div1).append(div2);
			// 用户信息
			var uaddress = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><label class='label label-success'>家</label>&nbsp;&nbsp;<span style='font-size:10px;'>"+json.send[i].address+"</span></h6>");
			var sex = "";
			if(json.send[i].gender=="男"){
				sex=" <label class='label label-info' style='margin-left:5px;'>先生</label>";
			}else{
				sex=" <label class='label label-danger' style='margin-left:5px;'>女士</label>";
			}
			var gender = $("<span style='font-size:10px;padding-right:10px;'> "+sex+"</span>");
			var uname = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.send[i].realname+"</h6>").append(gender).append("<a class='pull-right' style='margin-top:-5px;'><span style='font-size:10px;padding-right:10px;'>联系电话 : "+json.send[i].tel+"</span></a>");
			var div3 = $("<div class='media-body'></div>").append(uname).append(uaddress);
			var medialeft = $("<div class='media-left' style='padding:0px 15px 0px 5px;'><img src='/ffts/"+json.send[i].photo+"' class='img30 img-circle'/></div>");
			var media = $("<div class='media' style='padding:0px;margin:5px 0px 0px 0px;'></div>").append(medialeft).append(div3);
			div.append(media);
			orders.append(div);
		}
		
		// 已接单
		setTimeout(function(){
			getSaved(json,info);
		}, 500);
	});
}

function getOrder(index,uuid,info,event){
	// 加入购物车代码
	$(".carts:visible:eq(0)").attr("data-toggle","popover");
	var flyer = $(".order[index='"+index+"']").addClass("col-md-3");
	var offset = $(".end:visible:eq(0)").offset();
	var userid = $("#infos").attr("userid");
	if(userid==""){
		$("#s-modal-body").text("请您先登录！");
		$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
		$("#modal").modal({"show":true});
		return false;
	}
	// 发送请求到数据库
	$.post("sender!saveOrder.action",{"uuid":uuid,"userid":userid},function(data){
		if(data=="-1"){
			$("#s-modal-body").text("接单失败！");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#modal").modal({"show":true});
		}else{
			// 添加成功
			flyer.fly({
				start : {
					left : event.clientX - 250, // 开始位置（必填）#fly元素会被设置成position:
					top : event.clientY - 150 // 开始位置（必填）
				},
				end : {
					left : offset.left -60, // 结束位置（必填）
					top : offset.top - 40, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					flyer.css("margin","0px").css("padding","0px").removeClass("border2Circle");
					$(".carts:visible:eq(0)").attr("data-content","成功接单").popover("show");
					setTimeout(function(){
						$(".carts:visible:eq(0)").popover("hide");
					}, 500);
				}
			});
			getUsableOrders(info);
		}
	});
}
	

function getSaved(json,info){
	// 设置已接单内容
	var orders2 = $("#saved").empty();
	if(json.save.length==0){
		orders2.append("<h5 style='text-align:center;'>还没有接单...</h5>");
	}
	for ( var i = 0; i < json.save.length; i++) {
		var h62 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;'><a><span style='font-size:10px;'>订单号 : "+json.save[i].ouuid+"</span></a></h6>");
		var h63 = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><a><span style='font-size:10px;'>下单时间 : "+json.save[i].otime+"</span></a></h6>");
		var h6 = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.save[i].rtname+"订单<a class='pull-right' style='margin-top:-5px;'><span style='font-size:9px;padding-right:10px;'>订单金额：￥"+json.save[i].sum+"</span></a></h6>");
		var div2 = $("<div class='media-body'></div>").append(h6).append(h62).append(h63);
		var details = json.save[i].details;
		for ( var j = 0; j < json.save[i].details.length; j++) {
			if(j>3){
				div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'>...</h6>"));
				break;
			}
			div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix'><span>"+details[j].muname+"</span><span class='pull-right' style='padding-right:10px;'>×"+details[j].ocount+"</span></h6>"));
		}
		div2.append($("<h6 style='margin:0px;padding:3px 0px;font-size:9px;' class='clearfix page-header'></h6>"));
		var div1 = $("<div class='media-left'><img src='/ffts/"+json.save[i].rtpic+"' class='img40 img-circle'/></div>");
		var div = $("<div index='"+i+"' class='media border2Circle save' style='margin: 10px 0px 0px 0px;padding:5px 5px 5px 10px;'></div>").append(div1).append(div2);
		// 用户信息
		var uaddress = $("<h6 style='margin:0px;padding:0px 0px 5px 0px;' class='page-header'><label class='label label-success'>家</label>&nbsp;&nbsp;<span style='font-size:10px;'>"+json.save[i].address+"</span></h6>");
		var sex = "";
		if(json.save[i].gender=="男"){
			sex=" <label class='label label-info' style='margin-left:5px;'>先生</label>";
		}else{
			sex=" <label class='label label-danger' style='margin-left:5px;'>女士</label>";
		}
		var gender = $("<span style='font-size:10px;padding-right:10px;'> "+sex+"</span>");
		var uname = $("<h6 style='padding:5px 0px;margin:0px;'>"+json.save[i].realname+"</h6>").append(gender).append("<a class='pull-right' style='margin-top:-5px;'><span style='font-size:10px;padding-right:10px;'>联系电话 : "+json.save[i].tel+"</span></a>");
		var div3 = $("<div class='media-body'></div>").append(uname).append(uaddress);
		var medialeft = $("<div class='media-left' style='padding:0px 15px 0px 5px;'><img src='/ffts/"+json.save[i].photo+"' class='img30 img-circle'/></div>");
		var media = $("<div class='media' style='padding:0px;margin:5px 0px 0px 0px;'></div>").append(medialeft).append(div3);
		var btn = $("<a index='"+i+"' uuid='"+json.save[i].ouuid+"' class='borderGreen pull-right' style='padding:3px 10px;margin:5px 0px 0px;'>确认送达</a>");
		btn.click(function(event){
			sendToDest($(this).attr("index"),$(this).attr("uuid"),info,event);
		});
		div.append(media).append(btn);
		orders2.append(div);
	}
}

function sendToDest(index,uuid,info,event){
	$(".sended:visible:eq(0)").attr("data-toggle","popover");
	var flyer = $(".save[index='"+index+"']").addClass("col-md-3");
	var offset = $(".svend:visible:eq(0)").offset();
	var userid = $("#infos").attr("userid");
	if(offset==undefined){
		offset = $("#complete").offset();
	}
	if(userid==""){
		$("#s-modal-body").text("请您先登录！");
		$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
		$("#modal").modal({"show":true});
		return false;
	}
	// 发送请求到数据库
	$.post("sender!sendToDest.action",{"uuid":uuid},function(data){
		if(data=="0"){
			$("#s-modal-body").text("确认失败！");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#modal").modal({"show":true});
		}else{
			// 添加成功
			flyer.fly({
				start : {
					left : event.clientX - 250, // 开始位置（必填）#fly元素会被设置成position:
					top : event.clientY - 150 // 开始位置（必填）
				},
				end : {
					left : offset.left - 10, // 结束位置（必填）
					top : offset.top - 10, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					flyer.css("margin","0px").css("padding","0px").removeClass("border2Circle");
					$(".sended:visible:eq(0)").attr("data-content","确认送达").popover("show");
					setTimeout(function(){
						$(".sended:visible:eq(0)").popover("hide");
					}, 500);
				}
			});
			getUsableOrders(info);
		}
	});
}
