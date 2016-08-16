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
<link rel="stylesheet" href="<%=path%>/css/fileinput.css" />
<%--通用样式 --%>
<link href="<%=path%>/css/main2.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fly.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/requestAnimationFrame.js"></script>
<script type="text/javascript" src="<%=path%>/js/pain/sender.js"></script>
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
	<div class="container">
		<%--导航 --%>
		<ul class="nav nav-tabs visible-xs">
			<li index="0" name="nav" class="active"><a href="#">全部订单</a></li>
			<li index="1" name="nav"><a href="#">已接订单&nbsp;&nbsp;&nbsp;&nbsp;<span
					class="badge pull-right"></span>
			</a><i class="visible-xs pull-left end"></i> <i index="1"
				class="pull-left visible-xs carts" style="width:60px;"
				data-placement="bottom" data-content=""></i></li>
			<li index="2" name="nav"><a href="#">已完成订单</a><i
				class="visible-xs pull-left svend"></i> <i index="1"
				class="pull-left visible-xs sended" style="width:60px;"
				data-placement="bottom" data-content=""></i></li>
		</ul>
		<a class="sr-only" id="pginfo" page="1" size="10" page2="1" size2="10"></a>
		<div class="col-md-6" id="usable">
			<div class="dropdown visible-xs"
				style="font-size: 12px;margin-top: 10px;">
				<button type="button"
					class="btn borderGray2 dropdown-toggle pull-right"
					data-toggle="dropdown">
					<span id="pagecount">每页显示 10 条订单 </span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu pull-right" style="margin-top: 25px;">
					<li><a href="#" class="pagereact" value="2"><span>2</span>
					</a></li>
					<li><a href="#" class="pagereact" value="3"><span>3</span>
					</a></li>
					<li><a href="#" class="pagereact" value="5"><span>5</span>
					</a></li>
					<li><a href="#" class="pagereact" value="10"><span>10</span>
					</a></li>
					<li><a href="#" class="pagereact" value="20"><span>20</span>
					</a></li>
					<li><a href="#" class="pagereact" value="50"><span>50</span>
					</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#" class="pagereact" value="10"><span>重置</span>
					</a></li>
				</ul>
			</div>
			<div class="clearfix" style="margin-top: 10px;"></div>
			<div id="orders">
				<div class="media"
					style="border-top: 1px #000 solid;margin-top: 10px;">
					<div class="media-left">
						<img src="<%=path%>/image/defaults/userdefault.jpg"
							class="img40 img-circle" />
					</div>
					<div class="media-body">
						<h6>XXX店订单</h6>
					</div>
				</div>
			</div>
			<div class="portfolio-pagination">
				<ul class="pagination">
					<li><a id="first" href="" page="1" class="pageaction"><span>首页</span>
					</a></li>
					<%--设置上一页是否被激活 --%>
					<li><a href="" class="pageaction" page=""><span
							class="glyphicon glyphicon-chevron-left"></span> </a></li>
					<%--设置页数的开始页码 --%>
					<li><a href="" class="pageaction" page=""><span>1</span> </a>
					</li>
					<li><a href="" class="pageaction" page=""><span>2</span> </a>
					</li>
					<li><a href="" class="pageaction" page=""><span>3</span> </a>
					</li>
					<li><a href="" class="pageaction" page=""><span>4</span> </a>
					</li>
					<li><a href="" class="pageaction" page=""><span>5</span> </a>
					</li>
					<%--设置下一页是否被激活 --%>
					<li><a href="" class="pageaction" page=""><span
							class="glyphicon glyphicon-chevron-right"></span> </a></li>
					<li><a href="" class="pageaction" page=""><span>尾页</span>
					</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="col-md-6 hidden-xs" id="got">
			<h3 class="page-header hidden-xs">
				已接订单&nbsp;&nbsp;&nbsp;&nbsp;<span class="badge"></span> <i
					class="hidden-xs pull-left end"></i> <i index="1"
					class="pull-left hidden-xs carts" style="width:60px;"
					data-placement="bottom" data-content="加入购物车成功"></i>
			</h3>
			<div id="saved" style="margin-top: 20px;"></div>
		</div>
		<div class="col-md-6 hidden-xs" id="complete">
			<h3 class="page-header hidden-xs">
				已送订单 <i index="1" class="pull-left hidden-xs sended"
					style="width:60px;" data-placement="top" data-content=""></i>
			</h3>
			<div class="dropdown visible-xs"
				style="font-size: 12px;margin-top: 10px;">
				<button type="button"
					class="btn borderGray2 dropdown-toggle pull-right"
					data-toggle="dropdown">
					<span id="page2count">每页显示 10 条订单 </span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu pull-right" style="margin-top: 25px;">
					<li><a href="#" class="page2react" value="2"><span>2</span>
					</a></li>
					<li><a href="#" class="page2react" value="3"><span>3</span>
					</a></li>
					<li><a href="#" class="page2react" value="5"><span>5</span>
					</a></li>
					<li><a href="#" class="page2react" value="10"><span>10</span>
					</a></li>
					<li><a href="#" class="page2react" value="20"><span>20</span>
					</a></li>
					<li><a href="#" class="page2react" value="50"><span>50</span>
					</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#" class="page2react" value="10"><span>重置</span>
					</a></li>
				</ul>
			</div>
			<div class="clearfix" style="margin-top: 10px;"></div>
			<div id="sded">
			</div>
			<div class="portfolio-pagination">
				<ul class="pagination">
					<li><a id="first" href="" page2="1" class="page2action"><span>首页</span>
					</a></li>
					<%--设置上一页是否被激活 --%>
					<li><a href="" class="page2action" page2=""><span
							class="glyphicon glyphicon-chevron-left"></span> </a></li>
					<%--设置页数的开始页码 --%>
					<li><a href="" class="page2action" page2=""><span>1</span> </a>
					</li>
					<li><a href="" class="page2action" page2=""><span>2</span> </a>
					</li>
					<li><a href="" class="page2action" page2=""><span>3</span> </a>
					</li>
					<li><a href="" class="page2action" page2=""><span>4</span> </a>
					</li>
					<li><a href="" class="page2action" page2=""><span>5</span> </a>
					</li>
					<%--设置下一页是否被激活 --%>
					<li><a href="" class="page2action" page2=""><span
							class="glyphicon glyphicon-chevron-right"></span> </a></li>
					<li><a href="" class="page2action" page2=""><span>尾页</span>
					</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

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
