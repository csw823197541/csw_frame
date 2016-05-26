<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <a href="">立即注册</a>
    <a href="/api/goUserInfo">框架页面</a>
    <a href="/api/goBootStrapUI">bootStrap测试页面</a>
    <form action="/api/staffs" method="post">
        <input id="name" name="name" value="张三"/><br/>
        <input id="password" name="password" value="123456"/><br/>
        <input type="submit" value="提交"/>
    </form>
  </body>
</html>
