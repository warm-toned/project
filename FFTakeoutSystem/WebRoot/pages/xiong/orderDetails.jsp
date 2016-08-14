<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
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
<script type="text/javascript" src="<%=path%>/js/xiongli/order-load.js"></script>
</head>

<body>
	<a id="infos" class="sr-only" rtid="${sessionScope.shopid}"
		userid="${sessionScope.user.userid}" uuid="${param.uuid}"></a>
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
									<c:if test="${sessionScope.user.authority eq 2}">
										<li><a href="page!restaurantMain.action">外卖接单</a></li>
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
	<div class="clearfix" style="margin-top: 60px;"></div>
	<%--主体内容 --%>
	<div class="container">
		<div class="row clearfix">
			<div class="panel panel-default borderCircle"
				style="margin: 0px 10px 0px 10px;">
				<div class="panel-body">
					<div class="media">
						<div class="media-left">
							<img src="<%=path%>/image/defaults/shop.png" class="img40" />
						</div>
						<div class="media-body">
							<h5 style="padding: 0px;margin: 0px;" id="orderStatus">订单状态</h5>
							<a><span id="orderDate">2016年8月11日16:01:36</span>
							</a>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="font-size: 10px;padding-bottom: 16px;" id="close">
					<span class="pull-left statusSpan">等待商家接单</span>
					<div style="text-align: center;border-bottom: 1px #ddd dotted;">
						<span style="padding-bottom: 5px;" class="statusSpan">配送中...</span>
						<span class="pull-right statusSpan">已送达</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" style="padding: 10px;margin-top: 20px;margin-bottom:70px;">
			<table class="table">
				<tbody id="main">
					<tr>
						<td><span class="pull-left" style="padding-left:10px;">
							<img id="rtpic" src="" class="img40"/>
						</span>
							<a id="rtname" href="" style="padding-left: 10px;line-height: 30px"></a>
						</td>
					</tr>
					<tr class="orderInfo">
						<td><span class="pull-left" style="padding-left:10px;">支付方式</span>
							<span class="pull-right" style="padding-right:10px;">
							</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<a href="shop!ShopList.action"
					class="navbar-toggle collapsed pull-left span"
					style="background-color:#444;"><span
					class="glyphicon glyphicon-circle-arrow-left"
					style="color:#dab074;">&nbsp;</span>返回主页 </a>
			</div>
			<div id="footbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-left">
					<li class="return"><a href="shop!ShopList.action" class="span"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回主页</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>
