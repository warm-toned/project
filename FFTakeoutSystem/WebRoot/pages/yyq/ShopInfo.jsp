<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>商店信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
h2 {
	margin: 0px;
	padding-top: 0px
}

;
h5 {
	padding: 1px;
	margin: 0px
}

;
br {
	padding: 1px;
	margin: 0px
}
;
</style>
</head>

<body>
	<h1 align="center">所以商店信息</h1>
	<table align="center">
		<c:forEach items="${shop.list}" var="s">
			<tr>
				<td width="25%" align="center"><a
					href="shop!MenuList.action?rtid=${s.rtid}"><img
						src="image/${s.rtpic}" width="143" height="112" border="0" /> </a>
				</td>
				<td><h2>
						<a href="shop!MenuList.action?rtid=${s.rtid}">${s.rtname}</a>
					</h2> <br>
					<h5>${s.rtaddr}</h5> <br>
					<h5>${s.rtcontent}</h5>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr>
				</td>
			</tr>
		</c:forEach>
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
