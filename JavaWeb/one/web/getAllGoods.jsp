<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>getAllGood</title>
</head>
<body>

<h1>服饰管理</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="UserServlet?action=showAllUsers">用户管理</a>
<a href="GoodsServlet?action=showAllGoods">服饰管理</a>
<a href="IndentServlet?action=showAllIndents">订单管理</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${empty goodsList}">
    <h2>当前系统中没有服饰信息</h2>
</c:if>
<c:if test="${!empty goodsList}">
    <table border="1">
        <tr>
            <th>商品ID</th>
            <th>商品名称</th>
            <th>价格</th>
            <th>剩余数目</th>
            <th>所属分类</th>
            <th>图片</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${goodsList}" var="item">
            <form >
                <tr>
                    <td>${item.goodsId}</td>
                    <td>${item.goodsName}</td>
                    <td>${item.price}</td>
                    <td>${item.leave_amount}</td>
                    <td>${item.goodsClass.classname}</td>
                    <td><img src="${item.img}"></td>
                    <td><a href="goodsUpdate.jsp?goodsId=${item.goodsId}">修改</a>
                        <a href="GoodsServlet?action=delGoods&goodsId=${item.goodsId}">删除</a>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
