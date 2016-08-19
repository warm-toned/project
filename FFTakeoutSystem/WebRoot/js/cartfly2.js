$(function() {
	// 用于清空无效内容
	var badges = $(".badge");
	for ( var i = 0; i < badges.length; i++) {
		var texts = $(badges[i]).text();
		var flag = false;
		for ( var j = 0; j < texts.length; j++) {
			var regex = /^\d+$/;
			if(regex.test(texts[j])){
				flag = true;
				break;
			}
		}
		if(!flag){
			$(".badge:eq("+i+")").html("");
		}
	}
	
	
	$(".btnAdd").addClass("sr-only");
	var text = $(".badge").text();
	if(text!=""){
		$(".btnAddToCart").addClass("sr-only");
		$(".btnAdd").removeClass("sr-only");
	}
	$(".btnAddToCart").click(function(event){
		if($("#infos").attr("userid")==""){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#logBtn").removeClass("sr-only");
			$(this).attr("data-toggle","modal");
			$(this).attr("data-target",".bs-example-modal-sm");
		}else{
			var index = $(this).attr("index");
			$(".btnAdd").removeClass("sr-only");
			$(".btnAddToCart").addClass("sr-only");
			$(".carts:visible:eq(0)").attr("data-toggle","popover");
			var addcar = $(this);
			var index = $(this).attr("index");
			var img = $("#goodspic").attr('src');
			var flyer = $("<img class='u-flyer img-circle' src='"+ img + "' width='50' height='50' style='border:2px #573f2f solid;'>");
			var offset = $(".end:visible:eq(0)").offset();
			flyer.fly({
				start : {
					left : event.clientX - 50, // 开始位置（必填）#fly元素会被设置成position:
					top : event.clientY - 50 // 开始位置（必填）
				},
				end : {
					left : offset.left + 10, // 结束位置（必填）
					top : offset.top + 10, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					$(".carts:visible:eq(0)").attr("data-content","成功加入菜单").popover("show");
					setTimeout(function(){
						$(".carts:visible:eq(0)").popover("hide");
					}, 1000);
				}
			});
			$(".btnAdd:eq("+index+")").trigger("click");
		}
	});
	
	// 绑定添加事件
	$(".btnAdd").click(function(event) {
		// 判断用户是否登录
		if($("#infos").attr("userid")==""){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#logBtn").removeClass("sr-only");
			$(this).attr("data-toggle","modal");
			$(this).attr("data-target",".bs-example-modal-sm");
		}else{
			// 加入购物车代码
			$("#logBtn").addClass("sr-only");
			var userid = $("#infos").attr("userid");
			var rtid = $("#infos").attr("rtid");
			var muid = $(this).attr("muid");
			$(".carts:visible:eq(0)").attr("data-toggle","popover");
			var addcar = $(this);
			var index = $(this).attr("index");
			var img = $("#goodspic").attr('src');
			var flyer = $("<img class='u-flyer img-circle' src='"+ img + "' width='50' height='50' style='border:2px #573f2f solid;'>");
			var offset = $(".end:visible:eq(0)").offset();
			flyer.fly({
				start : {
					left : event.clientX - 50, // 开始位置（必填）#fly元素会被设置成position:
					top : event.clientY - 50 // 开始位置（必填）
				},
				end : {
					left : offset.left + 10, // 结束位置（必填）
					top : offset.top + 10, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					$(".carts:visible:eq(0)").attr("data-content","成功加入菜单").popover("show");
					setTimeout(function(){
						$(".carts:visible:eq(0)").popover("hide");
					}, 1000);
				}
			});
			// 发送查询
			$.post("order!addOrder.action",{"userid":userid,"rtid":rtid,"muid":muid},function(result){
				if(result!="fail"){
					// 要把减号按钮显示出来
					$(".btnMinus").removeClass("sr-only");
					// 要回显数据
					$(".badge").text(result);
					if(result=="1"){
						$(".badge").removeClass("sr-only");
					}
					loadCart(userid,rtid);
				}
			});
		}
	});
	
	$(".btnMinus").click(function(event) {
		// 判断用户是否登录
		if($("#infos").attr("userid")==""){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#logBtn").removeClass("sr-only");
			$(this).attr("data-toggle","modal");
			$(this).attr("data-target",".bs-example-modal-sm");
		}else{
			// 加入购物车代码
			$("#logBtn").addClass("sr-only");
			var userid = $("#infos").attr("userid");
			var rtid = $("#infos").attr("rtid");
			var muid = $(this).attr("muid");
			$(".carts:visible:eq(0)").attr("data-toggle","popover");
			var addcar = $(this);
			var index = $(this).attr("index");
			var img = $("#goodspic").attr('src');
			var flyer = $("<img class='u-flyer img-circle' src='"+ img + "' width='50' height='50' style='border:2px #573f2f solid;'>");
			var offset = $(".end:visible:eq(0)").offset();
			flyer.fly({
				start : {
					left : offset.left + 10, // 开始位置（必填）#fly元素会被设置成position:
					top : offset.top + 10 // 开始位置（必填）
				},
				end : {
					left : event.clientX, // 结束位置（必填）
					top : event.clientY, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					flyer.hide();
					$(".carts:visible:eq(0)").attr("data-content","菜单数量减一").popover("show");
					setTimeout(function(){
						$(".carts:visible:eq(0)").popover("hide");
					}, 1000);
				}
			});
			// 从订单中减少1
			$.post("order!minusOrder.action",{"userid":userid,"rtid":rtid,"muid":muid},function(result){
				if(result!="fail"){
					// 要回显数据
					$(".badge").text(result);
					loadCart(userid,rtid);
					if(result=="0"){
						// 要把减号按钮隐藏
						$(".btnMinus").addClass("sr-only");
						$(".badge").addClass("sr-only");
						$(".btnAddToCart").removeClass("sr-only");
						$(".btnAdd").addClass("sr-only");
					}
				}
			});
		}
	});
	
	//点击购物车
	$("#cart").click(function(){
		$("#cartbar").removeClass("sr-only");
		var userid = $("#infos").attr("userid");
		var rtid = $("#infos").attr("rtid");
		var timerid = setInterval(function(){
			if($(".cartes:visible:eq(0)").attr("id")==undefined){
				$("#cartbar").addClass("sr-only");
				setTimeout(function(){
					clearInterval(timerid);
				}, 2000);
			}else{
				$("#cartbar").removeClass("sr-only");
			}
		}, 500);
		// 加载订单内容
		loadCart(userid,rtid);
	});
	
	// 清空
	$("#clearCart").click(function(){
		var userid = $("#infos").attr("userid");
		if($("#infos").attr("userid")==""){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#logBtn").removeClass("sr-only");
			$("#modal").modal({"show":true});
		}else{
			var rtid = $("#infos").attr("rtid");
			$.post("order!clearCart.action",{"userid":userid,"rtid":rtid},function(result){
				if(result=="success"){
					$(".btnMinus").addClass("sr-only");
					$(".badge").addClass("sr-only");
					loadCart(userid,rtid);
				}else{
					$("#s-modal-body").text("购物车是空的！");
					$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
					$("#logBtn").addClass("sr-only");
					$("#modal").modal({"show":true});
				}
			});
		}
	});
	$("#clearCart2").click(function(){
		var userid = $("#infos").attr("userid");
		if($("#infos").attr("userid")==""){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#logBtn").removeClass("sr-only");
			$("#modal").modal({"show":true});
		}else{
			var rtid = $("#infos").attr("rtid");
			$.post("order!clearCart.action",{"userid":userid,"rtid":rtid},function(result){
				if(result=="success"){
					$(".btnMinus").addClass("sr-only");
					$(".badge").text("");
					loadCart(userid,rtid);
				}else{
					$("#s-modal-body").text("购物车是空的！");
					$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
					$("#logBtn").addClass("sr-only");
					$("#modal").modal({"show":true});
				}
			});
		}
	});
	
	// 大页面展示内容
	$("#showCart").click(function(){
		$(this).attr("data-toggle","modal");
		$(this).attr("data-target",".bs-example-modal");
		var userid = $("#infos").attr("userid");
		var rtid = $("#infos").attr("rtid");
		$.getJSON("order!getCart.action",{"userid":userid,"rtid":rtid},function(json){
			$("#m-modal-body").empty();
			if(json.length==0){
				$("#m-modal-body").text("购物车是空的");
			}else{
				var list = $("<ul></ul>");
				var account = 0;
				for ( var i = 0; i < json.length; i++) {
					var sum = parseFloat(json[i].muprice) * parseInt(json[i].ocount, 10);
					account = account+sum;
					var li = $("<li><a href='#'><span class='glyphicon glyphicon-barcode'>&nbsp;</span><span>"+json[i].muname+"</span><span class='pull-right' style='width:60px;text-align:right;'>￥:"+sum+"</span><span class='pull-right'>"+json[i].ocount+"</span></a></li>");
					list.append(li);
				}
				list.append($("<li><a href=''><span class='pull-right' style='width:60px;text-align:right;'>￥:"+account+"</span><span class='pull-right'>总计</span></a></li>"));
				$("#m-modal-body").append(list);
			}
		});
	});
	
	// 立即下单
	$(".orderNow").click(function(){
		var userid = $("#infos").attr("userid");
		var rtid = $("#infos").attr("rtid");
		orderNow(rtid,userid,this);
	});
	
	// 用于制作导航
	$("li[name='nav']").click(function(){
		$("li[name='nav']").removeClass("active");
		$(this).addClass("active");
		var index = $(this).attr("index");
		if(index==0){
			$("#menu").removeClass("sr-only");
		}else if(index==1){
			$("#menu").addClass("sr-only");
		}else{
			$("#menu").addClass("sr-only");
		}
	});
 
	
});
//style="width:262px;height:270px;background-repeat:no-repeat;background-image:url(<%=path%>/${item.PICTURE});background-position:center center"


