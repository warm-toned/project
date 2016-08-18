<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${empty shopinfos.money or shopinfos.money eq 0}">
	<jsp:forward page="shop!ShopList.action"/>
</c:if>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<%--bootstrap相关 --%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--参数相关 --%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1">
<meta http-equiv="description" content="">
<%--引入外部文件 --%>
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css"
	type="text/css"></link>
<%--与本页相关的css --%>
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/animate.min.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/lightbox.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/responsive.css" rel="stylesheet"
	type="text/css"></link>
<%--通用样式 --%>
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox.min.js"></script>

</head>

<body>
	<a id="infos" class="sr-only" rtid="${sessionScope.shopid}"
		userid="${sessionScope.user.userid}" deadline="${shopinfos.deadline}"
		uuid="${uuid}" money="${shopinfos.money}"></a>
	<!--此处是导航条 -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!--品牌logo -->
				<a class="navbar-brand text-success" href="shop!ShopList.action"><span
					class="glyphicon glyphicon-globe text-success">&nbsp;</span>F.Flame
					在线订餐系统</a>
			</div>
			<!--导航条实际内容 -->
			<div id="navbar" class="navbar-collapse collapse">
				<!--用于显示用户中心 -->
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a id="userid"
								userid="${sessionScope.user.userid}"
								username="${sessionScope.user.username}" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><img
									src="<%=path%>/${sessionScope.user.photo}"
									class="img20 img-circle" />&nbsp;&nbsp;${sessionScope.user.username}
									&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret">&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="user!gotoUserCenter.action">用户中心</a>
									</li>
									<li><a href="gift!getgiftList.action">积分商城</a></li>
									<c:if test="${sessionScope.user.authority eq 2}">
										<li><a href="<c:url value='/pages/pain/sender.jsp'/>">外卖接单</a></li>
									</c:if>
									<c:if test="${sessionScope.user.authority eq 3}">
										<li><a href="page!restaurantMain.action">店铺管理</a></li>
									</c:if>
									<c:if test="${sessionScope.user.authority >= 4}">
										<li><a href="page!adminMain.action">后台管理</a></li>
									</c:if>
									<li role="separator" class="divider"></li>
									<li><a href="user!logout.action">退出登录</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<!-- 显示登录 -->
						<ul class="nav navbar-nav navbar-right">
							<li><a id="logMsg" href="user!willLog.action">
									点击登录&nbsp;&nbsp;&nbsp;&nbsp;<span
									class="glyphicon glyphicon-log-in"></span> </a>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<div class="clearfix" style="margin-top: 50px;"></div>
	<%--主体内容 --%>
	<%--倒计时 --%>
	<h6 class="bg-warning"
		style="padding: 0px;margin: 0px;text-align: center;">
		<a style="font-size: 10px;">支付时间 : <span id="timer"
			style="color:red;font-size: 10px;"></span> </a>
	</h6>
	<%--信息 --%>
	<div class="container">
		<div class="row borderCircle" style="margin-top: 20px;">
			<table class="table">
				<tbody>
					<tr>
						<td><span class="pull-left" style="padding-left:10px;">订单名称</span>
							<span class="pull-right" style="padding-right:10px;">
								${shopinfos.rtname }外卖订单 </span></td>
					</tr>
					<tr>
						<td><span class="pull-left" style="padding-left:10px;">订单详情</span>
							<span class="pull-right" style="padding-right:10px;">
								${user.realname} <c:if test="${user.gender == '男'}">先生</c:if> <c:if
									test="${user.gender == '女'}">女士</c:if>
								${fn:substring(user.tel,0,3)}****${fn:substring(user.tel,6,10)}
								${user.address} </span></td>
					</tr>
					<tr style="border-bottom: 1px #ddd solid;">
						<td><span class="pull-left" style="padding-left:10px;">支付金额</span>
							<span class="pull-right" style="padding-right:10px;"> ￥ :
								${shopinfos.money} </span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" style="margin-top: 20px;">
			<form action="" class="form-horizontal" id="type">
				<table class="table">
					<tbody>
						<tr>
							<td style="margin-bottom: 0px;padding-bottom: 0px;">
								<div class="form-group">
									<div class="col-xs-10">
										<div class="media" style="margin:10px;">
											<div class="media-left"
												style="height: 40px;line-height: 30px;padding-left: 15px;">
												<img src="<%=path%>/image/xiong/alipay.png" class="img40" />
											</div>
											<div class="media-body" style="padding-top: 0px;">
												<h4 class="media-heading">
													支付宝支付<small>
												</h4>
												<div>
													<label class="label label-danger">支持免密支付</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-2" style="padding-top: 20px;">
										<input type="radio" name="pay" value="1"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td style="margin-bottom: 0px;padding-bottom: 0px;">
								<div class="form-group">
									<div class="col-xs-10">
										<div class="media" style="margin:10px;">
											<div class="media-left"
												style="height: 40px;line-height: 30px;padding-left: 15px;">
												<img src="<%=path%>/image/xiong/weixin.png" class="img40" />
											</div>
											<div class="media-body" style="padding-top: 0px;">
												<h4 class="media-heading">
													微信支付<small>
												</h4>
												<div>
													<label class="label label-danger">推荐安装微信5.0版本以上用户使用</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-2" style="padding-top: 20px;">
										<input type="radio" name="pay" value="2"/>
									</div>
								</div>
							</td>
						</tr>
						<tr style="border-bottom: 1px #ddd solid;">
							<td style="margin-bottom: 0px;padding-bottom: 0px;">
								<div class="form-group">
									<div class="col-xs-10">
										<div class="media" style="margin:10px;">
											<div class="media-left"
												style="height: 40px;line-height: 30px;padding-left: 15px;">
												<img src="<%=path%>/image/xiong/unionpay.png" class="img40" />
											</div>
											<div class="media-body" style="padding-top: 0px;">
												<h4 class="media-heading">
													银联支付<small>
												</h4>
												<div>
													<label class="label label-danger">支付服务由银联提供，无需开通网银</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-2" style="padding-top: 20px;">
										<input type="radio" name="pay" value="3"/>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header confirmPay">
				<a href="#" class="navbar-toggle collapsed pull-right span payNow"
					style="background-color:#444;"> <span
					class="glyphicon glyphicon-cutlery" style="color:#dab074;">&nbsp;</span>确认支付
				</a>
			</div>
			<div class="navbar-header sr-only return">
				<a href="shop!ShopList.action"
					class="navbar-toggle collapsed pull-right span"
					style="background-color:#444;"><span
					class="glyphicon glyphicon-circle-arrow-left"
					style="color:#dab074;">&nbsp;</span>返回主页 </a>
			</div>
			<div id="footbar" class="navbar-collapse collapse confirmPay">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#" class="span payNow"><span
							class="glyphicon glyphicon-cutlery">&nbsp;</span>确认支付</a></li>
					<li class="return sr-only"><a href="shop!ShopList.action"
						class="span"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回主页</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%--模态框(小) --%>
	<div id="modal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="font-weight:bold;">页面提示</h4>
				</div>
				<div class="modal-body">
					<span id="s-modal-body-addon" class=""></span> <span
						id="s-modal-body" class="h4"></span>
				</div>
				<div class="modal-footer" style="padding:5px;">
					<button type="button" class="btn btn-info btn-block"
						data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var EndTime = Math.floor(($("#infos").attr("deadline") - new Date()
			.getTime()) / 1000);
	var intvalue = 1;
	var timer2 = null;
	function startShow() {
		intvalue++;
		$("#timer").text(
				Math.floor(((EndTime - intvalue) / 60)).toString() + "分"
						+ ((EndTime - intvalue) % 60).toString() + "秒");
		if (intvalue >= EndTime) {
			$("#timer").parent().text("订单失效");
			endShow();
		}
	}
	function endShow() {
		window.clearTimeout(timer2);
		// 小bug
		$.post("order.getDeadline.action?uuid="+$("#infos").attr("uuid")+"&ti=15.0");
		$("#s-modal-body").text("超时！订单失效了！");
		$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
		$("#modal").modal({"show":true});
		$(".confirmPay").addClass("sr-only");
		$(".return").removeClass("sr-only");
	}
	timer2 = window.setInterval("startShow()", 1000);
	
	$(function(){
		$(".payNow").click(function(){
			var uuid = $("#infos").attr("uuid");
			var type = $("#type").serialize();
			var money = $("#infos").attr("money");
			if(type==""){
				$("#s-modal-body").text("请选择支付方式");
				$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
				$("#modal").modal({"show":true});
			}else{
				$.post("pay!payNow.action?"+type+"&uuid="+uuid+"&money="+money,function(result){
					if(result!="OK"){
						$("#s-modal-body").text(result);
						$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
						$("#modal").modal({"show":true});
					}else{
						window.location.href="/ffts/pages/xiong/orderDetails.jsp?uuid="+uuid;
					}
				});
			}
		});
	});
</script>
</html>
