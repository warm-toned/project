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
<link href="<%=path%>/css/font-awesome.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/animate.min.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/lightbox.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/responsive.css" rel="stylesheet"
	type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/css/fileinput.css" />
<%--通用样式 --%>
<link href="<%=path%>/css/main2.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/fileinput2.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.raty.js"></script>
<script type="text/javascript" src="<%=path%>/js/xiongli/star.js"></script>
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
	<div class="clearfix" style="margin-top: 60px;"></div>
	<%--页面主体 --%>
	<div class="container">
		<form action="assess!setAssess.action" method="post" id="assFrm">
			<input type="hidden" name="uscore" value="${param.uscore}"/> 
			<h5 style="text-align: center;margin: 0px;">
				<img class="img30 img-circle" src="<%=path%>/${mapshop.rtpic }" />&nbsp;&nbsp;${mapshop.rtname}
			</h5>
			<div class="page-header"
				style="padding: 0px; margin: 0px;font-size: 11px;text-align: center">
				<small>为商家服务打分</small>
			</div>
			<div class="" id="reststar"
				style="margin: 10px auto;padding-left: 50px;"></div>
			<textarea name="rtAssess" rows="3" class="sr-only form-control"
				id="rtAssess" style="font-size: 10px;"></textarea>
			<input type="hidden" name="uuid" value="${param.uuid}"/> 
			<input type="hidden" name="rtid" value="${mapshop.ortid}"/> 
			<div class="page-header"
				style="padding: 0px; margin: 0px;font-size: 11px;text-align: center">
				<small>请为商品打分</small>
			</div>
			<c:forEach items="${mapshop.menuList}" var="item" varStatus="s">
				<input type="hidden" name="muScore" class="muScore" value="0"/>
				<h6>
					<a class="pull-left"><span>${item.muname}</span> </a>
					<div class="pull-right afg">
						<a class="assFinger" index="${s.index}"><span>点评</span> </a>
					</div>
					<div class="pull-right status sr-only">
						<a class="assFingerGood" index="${s.index}"><span
							class="glyphicon glyphicon-thumbs-up"></span>推荐</a><a
							class="assFingerBad" index="${s.index}"><span
							class="glyphicon glyphicon-thumbs-down"></span>吐槽</a>
					</div>
				</h6>
				<div class="clearfix" style="padding: 0px;margin: 5px;"></div>
				<textarea class="form-control sr-only menuAssess" name="menuAssess"
					rows="2" placeholder="140字以内" style="font-size: 10px;"></textarea>
			</c:forEach>
		</form>
	</div>


	<%--底部 --%>
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<a href="#"
					class="navbar-toggle collapsed pull-right span setAssess"
					aria-controls="navbar"><span
					class="glyphicon glyphicon-tasks"
					style="color:#dab074;margin-right: 0px;">&nbsp;</span>提交评价 </a> <a
					href="javascript:history.back();"
					class="navbar-toggle pull-left span"
					astyle="background-color:#444;"> <span
					class="glyphicon glyphicon-circle-arrow-left"
					style="color:#dab074;">&nbsp;</span>返回 </a>
			</div>
			<!--导航条实际内容 -->
			<div id="footbar" class="navbar-collapse collapse">
				<!--站内导航 -->
				<ul class="nav navbar-nav navbar-left">
					<%--此项只为空一块出来 --%>
					<li><a href="javascript:history.back();"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回</a></li>
				</ul>
				<!--用于显示购物车 -->
				<ul class="nav navbar-nav navbar-right hidden-xs">
					<li><a href="#" id="" class="setAssess"><span
							class="glyphicon glyphicon-tasks">&nbsp;</span>提交评价</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%--模态框(小) --%>
	<div id="modal" class="modal fade bs-example-modal-sm" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
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
					<a href="page!loginPage.action" id="logBtn" type="button"
						class="btn btn-info sr-only">登陆</a>
					<button type="button" class="btn btn-info" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