function loadCart(userid,rtid){
	$.getJSON("order!getCart.action",{"userid":userid,"rtid":rtid},function(json){
		var list = $("#cartList").empty();
		var account = 0;
		for ( var i = 0; i < json.length; i++) {
			var sum = parseFloat(json[i].muprice) * parseInt(json[i].ocount, 10);
			account = account+sum;
			var li = $("<li><a href='#'><span class='glyphicon glyphicon-barcode'>&nbsp;</span><span>"+json[i].muname+"</span><span class='pull-right' style='width:60px;text-align:right;'>￥:"+sum+"</span><span class='pull-right'>"+json[i].ocount+"</span></a></li>");
			list.append(li);
		}
		list.append($("<li><a href='#' class='orderNow'><span class='glyphicon glyphicon-cutlery'>&nbsp;</span>立即下单<span class='pull-right' style='width:60px;text-align:right;'>￥:"+account+"</span><span class='pull-right'>总计</span></a></li>"));
		$(".orderNow").click(function(){
			var userid = $("#infos").attr("userid");
			var rtid = $("#infos").attr("rtid");
			orderNow(rtid,userid,this);
		});
	});
}


function orderNow(rtid,userid,i){
	if(userid==""){
		$("#s-modal-body").text("请您先登录");
		$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
		$("#logBtn").removeClass("sr-only");
		$("#modal").modal({"show":true});
	}else{
		$.post("order!getCart.action",{"userid":userid,"rtid":rtid},function(json){
			var length = eval(json).length;
			if(length!=0){
				window.location.href="order!orderNow.action?userid="+userid+"&rtid="+rtid;
			}else{
				$("#s-modal-body").text("购物车是空的！");
				$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
				$("#logBtn").addClass("sr-only");
				$("#modal").modal({"show":true});
			}
		});
	}
}

