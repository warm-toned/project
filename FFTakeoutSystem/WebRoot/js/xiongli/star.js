$(function() {
	// 星星评级
	$("#reststar").raty({
		cancel : false,
		size : 44,
		click : function(score, evt) {
			// alert('ID: ' + $(this).attr('id') + "\nscore: " + score +
			// "\nevent: " + evt);
			$("#rtAssess").removeClass("sr-only");
			if (score <= 3) {
				$("#rtAssess").attr("placeholder", "说说哪里不满意，帮商家改进");
			} else {
				$("#rtAssess").attr("placeholder", "求表扬~");
			}
		}
	});
	// 商店文本域失去焦点
	$("#rtAssess").blur(function() {
		if ($(this).val() == "") {
			$(this).addClass("sr-only");
		}
	});
	// 点评
	$(".assFinger").click(function() {
		var index = $(this).attr("index");
		$(".afg:eq(" + index + ")").addClass("sr-only");
		$(".status:eq(" + index + ")").removeClass("sr-only");
		$(".assFingerBad:eq(" + index + ")").removeClass("sr-only");
		$(".assFingerGood:eq(" + index + ")").removeClass("sr-only");
	});
	// 推荐
	$(".assFingerGood").click(
			function() {
				var index = $(this).attr("index");
				$(this).css("color", "orange").css("borderColor", "orange");
				$(".assFingerBad:eq(" + index + ")").css("color", "#888").css(
						"borderColor", "#f1f1f1");
				$(".assFingerBad:eq(" + index + ")").removeClass("sr-only");
				$(".menuAssess:eq(" + index + ")").removeClass("sr-only");
				$(".menuAssess:eq(" + index + ")").attr("placeholder", "求表扬~");
				$(".menuAssess:not(:eq(" + index + "))").addClass("sr-only");
				$(".status:eq(" + index + ")").attr("assess", "good");
				$(".muScore:eq(" + index + ")").val(5);
				var others = $(".status");
				for ( var i = 0; i < others.length; i++) {
					if (i == index) {
						continue;
					}
					var assess = $(others[i]).attr("assess");
					if (assess == "good") {
						$(".assFingerBad:eq(" + i + ")").addClass("sr-only");
					} else if (assess == "bad") {
						$(".assFingerGood:eq(" + i + ")").addClass("sr-only");
					}
				}
			});
	// 吐槽
	$(".assFingerBad").click(
			function() {
				var index = $(this).attr("index");
				$(this).css("color", "#444").css("borderColor", "#444");
				$(".assFingerGood:eq(" + index + ")").css("color", "#888").css(
						"borderColor", "#f1f1f1");
				$(".assFingerGood:eq(" + index + ")").removeClass("sr-only");
				$(".menuAssess:eq(" + index + ")").removeClass("sr-only");
				$(".menuAssess:eq(" + index + ")").attr("placeholder",
						"说说哪里不满意，帮商家改进");
				$(".menuAssess:not(:eq(" + index + "))").addClass("sr-only");
				$(".status:eq(" + index + ")").attr("assess", "bad");
				$(".muScore:eq(" + index + ")").val(1);
				var others = $(".status");
				for ( var i = 0; i < others.length; i++) {
					if (i == index) {
						continue;
					}
					var assess = $(others[i]).attr("assess");
					if (assess == "good") {
						$(".assFingerBad:eq(" + i + ")").addClass("sr-only");
					} else if (assess == "bad") {
						$(".assFingerGood:eq(" + i + ")").addClass("sr-only");
					}
				}
			});
	// 单个文本域失去焦点
	$(".menuAssess").blur(function() {
		if ($(this).val() == "") {
			$(this).addClass("sr-only");
		}
	});
	// 提交评价s
	$(".setAssess").click(function() {
		$("#assFrm").trigger("submit");
	});
	// 提交表单验证
	$("#assFrm").submit(function(){
		var score = $("input[type='hidden'][name='score']").val();
		if(score==""){
			$("#s-modal-body").text("请给商家评分");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$("#modal").modal({"show":true});
			return false;
		}
	});
	
});