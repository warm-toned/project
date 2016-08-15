<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'jump.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
</head>
<body>
	<a id="infos" uuid="${uuid}" rtid="${rtid}"></a>
	<form id="frm" action="" method="post"></form>
</body>
<script type="text/javascript">
	$(function() {
		var uuid = $("#infos").attr("uuid");
		var rtid = $("#infos").attr("rtid");
		window.location.href = "order!payPage.action?uuid=" + uuid
				+ "&ti=15.0&rtid=" + rtid;
	});
</script>
</html>
