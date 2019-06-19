
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userUpdate</title>
</head>
<body>
<h2>用户修改</h2>
<form method="post" action="UserServlet?action=updateUser&userId=${param.userId}">
    用户名
    <input type="text"  name="username"/> <br>

    密码：
    <input type="password" name="password"/> <br>

    <input type="submit"/>
</form>
</body>
</html>
