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
	<a id="infos" class="sr-only" userid="${sessionScope.user.userid}"
		cp="${cp}"></a>
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
									<li><a href="user!gotoUserCenter.action">用户中心</a></li>
									<li><a href="gift!getgiftList.action">积分商城</a></li>
									<c:if test="${sessionScope.user.authority eq 2}">
										<li><a href="<c:url value='/pages/pain/sender.jsp'/>">外卖接单</a>
										</li>
									</c:if>
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
	<div class="container" style="padding: 0px;">
		<ul style="margin: 0px;padding: 0px;">
			<c:forEach items="${shop.list}" var="s" varStatus="su">
				<li class="sd" index="${su.index}"
					style="<c:if test="${su.first}">border-top:1px #ccc solid;</c:if> border-bottom:1px #ccc solid;">
					<div class="media" style="padding: 0px;margin: 3px 0px;">
						<div class="media-left">
							<a index="${su.index}"
								href="shop!MenuList.action?rtid=${s.rtid}&cp=${cp}"
								class="pull-left detail" style="padding: 10px;"><img
								src="<%=path%>/${s.rtpic}" class="img60 img-rounded" /> </a>
						</div>
						<div class="media-body" style="padding: 5px 0px 0px;margin: 0px;">
							<a href="shop!MenuList.action?rtid=${s.rtid}&cp=${cp}"
								style="font-size:15px;">${s.rtname}</a><br /> <label
								class="label label-success pull-left"
								style="margin:0px;font-size:12px;">${s.rtaddr}</label> <br />
							<h5 style="margin:0px;padding:0px;font-size:10px;">${s.rtcontent}</h5>
						</div>
					</div></li>
			</c:forEach>
		</ul>
	</div>

	<c:choose>
		<c:when
			test="${not empty shop.info.sumpage and shop.info.sumpage != 0}">
			<c:choose>
				<c:when test="${shop.info.sumpage > 5}">
					<c:set var="begin" value="${shop.info.nowpage - 2}" />
					<c:set var="end" value="${shop.info.nowpage + 2}" />
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="1" />
					<c:set var="end" value="${shop.info.sumpage}" />
				</c:otherwise>
			</c:choose>
			<c:if test="${begin < 1}">
				<c:set var="begin" value="1" />
				<c:set var="end" value="5" />
			</c:if>
			<c:if test="${end > shop.info.sumpage}">
				<c:set var="begin" value="${shop.info.sumpage - 4}" />
				<c:set var="end" value="${shop.info.sumpage}" />
			</c:if>
			<div class="portfolio-pagination">
				<ul class="pagination">
					<li><a href="shop!ShopList.action?cp=1"><span>首页</span> </a>
					</li>
					<%--设置上一页是否被激活 --%>
					<c:choose>
						<c:when test="${shop.info.nowpage > 1}">
							<li><a href="shop!ShopList.action?cp=${shop.info.nowpage-1}"><span
									class="glyphicon glyphicon-chevron-left"></span> </a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="javascript:;"><span
									class="glyphicon glyphicon-chevron-left"></span> </a></li>
						</c:otherwise>
					</c:choose>
					<%--设置页数的开始页码 --%>
					<c:forEach var="i" begin="${begin}" end="${end}">
						<c:choose>
							<c:when test="${shop.info.nowpage == i}">
								<li class="disabled"><a href="javascript:;"><span>${i}</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="shop!ShopList.action?cp=${i}"><span>${i}</span>
								</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%--设置下一页是否被激活 --%>
					<c:choose>
						<c:when test="${shop.info.nowpage != shop.info.sumpage}">
							<li><a href="shop!ShopList.action?cp=${shop.info.nowpage+1}"><span
									class="glyphicon glyphicon-chevron-right"></span> </a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="javascript:;"><span
									class="glyphicon glyphicon-chevron-right"></span> </a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="shop!ShopList.action?cp=${shop.info.sumpage}"><span>尾页</span>
					</a>
					</li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="portfolio-pagination">
				<ul class="pagination">
					<li>系统崩溃中...请在一小时后重试...Orz</li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
	<%--/页码条 --%>
</body>
</html>
