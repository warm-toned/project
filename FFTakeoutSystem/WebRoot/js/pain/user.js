$(function() {
	document.documentElement.scrollTop = 0;
	var userid = $("#infos").attr("userid");

	// 导航
	$("li[name='nav']").click(function() {
		$("li[name='nav']").removeClass("active");
		$(this).addClass("active");
		var index = $(this).attr("index");
		if (index == 0) {
			$("#myorder").removeClass("sr-only");
			$("#myinfo").addClass("sr-only");
			$("#shopMgt").addClass("sr-only");
		} else if (index == 1) {
			$("#myorder").addClass("sr-only");
			$("#myinfo").removeClass("sr-only");
			$("#shopMgt").addClass("sr-only");
		} else {
			$("#myorder").addClass("sr-only");
			$("#myinfo").addClass("sr-only");
			$("#shopMgt").removeClass("sr-only");
		}
	});

	// 切换显示条数
	$(".pagereact").click(function() {
		var num = $(this).attr("value");
		var actions = $(".pageaction");
		for ( var i = 0; i < actions.length; i++) {
			var str = $(actions[i]).attr("href");
			var end = str.indexOf("&size");
			if (end == -1) {
				end = str.length;
			}
			$(actions[i]).attr("href", str.substring(0, end) + "&size=" + num);
		}
		window.location.href = $("#first").attr("href");
	});

	// 修改
	$(".btnEdit").click(
			function() {
				var index = $(this).attr("index");
				$(".editInfos:eq(" + index + ")").addClass("sr-only");
				$(".editBox:eq(" + index + ")").removeClass("sr-only");
				$(".infoBox:eq(" + index + ")").val(
						$(".info:eq(" + index + ")").text());
			});

	// 保存
	$(".btnSave").click(
			function() {
				// 判断表单是否合格
				var index = $(this).attr("index");
				if (index == 0) {
					var regex1 = /^1[3578]\d{9}$/;
					var regex2 = /^\d{4}-\d{8}$/;
					var regex3 = /^\d{3}-\d{7,8}$/;
					var flag = false;
					var val = $(".infoBox:eq(" + index + ")").val();
					flag = regex1.test(val) || regex2.test(val)
							|| regex3.test(val);
					if (flag) {
						$.post("user!editTel.action", {
							"user.tel" : val,
							"user.userid" : userid
						}, function(result) {
							if (result == "1") {
								$(".info:eq(" + index + ")").text(val);
							}
						});
					} else {
						$("#s-modal-body").text("电话格式不正确！");
						$("#s-modal-body-addon").removeClass().addClass(
								"glyphicon glyphicon-exclamation-sign");
						$("#modal").modal({
							"show" : true
						});
					}
				} else if (index == 1) {
					var val = $(".infoBox:eq(" + index + ")").val();
					if (val.length > 130) {
						$("#s-modal-body").text("地址过长！");
						$("#s-modal-body-addon").removeClass().addClass(
								"glyphicon glyphicon-exclamation-sign");
						$("#modal").modal({
							"show" : true
						});
					} else {
						$.post("user!editAddress.action", {
							"user.address" : val,
							"user.userid" : userid
						}, function(result) {
							if (result == "1") {
								$(".info:eq(" + index + ")").text(val);
							}
						});
					}
				} else if (index == 2) {
					var val = $(".infoBox:eq(" + index + ")").val();
					var regex1 = /^\w+@\w{2,4}.\w{2,4}$/;
					var flag = false;
					flag = regex1.test(val);
					if (flag) {
						$.post("user!editEmail.action", {
							"user.email" : val,
							"user.userid" : userid
						}, function(result) {
							if (result == "1") {
								$(".info:eq(" + index + ")").text(val);
							}
						});
					} else {
						$("#s-modal-body").text("电子邮箱格式不正确！");
						$("#s-modal-body-addon").removeClass().addClass(
								"glyphicon glyphicon-exclamation-sign");
						$("#modal").modal({
							"show" : true
						});
					}
				} else {
					var val = $(".infoBox:eq(" + index + ")").val();
					var val = $(".infoBox:eq(" + index + ")").val();
					if (val.length > 10) {
						$("#s-modal-body").text("真实姓名过长！");
						$("#s-modal-body-addon").removeClass().addClass(
								"glyphicon glyphicon-exclamation-sign");
						$("#modal").modal({
							"show" : true
						});
					} else {
						$.post("user!editRealname.action", {
							"user.realname" : val,
							"user.userid" : userid
						}, function(result) {
							if (result == "1") {
								$(".info:eq(" + index + ")").text(val);
							}
						});
					}
				}
				$(".editInfos:eq(" + index + ")").removeClass("sr-only");
				$(".editBox:eq(" + index + ")").addClass("sr-only");
			});

	// 更换头像
	$("#editPhoto").click(function() {
		$("#photoreact").addClass("sr-only");
		$("#photoaction").removeClass("sr-only");
		$("#photoInfo").trigger("click");
	});

	// 加载性别
	var gender = $("#gender").attr("gender");
	loadGender(gender);

	// 点击女
	$("#female").click(function() {
		$.post("user!editGender.action", {
			"user.gender" : "女",
			"user.userid" : userid
		}, function(result) {
			if (result == "1") {
				loadGender("女");
			}
		});
	});

	$("#male").click(function() {
		$.post("user!editGender.action", {
			"user.gender" : "男",
			"user.userid" : userid
		}, function(result) {
			if (result == "1") {
				loadGender("男");
			}
		});
	});

	
	chooseMenu();
	

});

function loadGender(gender) {
	if (gender == "男") {
		$("#male").removeClass("btn-default");
		$("#male").addClass("btn-success").addClass("disabled");
		$("#female").removeClass("btn-danger").removeClass("disabled");
		$("#female").addClass("btn-default");
	} else {
		$("#female").removeClass("btn-default");
		$("#female").addClass("btn-danger").addClass("disabled");
		$("#male").removeClass("btn-success").removeClass("disabled");
		$("#male").addClass("btn-default");
	}
}

function chooseMenu() {
	var index = $("#infos").attr("index");
	if (index != "") {
		$("li[name='nav']:eq(" + index + ")").trigger("click");
	}
}


