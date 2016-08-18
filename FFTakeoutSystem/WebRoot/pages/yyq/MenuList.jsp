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
<script type="text/javascript" src="<%=path%>/js/cartfly.js"></script>
</head>
<body onscroll="setMenuBar(document.documentElement.scrollTop||document.body.scrollTop)">
	<a id="infos" class="sr-only" rtid="${rtid}"
		userid="${sessionScope.user.userid}" index="${param.index}"></a>
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
				<!-- 返回按键 -->
				<a class="navbar-brand visible-xs pull-right"
					href="shop!ShopList.action?cp=${cp}" style="width: 60px;"><span
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
							<li><a id="logMsg" href="user!willLog.action?from=MenuList">
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

	<%--菜单 --%>
	<div id="body" class="container">
		<div class="media panel panel-warning">
			<a class="pull-left" href="#"> <img
				src="<%=path%>/${shopById.rtpic}" width="67" height="67"
				style="margin:0px;padding:0px;" /> </a>
			<div class="media-body panel-heading">
				<label class="pull-right" style="text-align: center"><fmt:formatNumber
						value="${shopById.score}" pattern="0.0" /><br /> <small>综合评分</small>
				</label>
				<h4 class="media-heading">${shopById.rtname}</h4>
				<label class="label label-primary">${shopById.rtaddr}</label>
			</div>
		</div>
		<ul class="nav nav-tabs visible-xs">
			<li index="0" name="nav" class="active"><a href="#">菜单</a>
			</li>
			<li index="1" name="nav"><a href="#">评论</a>
			</li>
			<li index="2" name="nav"><a href="#">商店</a>
			</li>
		</ul>
		<div id="menu">
			<div class="col-xs-3 col-md-1" id="menubar"
				style="margin: 0px;padding: 0px;position: fixed;">
				<div class="sidebar-item categories">
					<ul class="nav navbar-stacked">
						<c:forEach items="${MenuType}" var="m" varStatus="s">
							<li class=""><c:if test="${s.first}">
									<div class="page-header"
										style="margin:5px 0px 5px 0px;padding: 0px;"></div>
								</c:if> <a href="#type${s.index}"> <span>${m.mutype}</span> </a> <c:if
									test="${s.last}">
									<div class="page-header"
										style="margin:5px 0px 5px 0px;padding: 0px;"></div>
								</c:if></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-xs-10 col-xs-push-2 col-md-8 col-md-push-1"
				style="margin: 0px;padding: 0px;">
				<ul class="">
					<c:set var="tindex" value="0" scope="page" />
					<c:forEach items="${MenuList}" var="m" varStatus="s">
						<%--找page域中有没有当前类 --%>
						<c:if test="${empty pageScope.type or pageScope.type != m.mutype}">
							<a name="type${tindex}" class="sr-only"
								style="margin-top: -60px;"></a>
							<div class="panel panel-success" style="margin: 4px 0px 4px 0px;">
								<div class="panel-heading"
									style="height: 20px;line-height: 20px;font-size: 12px;padding:0px 0px 0px 10px;margin:0px;">${m.mutype}</div>
							</div>
							<div class="page-header"
								style="margin:4px 0px 10px 0px;padding: 0px;"></div>
							<c:set var="type" value="${m.mutype}" scope="page" />
							<c:set var="tindex" value="${pageScope.tindex + 1}" scope="page" />
						</c:if>
						<li>
							<div class="media">
								<a href="shop!getMenuItem.action?muid=${m.muid}&rtid=${rtid}"><img
									class="img60 pull-left img-rounded" src="<%=path%>/${m.mupic}"
									border="0" /> </a>
								<div class="media-body">
									<a class="pull-left clearfix" style="margin-left: 10px;"
										href="shop!getMenuItem.action?muid=${m.muid}&rtid=${rtid}">${m.muname}</a>
									<label class="label label-danger pull-right">${m.mudesc}</label>
									<div style="margin: 0px;padding: 0px;">&nbsp;</div>
									<p class="info" style="margin: 0px 0px 0px 10px;padding: 0px;">
										月售 ${m.musale} 份
										<c:if test="${m.haoping >= 0.8}">
											<small class="text-mute"> 好评率 <fmt:formatNumber
													value="${m.haoping * 100}" pattern="0.00" />% </small>
										</c:if>
										<c:if test="${m.haoping >=0 and m.haoping < 0.8}">
											<small class="text-mute"> 好评率低于同类均值</small>
										</c:if>
										<c:if test="${m.haoping == -1}">
											<small> 暂无评价</small>
										</c:if>
									</p>
									<h3 class="text-danger pull-left"
										style="margin:0px 0px 0px 10px;">
										<span class="glyphicon glyphicon-yen"></span>:
										<fmt:formatNumber value="${m.muprice}" pattern="0.00" />
									</h3>
									<div class="pull-right">
										<a index="${s.index}" muid="${m.muid}"
											class="glyphicon glyphicon-minus-sign btnMinus <c:if test="${empty m.ocount or m.ocount == 0}">sr-only</c:if>"
											style="cursor:pointer"></a> <a class=""><label id=""
											class="label label-info badge"><c:if
													test="${not empty m.ocount and m.ocount != 0}">${m.ocount}</c:if>
										</label> </a> <a index="${s.index}" muid="${m.muid}"
											class="glyphicon glyphicon-plus-sign btnAdd"
											style="cursor:pointer"></a>
									</div>
								</div>
							</div></li>
						<div class="page-header clearfix"
							style="margin:10px 0px 10px 0px;padding: 0px;"></div>
							<c:if test="${s.last}">
								<div class="clearfix" style="margin-bottom: 70px;"></div>
							</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<%--菜单结束 --%>
		
		<div class="hidden-xs col-md-3 col-md-push-1" id="restaurant" style="margin-top: 14px;">
			<div class="border4Circle">
				<table class="table">
					<thead>
						<tr>
							<th>商家信息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span>${shopById.rtcontent}</span></td>
						</tr>
						<tr>
							<td><span>地址 : ${shopById.rtaddr}</span></td>
						</tr>
						<tr>
							<td><span>营业时间 : ${shopById.rtonbuz}</span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="clearfix visible-xs" style="margin-bottom: 70px;"></div>
		</div>
		<%--店铺信息结束 --%>

		<div class="hidden-xs col-md-3 col-md-push-1" id="bean">
			<h3 class="hidden-xs" style="padding-top: 20px;">最新评论</h3>
			<table style="margin-top: 14px;" class="table">
				<tbody>
					<c:choose>
						<c:when test="${message.totalCounts != 0}">
							<c:forEach items="${message.beanList}" var="item" varStatus="s">
								<c:if test="${s.last}">
									<tr style="border-bottom: 1px #f1f1f1 solid;">
								</c:if>
								<c:if test="${not s.last}">
									<tr>
								</c:if>
								<td>
									<div class="media">
										<div class="media-left">
											<img class="img40 img-circle" src="<%=path%>/${item.photo}" />
										</div>
										<div class="media-body">
											<h6 style="font-size: 12px;margin: 0px;padding-left: 10px;">
												${item.username}<small class="pull-right">${item.mtime}</small>
											</h6>
											<p
												style="padding-left: 10px;margin-bottom: 0px;margin-top: 4px;">
												<a><span>${item.mcontent}</span> </a>
											</p>
											<c:choose>
												<c:when test="${item.mscore == 5}">
													<label class="label label-success pull-left"
														style="margin: 5px 0px 10px 10px;">好评</label>
												</c:when>
												<c:when test="${item.mscore == 4}">
													<label class="label label-primary pull-left"
														style="margin: 5px 0px 10px 10px;">好评</label>
												</c:when>
												<c:when test="${item.mscore == 3}">
													<label class="label label-default pull-left"
														style="margin: 5px 0px 10px 10px;">中评</label>
												</c:when>
												<c:when test="${item.mscore == 2}">
													<label class="label label-warning pull-left"
														style="margin: 5px 0px 10px 10px;">差评</label>
												</c:when>
												<c:when test="${item.mscore == 1}">
													<label class="label label-danger pull-left"
														style="margin: 5px 0px 10px 10px;">差评</label>
												</c:when>
											</c:choose>
										</div>
									</div></td>
								</tr>
							</c:forEach>
							<tr>
								<td align="center">
									<%--页码条:两个字，完美！ --%> <c:choose>
										<c:when
											test="${not empty message.totalPages and message.totalPages != 0}">
											<c:choose>
												<c:when test="${message.totalPages > 7}">
													<c:set var="begin" value="${message.currentPage - 3}" />
													<c:set var="end" value="${message.currentPage + 3}" />
												</c:when>
												<c:otherwise>
													<c:set var="begin" value="1" />
													<c:set var="end" value="${message.totalPages}" />
												</c:otherwise>
											</c:choose>
											<c:if test="${begin < 1}">
												<c:set var="begin" value="1" />
												<c:set var="end" value="7" />
											</c:if>
											<c:if test="${end > message.totalPages}">
												<c:set var="begin" value="${message.totalPages - 6}" />
												<c:set var="end" value="${message.totalPages}" />
											</c:if>
											<div class="portfolio-pagination">
												<ul class="pagination">
													<li><a
														href="shop!MenuList.action?rtid=${rtid}&cp=1&index=1"><span>首页</span>
													</a>
													</li>
													<%--设置上一页是否被激活 --%>
													<c:choose>
														<c:when test="${message.currentPage > 1}">
															<li><a
																href="shop!MenuList.action?rtid=${rtid}&cp=${message.currentPage-1}&index=1"><span
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
															<c:when test="${message.currentPage == i}">
																<li class="disabled"><a href="javascript:;"><span>${i}</span>
																</a></li>
															</c:when>
															<c:otherwise>
																<li><a
																	href="shop!MenuList.action?rtid=${rtid}&cp=${i}&index=1"><span>${i}</span>
																</a></li>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													<%--设置下一页是否被激活 --%>
													<c:choose>
														<c:when
															test="${message.currentPage != message.totalPages}">
															<li><a
																href="shop!MenuList.action?rtid=${rtid}&cp=${message.currentPage+1}&index=1"><span
																	class="glyphicon glyphicon-chevron-right"></span> </a></li>
														</c:when>
														<c:otherwise>
															<li class="disabled"><a href="javascript:;"><span
																	class="glyphicon glyphicon-chevron-right"></span> </a></li>
														</c:otherwise>
													</c:choose>
													<li><a
														href="shop!MenuList.action?rtid=${rtid}&cp=${message.totalPages}&index=1"><span>尾页</span>
													</a>
													</li>
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
									</c:choose> <%--/页码条 --%></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>暂时没有评价</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="clearfix visible-xs" style="margin-bottom: 70px;"></div>
		</div>
		<%--评价结束 --%>
		
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
					<li><a href="shop!ShopList.action"><span
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
					</a>
					</li>
				</ul>
			</div>
		</div>
		<%--商品详细列表 --%>
		<div id="cartbar" class="navbar-collapse collapse sr-only">
			<ul id="cartList" class="nav navbar-nav navbar-left">

			</ul>
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