function setMenuBar(top){
	if(top==0){
		$("#menubar").attr("style","margin: 0px;padding: 0px;position: fixed;");
	}else if(top<=134.6){
		$("#menubar").css("top",190.6-top);
	}else{
		$("#menubar").css("top",56);
	}
}

function judge() {
	// 判断是否为移动端运行环境
	if (/AppleWebKit.*Mobile/i.test(navigator.userAgent)
			|| (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))) {
		if (window.location.href.indexOf("?mobile") < 0) {
			try {
				if (/Android|webOS|iPhone|iPod|BlackBerry/i
						.test(navigator.userAgent)) {
					// 判断访问环境是 Android|webOS|iPhone|iPod|BlackBerry 则加载以下样式
					$("#menubar").attr("style","margin: 0px;padding: 0px;");
				} else if (/iPad/i.test(navigator.userAgent)) {
					// 判断访问环境是 iPad 则加载以下样式
					$("#menubar").attr("style","margin: 0px;padding: 0px;");
				} else {
					// 判断访问环境是 其他移动设备 则加载以下样式
					$("#menubar").attr("style","margin: 0px;padding: 0px;");
				}
			} catch (e) {
			}
		}
	} else {
		// 如果以上都不是，则加载以下样式
		$("#menubar").attr("style","margin: 0px;padding: 0px;position: fixed;");
	}
}