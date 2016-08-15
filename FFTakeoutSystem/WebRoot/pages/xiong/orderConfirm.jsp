<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${empty cartinfos}">
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
		userid="${sessionScope.user.userid}"></a>
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
	<div class="clearfix" style="margin-top: 60px;"></div>
	<%--主体内容 --%>

	<div class="container">
		<div class="row" style="height: 60px;">
			<div class="media mediaCircle" style="margin:10px;">
				<div class="media-left"
					style="height: 60px;line-height: 60px;padding-left: 15px;">
					<span class="glyphicon glyphicon-map-marker"></span>
				</div>
				<div class="media-body" style="padding-top:5px;">
					<h4 class="media-heading">
						${sessionScope.user.realname}<small>&nbsp;&nbsp; <c:if
								test="${user.gender == '男'}">先生</c:if> <c:if
								test="${user.gender == '女'}">女士</c:if> &nbsp;&nbsp;${user.tel} </small>
					</h4>
					<div>
						<label class="label label-danger">家</label>&nbsp;&nbsp;${user.address}
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix" style="margin:25px;"></div>
		<div class="row borderCircle">
			<table class="table">
				<tbody>
					<tr>
						<td><span class="pull-left" style="padding-left:10px;">支付方式</span>
							<span class="pull-right" style="padding-right:10px;">
								<select>
									<option>在线支付</option>
									<option>货到付款</option>
								</select>
							</span>
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td><img src="<%=path%>/${shopinfos.rtpic}"
							class="img20 img-rounded" />&nbsp;&nbsp;<a
							href="shop!MenuList.action?rtid=${shopinfos.rtid}">${shopinfos.rtname}</a>
						</td>
					</tr>
					<tr></tr>
					<c:set var="sum" value="0" scope="page" />
					<c:forEach items="${cartinfos}" var="info" varStatus="st">
						<tr>
							<td><span class="pull-left" style="padding-left:10px;">${info.muname}</span><span
								class="pull-right"
								style="padding-right:10px;width:60px;text-align:right">￥
									${info.ocount * info.muprice}</span><span class="pull-right"
								style="padding-right:20px;">×${info.ocount}</span></td>
							<c:set var="sum" value="${sum + info.ocount * info.muprice}"
								scope="page" />
						</tr>
						<tr></tr>
						<c:if test="${st.last}">
							<tr style="border-bottom:1px #ddd solid;">
								<td><span class="pull-right" style="padding-right:10px;">待支付
										: ￥${sum}</span></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="clearfix" style="margin-bottom: 70px;"></div>
	</div>


	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<a href="order!setOrder.action?userid=${user.userid}&rtid=${rtid}&ti=15.0" class="navbar-toggle collapsed pull-right span"
					style="background-color:#444;"> <span
					class="sr-only">Toggle navigation</span> <span
					class="glyphicon glyphicon-cutlery" style="color:#dab074;">&nbsp;</span>提交订单
				</a> <a href="shop!MenuList.action?rtid=${rtid}" type="button"
					class="navbar-toggle pull-left span" id="clearCart"
					style="background-color:#444;"> <span
					class="glyphicon glyphicon-circle-arrow-left"
					style="color:#dab074;">&nbsp;</span>返回 </a>
			</div>
			<div id="footbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="shop!MenuList.action?rtid=${rtid}" class="span"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="order!setOrder.action?userid=${user.userid}&rtid=${rtid}&ti=15.0" class="span"><span
							class="glyphicon glyphicon-cutlery">&nbsp;</span>提交订单</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>
