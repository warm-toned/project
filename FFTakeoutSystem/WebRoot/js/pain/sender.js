$(function() {
	var info = $("#pginfo");
	var page = info.attr("page");
	var size = info.attr("size");
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
	getUsableOrders(page, size);
	setInterval(function(){
		getUsableOrders(page, size);
	}, 20000);
});

function getUsableOrders(page,size){
	$.post("sender!getUsableOrders.action",{"page":page,"size":size},function(data){
		var json = eval(data);
		for
	});
}