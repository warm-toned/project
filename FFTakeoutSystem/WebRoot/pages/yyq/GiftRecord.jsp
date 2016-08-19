<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="<%=path%>/css/responsive.css" rel="stylesheet"
	type="text/css"></link>
<%--通用样式 --%>
<link href="<%=path%>/css/main2.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
</head>

<body>
	<a id="infos" class="sr-only" userid="${sessionScope.user.userid}"></a>
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
					class="glyphicon glyphicon-fire text-danger">&nbsp;</span>F.Flame
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
									<li><a href="user!gotoUserCenter.action">用户中心</a>
									</li>
									<li><a href="gift!getgiftList.action">积分商城</a>
									</li>
									<c:if test="${sessionScope.user.authority eq 2}">
										<li><a href="<c:url value='/pages/pain/sender.jsp'/>">外卖接单</a>
										</li>
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
	<!-- 主要内容 -->
	<div class="container">
		<c:forEach items="${record}" var="r">
			<div class="media" style="border-bottom: 1px #ccc solid;padding-bottom: 10px;">
				<div class="media-left">
					<img src="<%=path%>/${r.gpic}" class="img40 img-rounded" />
				</div>
				<div class="media-body">
					<div class="">
						<h6 style="padding: 0px;margin: 0px;font-size: 14px;">${r.gname}</h6>
						<p style="padding: 0px;margin: 0px;font-size: 12px;">
							<label class="label label-warning">积分</label> <a><span>${r.greqscore}</span>
							</a>
						</p>
						<p style="padding: 0px;margin-top: -25px;font-size: 12px;"
							class="pull-right">
							<label class="label label-info">兑换时间</label> <a><span>${r.gdate}</span>
							</a>
						</p>
						<c:choose>
							<c:when test="${r.grstatus==1}">
								<p style="padding: 0px;margin: 0px;font-size: 14px;">
									<label class="label label-success">已完成</label>
								</p>
							</c:when>
							<c:otherwise>
								<p style="padding: 0px;margin: 0px;font-size: 14px;">
									<label class="label label-danger">未送达</label>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
