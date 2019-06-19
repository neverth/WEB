<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>userCart</title>
</head>
<body>
<h1>购物车</h1>
<hr>
<a href="index.jsp">首页</a>
<a href="userCenter.jsp">用户中心</a>
<a href="userCart.jsp">查看购物车</a>
<a href="IndentServlet?action=showAllUserIndents&userid=${sessionScope.userid}">我的订单</a>
<a href="UserServlet?action=loginOut">注销</a>
<hr>
<c:if test="${empty shopcart}">
    <h2>购物车为空</h2>
</c:if>
<c:if test="${!empty shopcart}">
    <table border="1">
        <tr>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>商品类目</th>
            <th>图片</th>
            <th>购买数目</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${shopcart}" var="item">
            <form action="GoodsServlet">

                <tr>
                    <td>${item.goods.goodsName}</td>
                    <td>${item.goods.price}</td>
                    <td>${item.goods.goodsClass.classname}</td>
                    <td><img src="${item.goods.img}" alt=""></td>
                    <td>${item.amount}</td>
                    <td>
                        <input type="text" name="saleAmount">
                        <input type="hidden" name="goodsId" value="${item.goodsId}">
                    </td>
                    <td>
                        <input type="submit" name="action" value="updateCart">
                        <input type="submit" name="action" value="delCart">
                    </td>

                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
<hr>
<c:if test="${!empty shopcart}">
    <a href="IndentServlet?action=payout">结算</a>
</c:if>

</body>
</html>
