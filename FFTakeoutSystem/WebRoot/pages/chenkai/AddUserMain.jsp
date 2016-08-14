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
            <a href='<%= basePath %>AddUserMain.jsp'>新增用户1111</a>
            <a id="addUser">新增用户</a>
            <a href='shwk!WriteUser.action'>下载所有用户信息</a>
          </div>
        </li>
        <li>
          <h4 class="M2"><span></span>店家管理</h4>
          <div class="list-item none">
            <a id="findallrest">查询所有店家</a>
            <a id='addRest'>新增店家</a>
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
            <a id='addgift'>添加礼品</a>
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
				<s:form action="shwk!addUser.action" method='post' enctype='multipart/form-data' >
    			<table align='center' >
    				<tr><td ><s:textfield name="username" label="姓名 "></s:textfield></td></tr>
    				<tr><td ><s:password name="pwd" label="密码"></s:password></td></tr>
    				<tr><td ><s:textfield name="tel" label="电话"></s:textfield></td></tr>
    				<tr><td ><s:textfield name="email" label="邮件 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="address" label="地址 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="realname" label="真实姓名 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="balance" label="余额"></s:textfield></td></tr>
    				<tr><td ><s:radio list="{'男','女'} "  value="'男'"
    					name="f.gender"
    					label="性别"
    				></s:radio></td></tr>
    				<tr><td ><s:select list="#{'1':'普通用户','2':'配送员','3':'店主','4':'普通管理员','5':'系统管理员'}"
    						listKey="key"
    					  	listValue="value"
    						name="authority"
    						label="权限"
    				></s:select></td></tr>			
    				<tr><td ><s:file id='photo' name='photo' label="图片"></s:file></td></tr>
    				<tr><td><s:token theme='simple'/><s:actionerror theme='simple'/></td></tr>
    				<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" theme="simple"></s:submit></td>
    				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:reset value="取消" theme="simple"></s:reset>	</td></tr>
    				</table>
    		</s:form>
			</div>
		</div>
</div>
<div class="bottom"></div>
<div id="footer"><p>Copyright©  2016 版权所有 京ICP备08082016号-110  </p></div>
<script>navList(12);</script>
</body>

</html>
