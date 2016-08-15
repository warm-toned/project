$(function() {
	var info = $("#pginfo");

	// 导航
	$("li[name='nav']").click(function() {
		$("li[name='nav']").removeClass("active");
		$(this).addClass("active");
		var index = $(this).attr("index");
		if (index == 0) {
			$("#usable").removeClass("sr-only");
			$("#got").addClass("sr-only");
			$("#complete").addClass("sr-only");
		} else if (index == 1) {
			$("#usable").addClass("sr-only");
			$("#got").removeClass("sr-only");
			$("#complete").addClass("sr-only");
		} else {
			$("#usable").addClass("sr-only");
			$("#got").addClass("sr-only");
			$("#complete").removeClass("sr-only");
		}
	});

	// 查询可接订单信息
	getUsableOrders(info);
	setInterval(function() {
		getUsableOrders(info);
	}, 20000);

	$(".pagereact").click(function() {
		var num = $(this).attr("value");
		var actions = $(".pageaction");
		for ( var i = 0; i < actions.length; i++) {
			$(actions[i]).attr("size", num);
		}
		info.attr("size", num);
		$("#pagecount").text("每页显示 "+num+" 条订单");
	});
});

function getUsableOrders(info) {
	var page = info.attr("page");
	var size = info.attr("size");
	$.post("sender!getUsableOrders.action", {
		"page" : page,
		"size" : size
	}, function(data) {
		var json = eval(data);
	});
}