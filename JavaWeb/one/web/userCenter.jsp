<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GoodCenter</title>
</head>
<body>
<h1>商品中心</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="userCenter.jsp">商品中心</a>
<a href="userCart.jsp">查看购物车</a>
<a href="IndentServlet?action=showAllUserIndents&userid=${sessionScope.userid}">我的订单</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${!empty goodsList}">
    <table border="1">
        <tr>
            <th>商品名称</th>
            <th>价格</th>
            <th>剩余数目</th>
            <th>图片</th>
            <th>所属分类</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${goodsList}" var="item">
            <form >
                <tr>
                    <td>${item.goodsName}</td>
                    <td>${item.price}</td>
                    <td>${item.leave_amount}</td>
                    <td><img src="${item.img}"></td>
                    <td>${item.goodsClass.classname}</td>
                    <td>
                        <a href="GoodsServlet?action=addToCart&goodsId=${item.goodsId}&saleAmount=1">加入购物车</a>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
