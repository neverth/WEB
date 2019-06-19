<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>userIndent</title>
</head>
<body>

<h1>我的订单</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="userCenter.jsp">用户中心</a>
<a href="userCart.jsp">查看购物车</a>
<a href="IndentServlet?action=showAllUserIndents&userid=${sessionScope.userid}">我的订单</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${empty userGoodsList}">
    <h2>订单为空</h2>
</c:if>
<c:if test="${!empty userGoodsList}">
    <table border="1">
        <tr>
            <th>商品名称</th>
            <th>价格</th>
            <th>类别</th>
            <th>商品图片</th>
        </tr>
        <c:forEach items="${userGoodsList}" var="item">
            <form >
                <tr>
                    <td>${item.goodsName}</td>
                    <td>${item.price}</td>
                    <td>${item.goodsClass.classname}</td>
                    <td><img src="${item.img}"></td>
                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
最近一次订单
</body>
</html>
