
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户登录</title>
</head>
<body>
<%
    if(session.getAttribute("loginState") == "1") {
        out.println("<script>");
        out.println("alert('请退出管理员登录');");
        out.println("window.location.href='index.jsp';");
        out.println("</script>");
    }
    if(session.getAttribute("loginState") == "0") {
        out.println("<script>");
        out.println("alert('您已经登录');");
        out.println("window.location.href='userCenter.jsp';");
        out.println("</script>");
    }
%>
<h1>用户登录</h1>
<hr>
<a href="index.jsp">首页</a>
<hr>
<form method="post" action="UserServlet?action=login">
    用户名
    <input type="text"  name="username"/> <br>

    密码：
    <input type="text" name="password"/> <br>

    <input type="submit"/>
</form>
</body>
</html>
