<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>

<h1>用户登录</h1>
<hr>
<a href="index.jsp">首页</a>
<hr>
<form method="post" action="UserServlet?action=insertUser">
    用户名
    <input type="text"  name="username"/> <br>

    密码：
    <input type="text" name="password"/> <br>

    <input type="submit"/>

</form>
</body>
</html>
