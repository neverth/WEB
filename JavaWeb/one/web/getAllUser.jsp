<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>getAllUser</title>
</head>
<body>
<h1>用户管理</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="UserServlet?action=showAllUsers">用户管理</a>
<a href="GoodsServlet?action=showAllGoods">服饰管理</a>
<a href="IndentServlet?action=showAllIndents">订单管理</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${empty userList}">
    <h2>系统中没有用户</h2>
</c:if>
<c:if test="${!empty userList}">
    <table border="1">
        <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userList}" var="item">
            <form >
                <tr>
                    <td>${item.userid}</td>
                    <td>${item.username}</td>
                    <td>${item.password}</td>
                    <td><a href="userUpdate.jsp?userId=${item.userid}">修改</a>
                        <a href="UserServlet?action=delUser&userId=${item.userid}">删除</a>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
