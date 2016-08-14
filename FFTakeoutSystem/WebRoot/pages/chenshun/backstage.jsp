<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'backstage.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/themes/icon.css">
			<script type="text/javascript" src="<%=path%>/js/ChenShun/jquery-1.5.2.js"></script>
			<script type="text/javascript" src="<%=path%>/js/ChenShun/echarts.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

	
		
		
		<script type="text/javascript" src="<%=path%>/js/ChenShun/script.js"></script>
		<style type="text/css">
span{
	font-size: 15px;
	color: blue;
	span: hover {       text-decoration :       underline;
	color: red;
	cursor: pointer;
}




</style>
	</head>
	<body class="easyui-layout" style="overflow-y:hidden"  scroll="no" onload="ts_menu()">
     <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
      background:  #87cefa repeat-x center 50%;
        line-height: 20px;color: #red; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎:${user.username} <a href="#" id="return">返回主页面</a>  <a href="javascript:void(0);" onclick="AlterPassword()">修改密码</a> <a href="#" onclick="exit()">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" />后台管理</span>
    </div>
		<div region="west" split="true" title="后台管理" style="width: 200px;">
			<div id="aa" class="easyui-accordion"
				style="width: 300px; height: 200px;">

				<!-- <div title="用户管理" iconCls="icon-save"
					style="overflow: auto; padding: 10px; margin: auto; width: 300px; height: 50px;">

					<ul style="list-style-type: none">
						<li >
							<span onclick='ts_user()'>[我的资料]</span>
						</li>

					</ul>
				</div> -->


				<div title="菜单管理" iconCls="icon-reload" selected="true"
					style="padding: 10px; width: 300px; height: 50px;" onclick="caidanguanli()">
					<ul style="list-style-type: none">
						<li >
							<span onclick='ts_menu()' onmouseover="this.style.textDecoration='underline'; this.style.cursor='hand' ;this.style.color='red' " onmouseout="this.style.textDecoration='none' ;this.style.color='blue'" >[我的菜单]</span>
		
						</li>
		
					</ul>
				</div>


				<div title="订单管理" iconCls="icon-reload" selected="true"
					style="padding: 10px; width: 300px; height: 50px;" onclick="caidanguanli()">
					<ul style="list-style-type: none">
						<li>
							<span onclick='ts_order()' onmouseover="this.style.textDecoration='underline' ; this.style.cursor='hand'  ;this.style.color='red'" onmouseout="this.style.textDecoration='none' ;this.style.color='blue'" >[全部订单]</span>
							
						</li>
						<li>
						   <span onclick='ts_order_no()' onmouseover="this.style.textDecoration='underline' ; this.style.cursor='hand';this.style.color='red'" onmouseout="this.style.textDecoration='none' ;this.style.color='blue'" >[未处理订单]</span>
						</li>
						
						<li>
						   <span onclick='ts_order_yes()' onmouseover="this.style.textDecoration='underline' ; this.style.cursor='hand' ;this.style.color='red' " onmouseout="this.style.textDecoration='none' ;this.style.color='blue'" >[待配送订单]</span>
						</li>
						
					</ul>
				</div>





			</div>




		</div>


		<div region="center" id="cen" title="主窗体"
			style="padding: 5px; background: #eee;">
			<!--      条件部分  -->
			<div id="searchDiv" class="easyui-panel" title="查询"
				collapsible="true" collapsed=true iconCls="icon-search">
			</div>
         
			<table id="myTable"></table> 
			
			
			<div id="mydiv" class="easyui-dialog" closable=true closed=true title="添加" iconCls="icon-add" style="width:400px; height:500px;padding-top:40px;>
         <s:form id="myfrm"  action=""  method="post" enctype="multipart/form-data">
				<Table align="center" width="100%" style="padding-left: 50px">
				<tr>
					  <td>菜单名称:</td>
						<Td>
				           <input type="text" name="muname"  class="easyui-validatebox" required=true" />
						</Td>
					</Tr>
					<Tr>
						<Td>菜单价格</Td>
						<Td>
							<input type="text" name="muprice" class="easyui-numberbox" required=true />
						</Td>
					</Tr>
					<Tr>
						<Td>				  
                     
                             <img  src="" name="image" width="50" height="50">
				             <s:file name="u.abc" label="上传文件"  accept="image/*" />
      	
						</Td>
					</Tr>
					
					<Tr>
						<Td>菜单类型</Td>
						<Td>
						<select name="mutype" class="easyui-validatebox" required=true style="width: 174px">
						        <option value="湘菜">湘菜</option>
						           <option value="川菜">川菜</option>
						              <option value="粤菜">粤菜</option>
						                <option value="鲁菜馆">鲁菜馆</option>
						                   <option value="徽菜馆">徽菜馆</option>
						     </select>   
						</Td>
					</Tr>
						<Tr>
						<Td>菜单状态</Td>
						<Td>
						<select name="mustatus" class="easyui-validatebox" required=true style="width: 174px">
						        <option value="0">销售中</option>
						           <option value="1">已下架</option>
						    
						     </select>   
						</Td>
					</Tr>
					
					
					<Tr>
						<Td>描述</Td>
						<Td>
						    <textarea rows="7" cols="22" name="mudesc"></textarea>  
						</Td>
					</Tr>
					<Tr>
						<Td colspan="3">
							&nbsp;&nbsp;<a class="easyui-linkbutton" id="addBtn" iconCls="icon-ok" title="保存"/>保存</a>
							&nbsp;&nbsp;<a class="easyui-linkbutton" id="quxiao" iconCls="icon-cancel" title="取消">取消</a>
						</Td>
					</Tr>
					 <tr><td><input type="hidden" name="muid"></td></tr>
				</Table>
		  </s:form>
   </div>
			
			
          
		</div>
		
			<!-- 订单详情 -------------------------------- -->	
		<div id="myorder" class="easyui-dialog" closable=true closed=true title="详情" iconCls="icon-tip" style="width:400px;height:500px;margin:50px;background: silver;overflow: auto;">
		       <!--   <table align="center" width="60%">
		          <th>列表</th><th>数量</th><th>金额</th>
		           -------------------------------------
		           <tr align="center">
		             
		           </tr>
		           <a class="easyui-linkbutton" id="guanbi" iconCls="icon-cancel" title="关闭">关闭</a>
		         </table> -->	
		</div>
		
		<!-- 修改密码 -->
		<div id="alterpassword" class="easyui-dialog" closable=true closed=true title="详情" iconCls="icon-tip" style="width:280px;height:120px;margin:0px; ">
		             <table align="center">
		             <tr>
		              <td style="font-size:12px; color:#9ED7E8">请输入新密码：<input type="password" name="password"/></td>
		             </tr>
                        <tr></tr>
		             <tr>
		              <td style="font-size:12px; color:#9ED7E8">&nbsp;确认新密码：<input type="password" name="passwordx"/></td>
		             </tr>
		           </table>          
		             <br/>
		     &nbsp;&nbsp;<a class="easyui-linkbutton" id="updateBtn" iconCls="icon-ok" title="确定修改">确定修改</a>
							&nbsp;&nbsp;<a class="easyui-linkbutton" id="quxiaoxiugai" iconCls="icon-cancel" title="取消">取消</a>
		</div>
		
		
	

		

	</body>

</html>
