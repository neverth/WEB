<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>adIndentManage</title>
</head>
<body>

<h1>订单管理</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="UserServlet?action=showAllUsers">用户管理</a>
<a href="GoodsServlet?action=showAllGoods">服饰管理</a>
<a href="IndentServlet?action=showAllIndents">订单管理</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${empty indents}">
    <h2>当前系统中没有服饰信息</h2>
</c:if>
<c:if test="${!empty indents}">
    <table border="1">
        <tr>
            <th>订单ID</th>
            <th>用户姓名</th>
<%--            <th>总价</th>--%>
            <th>操作</th>
        </tr>
        <c:forEach items="${indents}" var="item">
            <form >
                <tr>
                    <td>${item.indentId}</td>
                    <td>${item.user.username}</td>
<%--                    <td>${item.totalPrice}</td>--%>
                    <td>
                        <a href="IndentServlet?action=delIndent&indentId=${item.indentId}">删除</a>
                    </td>
                </tr>

            </form>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
