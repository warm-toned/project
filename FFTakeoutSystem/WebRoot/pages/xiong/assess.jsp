<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'assess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="js/xiongli/xing.js"></script>
  </head>
  <body>
    <table align="center" width="60%">
       <tr>
          <td><input type="hidden" value="${mapshop.rtid }" name="rtid"/></td>
       </tr>
        <tr>
            <td align="center" colspan="2"><img src="${mapshop.rtpic }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${mapshop.rtname }</td>
        </tr>
        <tr>
            <td align="center"><h5>为商家服务打分</h5></td>
        </tr>
        <tr>
           <td align="center" >
           <c:forEach begin="1" end="5" var="i">
           	<span id="${i}" style="cursor: pointer;" onclick="assess(${i})"><font size="4" color="#ccc">★</font></span> 
           </c:forEach>                                        
           </td>
         </tr>
         <tr>
         <td align="center">
         <textarea rows="3" cols="28" hidden="true" id="shopAssess" >
            
         </textarea>
         </tr>
         <c:forEach items="${mapshop.menuList}" var="m" varStatus="i">
             <tr>
                <td align="center">${m.muname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.ocount }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="点评" onclick="meu(${i.index})"/></td>
             </tr>
             <tr>
               <td align="center">
                     
                     <div name="${i.index }" style="display:none">
                      <c:forEach begin="1" end="5" var="j">
                   	   <span  style="cursor: pointer;" onclick="menuassess(${j})" ><font size="4" color="#ccc">★</font></span> 
                      </c:forEach> </div><br/>
                      <textarea rows="2" cols="28" hidden="true"  name="${i.index }">
                         ${i.index}
                      </textarea>
                   
               </td>
             </tr>
         </c:forEach>
    </table>
  </body>
</html>
