<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
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
<link href="<%=path%>/css/main2.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/goodslist.js"></script>
<script type="text/javascript" src="<%=path%>/js/wow.min.js"></script>
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
					class="glyphicon glyphicon-globe text-success">&nbsp;</span>F.Flame 在线订餐系统</a><a class="navbar-brand visible-xs"
					href="shop!ShopList.action"><span
					class="glyphicon glyphicon-circle-arrow-left text-danger"></span> </a>
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
								aria-expanded="false"><img src="<%=path%>/${sessionScope.user.photo}" class="img20 img-circle"/>&nbsp;&nbsp;${sessionScope.user.username}
									&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret">&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="<c:url value='/page?method=userCenter&userid=${sessionScope.userid}'/>">用户中心</a>
									</li>
									<c:if test="${sessionScope.user.authority eq 3}">
										<li><a href="page!restaurantMain.action">店铺管理</a>
										</li>
									</c:if>
									<c:if test="${sessionScope.user.authority >= 4}">
										<li><a href="page!adminMain.action">后台管理</a>
										</li>
									</c:if>
									<li role="separator" class="divider"></li>
									<li><a href="user!logout.action">退出登录</a>
									</li>
								</ul>
							</li>
						</ul>
					</c:when>
					<c:otherwise>
						<!-- 显示登录 -->
						<ul class="nav navbar-nav navbar-right">
							<li><a id="logMsg" href="user!willLog.action">
									点击登录&nbsp;&nbsp;&nbsp;&nbsp;<span
									class="glyphicon glyphicon-log-in"></span> </a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<div class="clearfix" style="margin-top: 60px;"></div>
	<!-- 主要内容 -->
	<div class="container">
		<ul style="margin: 0px;padding: 0px;">
			<c:forEach items="${shop.list}" var="s" varStatus="su">
				<li><c:if test="${su.first}">
						<div class="page-header"
							style="margin:5px 0px 5px 0px;padding: 0px;"></div>
					</c:if>
					<div class="media panel panel-info">
						<a href="shop!MenuList.action?rtid=${s.rtid}" class="pull-left"><img
							src="<%=path%>/${s.rtpic}" width="143" height="112" border="0" />
						</a>
						 <div class="media-body panel-body">
							<a href="shop!MenuList.action?rtid=${s.rtid}">${s.rtname}</a>
						<h5>${s.rtaddr}</h5>
						<h5>${s.rtcontent}</h5>
						</div>
					</div>
					<div class="page-header clearfix"
						style="margin:5px 0px 5px 0px;padding: 0px;"></div></li>
			</c:forEach>
		</ul>
	</div>
	<h1 align="center">商店信息</h1>
	<table align="center">

		<tr>
			<td colspan="2" align="right"><a
				href="shop!ShopList.action?nowpage=1">首页</a> <c:if
					test="${shop.info.nowpage==1 }">上一页</c:if> <c:if
					test="${shop.info.nowpage>1 }">
					<a href="shop!ShopList.action?nowpage=${shop.info.nowpage-1 }">上一页</a>
				</c:if> <c:if test="${shop.info.nowpage<shop.info.sumpage}">
					<a href="shop!ShopList.action?nowpage=${shop.info.nowpage+1 }">下一页</a>
				</c:if> <c:if test="${shop.info.nowpage==shop.info.sumpage}">下一页</c:if> <a
				href="shop!ShopList.action?nowpage=${shop.info.sumpage}">尾页</a></td>
		</tr>
	</table>
</body>
</html>
