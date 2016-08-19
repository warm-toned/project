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
<script type="text/javascript" src="<%=path%>/js/jquery.fly.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/requestAnimationFrame.js"></script>
<script type="text/javascript" src="<%=path%>/js/cartfly2.js"></script>
<style type="text/css">
.img100 {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<a id="infos" class="sr-only" rtid="${rtid}"
		userid="${sessionScope.user.userid}" muid="${muid}"></a>
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
				<!-- 返回按键 -->
				<a class="navbar-brand visible-xs pull-right"
					href="shop!MenuList.action?rtid=${rtid}" style="width: 60px;"><span
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
	<%--主体内容 --%>
	<div class="hidden-xs" style="margin-top: 10px;"></div>
	<div class="container">
		<%--商品图 --%>
		<div class="col-sm-6">
			<img id="goodspic" src="<%=path%>/${menuItem.mupic}"
				class="img-responsive img-thumbnail img100" alt="${menuItem.muname}">
		</div>
		<%--详情 --%>
		<div class="col-sm-6">
			<%--只在小窗口显示 --%>
			<div class="visible-xs" style="margin-top: 10px;">
				<a class="pull-left clearfix" style="margin-left: 10px;"
					href="shop!getMenuItem.action?muid=${menuItem.muid}">${menuItem.muname}</a>
				<label class="label label-danger pull-right">${menuItem.mudesc}</label>
				<div style="margin: 0px;padding: 0px;">&nbsp;</div>
				<p class="info" style="margin: 0px 0px 0px 10px;padding: 0px;">月售
					${menuItem.musale} 份</p>
				<h3 class="text-danger pull-left" style="margin:5px 0px 0px 10px;">
					<span class="glyphicon glyphicon-yen"></span>:
					<fmt:formatNumber value="${menuItem.muprice}" pattern="0.00" />
				</h3>
				<div class="pull-right" style="width: 120px;text-align: right;">
					<a index="0" muid="${menuItem.muid}"
						class="glyphicon glyphicon-minus-sign btnMinus <c:if test="${empty menuItem.ocount or menuItem.ocount == 0}">sr-only</c:if>"
						style="cursor:pointer"></a> <a class=""><label id=""
						class="label label-info badge"><c:if
								test="${not empty menuItem.ocount and menuItem.ocount != 0}">${menuItem.ocount}</c:if>
					</label> </a> <a index="0" muid="${menuItem.muid}"
						class="glyphicon glyphicon-plus-sign btnAdd"
						style="cursor:pointer"></a> <a index="0" class="btnAddToCart"
						style="background-color: #a5732a;color: white;padding: 2px 10px;border-radius:10px;font-size: 12px;cursor: pointer;">加入购物车</a>
				</div>
				<div class="clearfix"
					style="border-bottom: 2px #ccc solid;padding-bottom: 10px;"></div>
			</div>
			<%--只在大窗口显示 --%>
			<div class="hidden-xs">
				<div class="project-name overflow">
					<h3 class="bold" style="font-size:40px;">${menuItem.muname}</h3>
				</div>
				<div class="project-name overflow">
					<h2></h2>
					<ul class="nav navbar-nav navbar-default"
						style="background:none;padding:0;">
						<li><a href="#" class="text-danger"
							style="font-size:12px;padding:0;padding-right:15px;"><span
								class="glyphicon glyphicon-tag">&nbsp;</span>月售 <span
								id="goodsSales">${menuItem.musale}</span> 份</a></li>
					</ul>
				</div>
				<div class="project-name overflow">
					<h2></h2>
					<ul class="nav navbar-nav navbar-default"
						style="background:none;padding:0;">
						<li><a href="#" class="text-danger"
							style="font-size:12px;padding:0;padding-right:15px;"> <span
								class="glyphicon glyphicon-tag">&nbsp;</span><span>${menuItem.mutype}</span>
						</a></li>
					</ul>
				</div>
				<div class="project-name overflow">
					<ul class="navbar-default"
						style="background:none;margin-top:30px;padding:0;">
						<li class="page-header"><span class="glyphicon glyphicon-yen"
							style="font-size:30px;"></span><a href="#" class="text-danger"
							style="font-size:50px;padding:0;padding-right:15px;color:#a21e46;font-weight:bold;">
								: <fmt:formatNumber value="${menuItem.muprice}" pattern="0.00" />
						</a></li>
					</ul>
				</div>
				<div class="pull-right" style="width: 220px;text-align: right">
					<a index="1" muid="${menuItem.muid}"
						class="glyphicon glyphicon-minus-sign btnMinus <c:if test="${empty menuItem.ocount or menuItem.ocount == 0}">sr-only</c:if>"
						style="cursor:pointer;font-size: 26px;"></a> <a class=""><label
						id="" style="font-size: 20px;margin-bottom: 16px;"
						class="label label-info badge"><c:if
								test="${not empty menuItem.ocount and menuItem.ocount != 0}">${menuItem.ocount}</c:if>
					</label> </a> <a index="1" muid="${menuItem.muid}"
						class="glyphicon glyphicon-plus-sign btnAdd"
						style="cursor:pointer;font-size: 26px;"></a> <a index="1"
						class="btnAddToCart"
						style="background-color: #a5732a;color: white;padding: 5px 20px;border-radius:20px;font-size: 20px;cursor: pointer;">加入购物车</a>
				</div>
				<div class="pull-left">
					<a><span style="font-size: 20px;">点击购买:</span> </a>
				</div>
			</div>
		</div>

		<div style="margin: 40px 0px 0px;" class="col-sm-6">
			<table class="table borderCircle2">
				<thead>
					<tr>
						<th>
							<h3 style="padding: 5px;margin: 0px;">
								商品评价
								<c:if test="${menuItem.haoping >= 0.8}">
									<small class="pull-right" style="margin-top: 10px;"><label
										class="label label-success">${menuMess.info.sumnum
											}个评价</label> 好评率 <fmt:formatNumber value="${menuItem.haoping * 100}"
											pattern="0.0" />% </small>
								</c:if>
								<c:if test="${menuItem.haoping >=0 and menuItem.haoping < 0.8}">
									<small class="pull-right" style="margin-top: 10px;"><label
										class="label label-success">${menuMess.info.sumnum
											}个评价</label> 好评率低于同类均值</small>
								</c:if>
								<c:if test="${menuItem.haoping == -1}">
									<small class="pull-right" style="margin-top: 10px;">
										暂无评价</small>
								</c:if>
							</h3></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuMess.list}" var="m">
						<tr>
							<td><span class="pull-left">${m.mmdate} <c:choose>
										<c:when test="${m.mmscore == 5}">
											<label class="label label-success">好评</label>
										</c:when>
										<c:when test="${m.mmscore == 4}">
											<label class="label label-primary">好评</label>
										</c:when>
										<c:when test="${m.mmscore == 3}">
											<label class="label label-default">中评</label>
										</c:when>
										<c:when test="${m.mmscore == 2}">
											<label class="label label-warning">差评</label>
										</c:when>
										<c:when test="${m.mmscore == 1}">
											<label class="label label-danger">差评</label>
										</c:when>
									</c:choose> </span> <span class="pull-right">${m.mm}&nbsp;&nbsp;<img
									class="img20 img-circle" src="<%=path%>/${m.photo}" /> </span> <span
								class="clearfix" style="margin: 0px;padding: 0px;">&nbsp;</span>
								<span class="">${m.mmcontent}</span>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="center">
							<%--页码条:两个字，完美！ --%> <c:choose>
								<c:when
									test="${not empty menuMess.info.sumpage and menuMess.info.sumpage != 0}">
									<c:choose>
										<c:when test="${menuMess.info.sumpage > 5}">
											<c:set var="begin" value="${menuMess.info.nowpage - 2}" />
											<c:set var="end" value="${menuMess.info.nowpage + 2}" />
										</c:when>
										<c:otherwise>
											<c:set var="begin" value="1" />
											<c:set var="end" value="${menuMess.info.sumpage}" />
										</c:otherwise>
									</c:choose>
									<c:if test="${begin < 1}">
										<c:set var="begin" value="1" />
										<c:set var="end" value="5" />
									</c:if>
									<c:if test="${end > menuMess.info.sumpage}">
										<c:set var="begin" value="${menuMess.info.sumpage - 4}" />
										<c:set var="end" value="${menuMess.info.sumpage}" />
									</c:if>
									<div class="portfolio-pagination">
										<ul class="pagination">
											<li><a
												href="shop!getMenuItem.action?muid=${menuItem.muid}&rtid=${rtid}&cp=1"><span>首页</span>
											</a></li>
											<%--设置上一页是否被激活 --%>
											<c:choose>
												<c:when test="${menuMess.info.nowpage > 1}">
													<li><a
														href="shop!getMenuItem.action?muid=${menuItem.muid}&rtid=${rtid}&cp=${menuMess.info.nowpage-1}"><span
															class="glyphicon glyphicon-chevron-left"></span> </a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="disabled"><a href="javascript:;"><span
															class="glyphicon glyphicon-chevron-left"></span> </a>
													</li>
												</c:otherwise>
											</c:choose>
											<%--设置页数的开始页码 --%>
											<c:forEach var="i" begin="${begin}" end="${end}">
												<c:choose>
													<c:when test="${menuMess.info.nowpage == i}">
														<li class="disabled"><a href="javascript:;"><span>${i}</span>
														</a>
														</li>
													</c:when>
													<c:otherwise>
														<li><a
															href="shop!getMenuItem.action?muid=${menuItem.muid}&rtid=${rtid}&cp=${i}"><span>${i}</span>
														</a>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											<%--设置下一页是否被激活 --%>
											<c:choose>
												<c:when
													test="${menuMess.info.nowpage != menuMess.info.sumpage}">
													<li><a
														href="shop!getMenuItem.action?muid=${menuItem.muid}&rtid=${rtid}&cp=${menuMess.info.nowpage+1}"><span
															class="glyphicon glyphicon-chevron-right"></span> </a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="disabled"><a href="javascript:;"><span
															class="glyphicon glyphicon-chevron-right"></span> </a>
													</li>
												</c:otherwise>
											</c:choose>
											<li><a
												href="shop!getMenuItem.action?muid=${menuItem.muid}&rtid=${rtid}&cp=${menuMess.info.sumpage}"><span>尾页</span>
											</a></li>
										</ul>
									</div>
								</c:when>
								<c:otherwise>
									<div class="portfolio-pagination">
										<ul class="pagination">
											<li>还没有顾客评价</li>
										</ul>
									</div>
								</c:otherwise>
							</c:choose> <%--/页码条 --%>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="" style="margin-bottom: 80px;"></div>
		</div>
	</div>
	<%--底部 --%>
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<i class="visible-xs pull-left end"></i> <a
					class="pull-left visible-xs carts" style="width:60px;"
					role="button" data-placement="top" data-content="加入购物车成功"></a>
				<button id="cart" type="button"
					class="navbar-toggle collapsed pull-left visible-xs cartes"
					data-toggle="collapse" data-target="#cartbar" aria-expanded="false"
					aria-controls="navbar" style="background-color:#444;">
					<span class="sr-only">Toggle navigation</span> <span
						class="glyphicon glyphicon-shopping-cart" style="color: white;">&nbsp;</span>
					<span style="color: white;">Cart</span>
				</button>
				<a href="#" class="navbar-toggle collapsed pull-right span orderNow"
					data-toggle="collapse" data-target="" aria-expanded="false"
					aria-controls="navbar" style="background-color:#444;"> <span
					class="sr-only">Toggle navigation</span> <span
					class="glyphicon glyphicon-cutlery" style="color:#dab074;">&nbsp;</span>立即下单
				</a> <a href="#" type="button" class="navbar-toggle pull-right span"
					id="clearCart" style="background-color:#444;"> <span
					class="glyphicon glyphicon-remove-circle" style="color:#dab074;">&nbsp;</span>清空
				</a>
			</div>
			<!--导航条实际内容 -->
			<div id="footbar" class="navbar-collapse collapse">
				<!--站内导航 -->
				<ul class="nav navbar-nav navbar-left">
					<%--此项只为空一块出来 --%>
					<li><a href="shop!MenuList.action?rtid=${rtid}"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回</a></li>
					<li><div class="col-md-1"></div></li>
					<li><a href="#" class="orderNow"><span
							class="glyphicon glyphicon-cutlery">&nbsp;</span>立即下单</a></li>
				</ul>
				<!--用于显示购物车 -->
				<ul class="nav navbar-nav navbar-right hidden-xs">
					<i class="hidden-xs pull-left end"></i>
					<a class="pull-left hidden-xs carts" style="width:60px;"
						role="button" data-placement="top" data-content="加入购物车成功"></a>
					<li><a href="#" id="showCart"><span
							class="glyphicon glyphicon-shopping-cart">&nbsp;</span>我的购物车</a></li>
					<li><a href="#" id="clearCart2"><span
							class="glyphicon glyphicon-remove-circle" style="color:#dab074;">&nbsp;</span>清空
					</a></li>
				</ul>
			</div>
			<%--商品详细列表 --%>
			<div id="cartbar" class="navbar-collapse collapse sr-only">
				<ul id="cartList" class="nav navbar-nav navbar-left">

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
	<%--模态框(中) --%>
	<div id="modal2" class="modal fade bs-example-modal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2"
						style="font-weight:bold;">页面提示</h4>
				</div>
				<div class="modal-body">
					<div id="m-modal-body" class="h4"></div>
				</div>
				<div class="modal-footer" style="padding:5px;">
					<a href="#" class="btn btn-danger orderNow"><span
						class="glyphicon glyphicon-cutlery">&nbsp;</span>立即下单</a>
					<button type="button" class="btn btn-info" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

