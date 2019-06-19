
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>abUserLogin</title>
</head>
<%
    if(session.getAttribute("loginState") == "0") {
        out.println("<script>");
        out.println("alert('请退出用户登录登录');");
        out.println("window.location.href='index.jsp';");
        out.println("</script>");
    }
    if(session.getAttribute("loginState") == "1") {
        out.println("<script>");
        out.println("alert('您已经登录');");
        out.println("window.location.href='adManage.jsp';");
        out.println("</script>");
    }
%>
<body>
<h1>管理员登录</h1>
<hr>
<a href="index.jsp">首页</a>
<hr>
<form method="post" action="UserServlet?action=adLogin">
    用户名
    <input type="text"  name="username"/> <br>

    密码：
    <input type="text" name="password"/> <br>

    <input type="submit"/>
</form>
</body>
</html>
