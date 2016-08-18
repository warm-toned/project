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
<script type="text/javascript" src="<%=path%>/js/fileinput2.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/pain/user.js"></script>
</head>

<body>
	<a id="infos" class="sr-only" userid="${sessionScope.user.userid}"
		index="${param.index}"></a>
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
									<li><a href="user!gotoUserCenter.action">用户中心</a>
									</li>
									<li><a href="gift!getgiftList.action">积分商城</a></li>
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
	<%--主体内容 --%>
	<div class="container">
		<%--导航 --%>
		<ul class="nav nav-tabs">
			<li index="0" name="nav" class="active"><a href="#">我的订单</a>
			</li>
			<li index="1" name="nav"><a href="#">编辑个人信息</a>
			</li>
			<c:choose>
				<c:when test="${user.authority == 1}">
					<li index="2" name="nav"><a href="#">我要开店</a>
					</li>
				</c:when>
				<c:when test="${user.authority == 3}">
					<li index="2" name="nav"><a href="#">管理商店</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
		<div id="myorder" style="margin-top: 10px;">
			<c:if test="${not empty bean.beanList}">
				<div class="dropdown" style="font-size: 12px;">
					<button type="button"
						class="btn borderGray2 dropdown-toggle pull-right"
						data-toggle="dropdown">
						每页显示 ${bean.pageSize} 条订单 <span class="caret"></span>
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
						<li role="separator" class="divider"></li>
						<li><a href="#" class="pagereact" value="10"><span>重置</span>
						</a></li>
					</ul>
				</div>
			</c:if>
			<div class="clearfix"></div>
			<h6 style="padding: 0px;margin: 0px;">&nbsp;</h6>
			<c:forEach items="${bean.beanList}" var="item">
				<div class="border2Circle" style="margin-bottom: 20px;">
					<a
						href="/ffts/pages/xiong/orderDetails.jsp?uuid=${item.ouuid}&status=${item.ostatus}"
						style="font-size: 9px;margin-top: 0px;padding-left: 10px;display: block;margin-bottom: 5px;padding-bottom: 0px;"
						class="page-header">订单号 : ${item.ouuid} <span
						class="glyphicon glyphicon-chevron-right pull-right"
						style="width: 20px;margin-top: 3px;"></span> </a>
					<h6 class="clearfix" style="padding: 0px 0px 0px 10px;margin: 0px;">
						<c:choose>
							<c:when test="${item.ostatus == 1}">用户未支付
							<c:if test="${item.timestatus < 0}">
									<label class="label label-danger pull-right"
										style="margin-right: 10px;">已过期</label>
								</c:if>
							</c:when>
							<c:when test="${item.ostatus == 2}">等待商家接单<label
									class="label label-info pull-right" style="margin-right: 10px;">等待中</label>
							</c:when>
							<c:when test="${item.ostatus == 3}">商家已接单<label
									class="label label-info pull-right" style="margin-right: 10px;">等待中</label>
							</c:when>
							<c:when test="${item.ostatus == 4}">等待送达<label
									class="label label-info pull-right" style="margin-right: 10px;">等待中</label>
							</c:when>
							<c:when test="${item.ostatus == 5}">订单已完成<label
									class="label label-success pull-right"
									style="margin-right: 10px;">已完成</label>
							</c:when>
						</c:choose>
						<c:if test="${item.ostatus == 1 and item.timestatus == 0}">
							<small class="timer" deadline="${item.deadline}"></small>
						</c:if>
						<c:if test="${item.ostatus != 1}">
							<small>${item.orderdate }</small>
						</c:if>
					</h6>
					<div class="media" style="margin: 0px;padding:0px 10px;">
						<div class="media-left">
							<img src="<%=path%>/${item.rtpic}" class="img30 img-rounded" />
						</div>
						<div class="media-body">
							<h6 class="page-header"
								style="margin: 0px; padding: 0px 0px 5px 0px;">
								<a href="shop!MenuList.action?rtid=${item.ortid}"><span>${item.rtname}</span><span
									class="glyphicon glyphicon-chevron-right pull-right"></span> </a>
							</h6>
							<c:set value="0" var="count" scope="page" />
							<c:forEach items="${item.details}" var="olist" varStatus="s">
								<c:set var="count" value="${count + olist.ocount}" scope="page" />
								<c:if test="${s.count<=5}">
									<h6 style="margin: 0px;padding: 0px;">
										<small>${olist.muname}</small><small class="pull-right">×${olist.ocount}</small>
									</h6>
								</c:if>
								<c:if test="${s.last}">
									<c:if test="${s.count>5}">
										<h6 style="margin: 0px;padding: 0px;">
											<small>...</small>
										</h6>
									</c:if>
								</c:if>
							</c:forEach>
							<h6 style="margin: 0px;padding: 0px;">
								<span class="pull-right" style="margin-top: -2px;">￥${item.sum}</span><small
									class="pull-right">共${count}份，实付</small>
							</h6>
						</div>
					</div>
					<h6 class="page-header"
						style="margin: 0px;padding: 0px 0px 5px 0px;"></h6>
					<div class="clearfix">
						<c:choose>
							<c:when test="${item.ostatus == 1 && item.timestatus == -1}">
								<a class="btn borderGray pull-right" href="javascript:;">交易已关闭</a>
							</c:when>
							<c:when test="${item.ostatus == 1 && item.timestatus == 0}">
								<a class="btn borderGreen pull-right"
									href="order!payPage.action?uuid=${item.ouuid}&ti=15.0&rtid=${item.ortid}">点击支付</a>
							</c:when>
							<c:when test="${item.ostatus == 3 or item.ostatus == 4}">
								<a class="btn borderSky pull-right" href="">点此催单</a>
							</c:when>
							<c:when test="${item.ostatus == 5 and item.oassess == 0}">
								<a class="btn borderOrange pull-right"
									href="shwx!getAssess.action?uuid=${item.ouuid}&uscore=${item.sum * 10}">评价得<fmt:formatNumber
										value="${item.sum * 10}" pattern="0" />积分</a>
							</c:when>
						</c:choose>
						<c:if test="${item.ostatus == 5}">
							<a class="btn borderSky pull-right" href="">再来一单</a>
						</c:if>
					</div>
				</div>
			</c:forEach>
			<c:choose>
				<c:when test="${not empty bean.totalPages and bean.totalPages != 0}">
					<c:choose>
						<c:when test="${bean.totalPages > 5}">
							<c:set var="begin" value="${bean.currentPage - 2}" />
							<c:set var="end" value="${bean.currentPage + 2}" />
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="1" />
							<c:set var="end" value="${bean.totalPages}" />
						</c:otherwise>
					</c:choose>
					<c:if test="${begin < 1}">
						<c:set var="begin" value="1" />
						<c:set var="end" value="5" />
					</c:if>
					<c:if test="${end > bean.totalPages}">
						<c:set var="begin" value="${bean.totalPages - 4}" />
						<c:set var="end" value="${bean.totalPages}" />
					</c:if>
					<div class="portfolio-pagination">
						<ul class="pagination">
							<li><a id="first" size="${bean.pageSize}"
								href="user!gotoUserCenter.action?page=1&size=${bean.pageSize}"
								class="pageaction"><span>首页</span> </a>
							</li>
							<%--设置上一页是否被激活 --%>
							<c:choose>
								<c:when test="${bean.currentPage > 1}">
									<li><a
										href="user!gotoUserCenter.action?page=${bean.currentPage-1}&size=${bean.pageSize}"
										class="pageaction"><span
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
									<c:when test="${bean.currentPage == i}">
										<li class="disabled"><a href="javascript:;"><span>${i}</span>
										</a>
										</li>
									</c:when>
									<c:otherwise>
										<li><a
											href="user!gotoUserCenter.action?page=${i}&size=${bean.pageSize}"
											class="pageaction"><span>${i}</span> </a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<%--设置下一页是否被激活 --%>
							<c:choose>
								<c:when test="${bean.currentPage != bean.totalPages}">
									<li><a
										href="user!gotoUserCenter.action?page=${bean.currentPage+1}&size=${bean.pageSize}"
										class="pageaction"><span
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
								href="user!gotoUserCenter.action?page=${bean.totalPages}&size=${bean.pageSize}"
								class="pageaction"><span>尾页</span> </a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="portfolio-pagination">
						<ul class="pagination">
							<li>您还没有任何订单，快去下单吧...Orz</li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
			<%--/页码条 --%>
		</div>
		<%--我的订单结束 --%>
		<%--编辑我的信息 --%>
		<div id="myinfo" class="sr-only" style="margin-top: 10px;">
			<form id="photoFrm" action="photo!upload.action"
				class="form-horizontal" style="" method="post"
				enctype="multipart/form-data">
				<%--联系方式 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">联系电话:</label>
					<div class="col-xs-9">
						<div class="editInfos">
							<label style="line-height: 40px;font-size: 12px;" class="info">${user.tel}</label><a
								index="0" class="btn btn-info pull-right btnEdit">修改</a>
						</div>
						<div class="sr-only editBox" index="0">
							<div class="input-group">
								<input type="text" class="form-control infoBox"
									placeholder="联系电话"> <span class="input-group-btn">
									<button index="0" class="btn btn-info btnSave" type="button">保存</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<%--地址 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">家庭住址:</label>
					<div class="col-xs-9">
						<div class="editInfos">
							<label style="line-height: 40px;font-size: 12px;" class="info">${user.address}</label><a
								index="1" class="btn btn-info pull-right btnEdit">修改</a>
						</div>
						<div class="sr-only editBox" index="1">
							<div class="input-group">
								<input type="text" class="form-control infoBox"
									placeholder="家庭住址"> <span class="input-group-btn">
									<button index="1" class="btn btn-info btnSave" type="button">保存</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<%--邮箱 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">电子邮箱:</label>
					<div class="col-xs-9">
						<div class="editInfos">
							<label style="line-height: 40px;font-size: 12px;" class="info">${user.email}</label><a
								index="2" class="btn btn-info pull-right btnEdit">修改</a>
						</div>
						<div class="sr-only editBox" index="2">
							<div class="input-group">
								<input type="text" class="form-control infoBox"
									placeholder="电子邮箱"> <span class="input-group-btn">
									<button index="2" class="btn btn-info btnSave" type="button">保存</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<%--真实姓名 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">真实姓名:</label>
					<div class="col-xs-9">
						<div class="editInfos">
							<label style="line-height: 40px;font-size: 12px;" class="info">${user.realname}</label><a
								index="3" class="btn btn-info pull-right btnEdit">修改</a>
						</div>
						<div class="sr-only editBox" index="3">
							<div class="input-group">
								<input type="text" class="form-control infoBox"
									placeholder="真实姓名"> <span class="input-group-btn">
									<button index="3" class="btn btn-info btnSave" type="button">保存</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<%--性别 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">性别:</label>
					<div class="col-xs-9">
						<div class="btn-group" id="gender" gender="${user.gender}">
							<button id="male" class="btn" type="button">先生</button>
							<button id="female" class="btn" type="button">女士</button>
						</div>
					</div>
				</div>
				<%--头像 --%>
				<div class="form-group">
					<label for="" class="col-xs-3 control-label"
						style="line-height: 40px;text-align: right;font-size: 12px;">头像:</label>
					<div class="col-xs-9" id="photoBox">
						<div class="" id="photoreact">
							<a id="editPhoto" style="cursor: pointer;"><img
								src="<%=path%>/${user.photo}" class="img60 img-rounded" /> </a>
						</div>
						<div class="sr-only" id="photoaction">
							<input type="file" class="file" id="photoInfo" name="photo" />
						</div>
					</div>
				</div>
				<div id="abc"></div>
			</form>
			<div style="margin-bottom: 20px;"></div>
		</div>
		<%--编辑我的信息结束 --%>
		<%--我要开店/店铺管理 --%>
		<c:choose>
			<c:when test="${user.authority == 1 and not sessionScope.os}">
				<%--申请开店 --%>
				<div id="shopMgt" class="sr-only" style="margin-top: 10px;">
					<form class="form-horizontal" id="osFrm"
						action="os!setApply.action" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="userid"
							value="${sessionScope.user.userid}" />
						<%--联系方式 --%>
						<div class="form-group">
							<label for="" class="col-xs-3 control-label"
								style="line-height: 40px;text-align: right;font-size: 12px;">店名:</label>
							<div class="col-xs-9">
								<input type="text" class="form-control" name="rtname"
									placeholder="店名" required style="font-size: 12px;"/>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 control-label"
								style="line-height: 40px;text-align: right;font-size: 12px;">地址:</label>
							<div class="col-xs-9">
								<input type="text" class="form-control" name="rtaddr"
									placeholder="地址" required style="font-size: 12px;"/>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 control-label"
								style="line-height: 40px;text-align: right;font-size: 12px;">简述:</label>
							<div class="col-xs-9">
								<textarea rows="3" class="form-control" name="rtcontent"
									placeholder="简单描述一下您的店铺.." required style="font-size: 12px;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 control-label"
								style="line-height: 40px;text-align: right;font-size: 12px;">营业时间:</label>
							<div class="col-xs-9"></div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 control-label"
								style="line-height: 40px;text-align: right;font-size: 12px;">店铺图标:</label>
							<div class="col-xs-9">
								<input class="file" type="file" name="rtpic" required style="font-size: 12px;"/>
							</div>
						</div>
						<button type="submit" class="btn btn-success btn-block">提交申请</button>
					</form>
					<div style="margin-bottom: 20px;"></div>
				</div>
			</c:when>
			<c:when test="${sessionScope.os}">
				<div id="shopMgt" class="sr-only">
					<h3>正在审核中...</h3>
				</div>
			</c:when>
			<c:when test="${user.authority == 3}">
				<div id="shopMgt" class="sr-only"></div>
			</c:when>
		</c:choose>
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
					<button type="button" class="btn btn-info btn-block"
						data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var timers = $(".timer");
	var intvalues = new Array(timers.length);
	var endtimes = new Array(timers.length);
	for ( var i = 0; i < timers.length; i++) {
		endtimes[i] = Math.floor(($(timers[i]).attr("deadline") - new Date()
				.getTime()) / 1000);
		intvalues[i] = 1;
		window.setInterval("startShow(" + i + ")", 1000);
	}

	function startShow(i, EndTime, intvalue) {
		intvalues[i]++;
		$(".timer:eq(" + i + ")").text(
				Math.floor(((endtimes[i] - intvalues[i]) / 60)).toString()
						+ "分" + ((endtimes[i] - intvalues[i]) % 60).toString()
						+ "秒");
		if (intvalues[i] >= endtimes[i]) {
			$(".timer:eq(" + i + ")").text("订单失效");
		}
	}

	function endShow(timer) {
		window.clearTimeout(timer);
	}
</script>
</html>
