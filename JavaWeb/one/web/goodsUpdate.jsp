
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改</title>
</head>
<body>
<h2>商品修改</h2>
<form method="post" action="GoodsServlet?action=updateGoods&goodsId=${param.goodsId}">
    商品名称
    <input type="text"  name="goodsName"/> <br>
    价格：
    <input type="text" name="goodsPrice"/> <br>
    剩余数目
    <input type="text" name="leave_amount"/> <br>
    所属分类
    <input type="text" name="goodsClassId"/> <br>

    <input type="submit"/>
</form>
</body>
</html>
