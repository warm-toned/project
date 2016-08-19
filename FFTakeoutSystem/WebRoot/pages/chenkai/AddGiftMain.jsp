<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>订餐系统后台模板管理系统</title>

	<link type="text/css" rel="stylesheet" href="<%=path %>/css/style.css" />
	<script type="text/javascript" src="<%=path %>/js/chenkai/jquery-1.5.2.js"></script>
	<script type="text/javascript" src="<%=path %>/js/chenkai/menu.js"></script>
	<script type="text/javascript" src="<%=path %>/js/chenkai/Manager.js"></script>
	<style type="text/css">
		a{text-decoration:underline;cursor:pointer} 
		.tdtype{text-decoration:none;cursor:pointer} 
	</style>
	
  </head>
  <body>
<div class="top"></div>
<div id="header">
	<div class="logo">订餐后台管理系统</div>
	<div class="navigation">
		<ul>
		 	<li>欢迎您！</li>
			<li><span>${sessionScope.user.username}</span></li>
			<li><input type="hidden" id="id" name="id" value="${sessionScope.user.userid}"> </li>
			
			<li><a href='user!logout.action'>退出</a></li>
		</ul>
	</div>
</div>
<div id="content">
	<div class="left_menu">
				<ul id="nav_dot">
      <li>
          <h4 class="M1"><span></span>用户管理</h4>
          <div class="list-item none">
            <a id="findalluser">查询所有用户</a>
            <a href='<%= path %>/pages/chenkai/AddUserMain.jsp'>新增用户</a>
            <a href='shwk!WriteUser.action'>下载所有用户信息</a>
          </div>
        </li>
        <li>
          <h4 class="M2"><span></span>店家管理</h4>
          <div class="list-item none">
            <a id="findallrest">查询所有店家</a>
            <a href='#'>查看开店申请</a>
            <a href='shwkrest!WriteRest.action'>下载所有店家信息</a>   
            <a id='findaddMess'>查询所有店家评论</a>   
           </div>
        </li>
        <li>
          <h4 class="M3"><span></span>菜单管理</h4>
          <div class="list-item none">
            <a id="findallmenu">查询所有菜单</a>
            <a href='shwkmenu!WriteMenu.action'>下载所有菜单</a>   
            <a id="findallmenumsg">查询所有菜单评论</a>
          </div>
        </li>
				<li>
          <h4 class="M4"><span></span>订单管理</h4>
          <div class="list-item none">
            <a id='findallorder'>查询所有订单</a>
             <a href='shwkorder!WriteOrder.action'>下载所有订单</a>
          </div>
        </li>
				<li>
          <h4 class="M5"><span></span>支付管理</h4>
          <div class="list-item none">
            <a id='findallpay'>查询所有订单支付方式</a>
        
          </div>
        </li>
				<li>
          <h4  class="M6"><span></span>礼品管理</h4>
          <div class="list-item none">
            <a id='findallgift'>查询所有礼品</a>
            <a href='shwkgift!WriteGift.action'>下载所有礼品</a>
            <a href="<%= path %>/pages/chenkai/AddGiftMain.jsp">添加礼品</a>
            <a id='findallgiftRec'>查询所有礼品兑换记录</a>
            <a href='shwkgiftrec!WriteGiftRec.action'>下载所有礼品兑换记录</a>
          </div>
        </li>
  </ul>
		</div>
		<div class="m-right">
			<div class="right-nav">					
			</div>
			<div id="mydiv" class="main">
				<s:form  action='shwkgift!addGift.action'  method='post' enctype='multipart/form-data'>
						<table align='center'>
					    <tr><td>礼品名：<input type='text' id='gname' name='gname'onblur='mygname()'/>&nbsp;&nbsp;<span id='mgname'></span></td></tr>
						<tr><td>图片：<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>
						<tr><td>所需积分:<input type='text' id='greqscore' name='greqscore' onblur='mygreqscore()'/>&nbsp;&nbsp;<span id='mgreqscore'></span></td></tr>
						<tr><td>总库存数:<input type='text' id='gsum' name='gsum' onblur='mygsum()'/>&nbsp;&nbsp;<span id='mgsum'></span></td></tr>
						<tr><td>描述：<textarea rows='3' cols='20' id='gdesc' name='gdesc'></textarea></td></tr>
						<tr><td><s:token theme='simple'/><s:actionerror theme='simple'/></td></tr>
						<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='submit' value='提交' id='submit'/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type='reset' value='取消'/></td></tr></table>
				</s:form>
			</div>
		</div>
</div>
<div class="bottom"></div>
<div id="footer"><p>Copyright©  2016 版权所有 京ICP备08082016号-110  </p></div>
<script>navList(12);</script>
</body>

</html>
