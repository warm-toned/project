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

		<title>菜单</title>

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
	padding: 1px
}

;
h4 {
	margin: 0px;
	padding: 1px
}

;
h3 {
	margin: 0px;
	padding: 1px
}
;
</style>

	</head>

	<body>
		<table>
			<tr>
				<td width="25%" align="center">
					<img src="image/${shopById.rtpic}" width="67" height="60"
						border="0" />
				</td>
				<td colspan="2">
					<h2>
						${shopById.rtname}
					</h2>
					<br>
					<h5>
						${shopById.rtaddr}
					</h5>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<h5>
						公告：${shopById.rtcontent}
					</h5>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table>
						<c:forEach items="${MenuType}" var="t">
							<tr>
								<td>
									${t.mutype}
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td>
					<table>
						<c:forEach items="${MenuList}" var="m">
							<tr>
								<td width="25%" align="center">
									<a href="#"><img src="image/${m.mupic}" width="143"
											height="112" border="0" />
									</a>
								</td>
								<td>
									<h2>
										${m.muname}
									</h2>
									<h4>
										${m.mudesc}
									</h4>
									<h3 style="color: red">
										￥${m.muprice}
									</h3>
									&nbsp;&nbsp;
								
									<c:if test="${m.ocount>=1}">
									<a href="shwx!deletes.action?sid=${shopById.rtid}&meuid=${m.omuid}"><input type="button" value="-" /></a>
									</c:if>									
									<c:if test="${m.ocount!=0}">
									  <span style="color:red">&nbsp;${m.ocount }&nbsp;</span>
									</c:if>
									<a href="shwx!addCar.action?shopid=${shopById.rtid }&menuid=${m.muid}" ><input type="button" value="+"/></a>
								</td>
							</tr>
						</c:forEach>
					</table>
                    
				</td>
			</tr>
			<tr>
			   <td colspan="3">
			      <table>
			          <tr>
			            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            <a href="shwx!searchCar.action?sid=${shopById.rtid}" style="text-decoration: none">查看我的订单</a>
			            </td>
			            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            <span style="color:red;font-size:22px"><a href="shwx!getDetail.action?sid=${shopById.rtid}" style="text-decoration: none">去结算</a></span>
			            </td>
			          </tr>
			      </table>
			   </td>
			</tr>
		</table>

	</body>
</html>
