<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dataview.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
     <script type="text/javascript" src="<%=path %>/js/ChenShun/jquery-1.5.2.js"></script>
        <script type="text/javascript" src="<%=path %>/js/ChenShun/echarts.min.js"></script>
          <script type="text/javascript" src="<%=path %>/js/ChenShun/script.js"></script>
  </head>
  
  <body onload='dataview()'>
   	<div id="mydiv" style="width:1000px;height: 600px"></div>
   		<!-- <div id="zhuzhaung" style="width:1000px;height: 600px"></div> -->
  </body>
</html>
