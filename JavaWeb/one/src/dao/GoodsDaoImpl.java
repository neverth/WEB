package dao;

import db.DBConnectionManager;
import entity.Goods;
import entity.GoodsClass;
import entity.IndentList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GoodsDaoImpl {

    // 根据商品id从数据库获取商品信息
    public Goods queryGoodsInfo(int goodsId) {

        //定义 goodbean
        Goods goods = new Goods();

        int goodClassId = 0;

        // 从商品表获取信息
        String sqlStr = " select * from goods where id=" + goodsId;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            // 结果集游标遍历结果集
            while (rs.next()) {
                // 结果集中获取对应信息
                String goodsName = rs.getString("goodname");
                float price = rs.getFloat("price");
                int amount = rs.getInt("amount");
                int leave_amount = rs.getInt("leave_amount");
                String img = rs.getString("img");
                goodClassId = rs.getInt("goodsClassId");
                goods.setGoodsName(goodsName);
                goods.setPrice(price);
                goods.setAmount(amount);
                goods.setLeave_amount(leave_amount);
                goods.setImg(img);
                // 上面是获取基本信息，下面开始获取 商品类别信息
            }
            rs.close();
            // 从商品类获取信息
            String sqlStr1 = " select * from goodsclass where id=" + goodClassId;
            // 执行sql获得结果集
            rs = stmt.executeQuery(sqlStr1);
            // 结果集游标遍历结果集
            while(rs.next()) {
                String goodClassname = rs.getString(2);

                goods.setGoodsClass(new GoodsClass(goodClassId, goodClassname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    // 获得所有商品信息
    public List<Goods> queryAllGoods() {

        List<Goods> goodlist = new ArrayList<Goods>();

        // 这是联合sql查询语句
        String sqlStr = "select goods.id,goodName,price,img," +
                "goodsClassId,classname," +
                "amount,leave_amount from goods, goodsclass " +
                "where goods.goodsClassId = goodsclass.id;";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();

            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);

            while (rs.next()) {
                // 结果集中获取对应信息
                int goodsId = rs.getInt(1);
                String goodsName = rs.getString(2);
                float price = rs.getFloat(3);
                String img = rs.getString(4);
                int amount = rs.getInt(7);
                int leave_amount = rs.getInt(8);
                int classId = rs.getInt(5);
                String className = rs.getString(6);

                Goods goods = new Goods();
                goods.setGoodsId(goodsId);
                goods.setGoodsName(goodsName);
                goods.setPrice(price);
                goods.setImg(img);
                goods.setAmount(amount);
                goods.setLeave_amount(leave_amount);
                goods.setGoodsClass(new GoodsClass(classId, className));
                // 把单个商品加入商品列表中
                goodlist.add(goods);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodlist;
    }

    // 添加商品
    public boolean addGoods(Goods newGoods) {

        boolean flag = false;
        // 定义预处理sql语句
        String sqlStr = "insert into goods(goodName,price,img," +
                "goodsClassId,amount," +
                "leave_amount) values(?,?,?,?,?,?)";
        try {
            // 获取数据库连接
            // 获得PreparedStatement
            PreparedStatement prepstmt;

            prepstmt = DBConnectionManager.getConnection().prepareStatement(sqlStr);
            prepstmt.setString(1, newGoods.getGoodsName());
            prepstmt.setFloat(2, newGoods.getPrice());
            prepstmt.setString(3, newGoods.getImg());
            prepstmt.setInt(4, newGoods.getGoodsClass().getGoodClassId());
            prepstmt.setInt(5, newGoods.getAmount());
            prepstmt.setInt(6, newGoods.getLeave_amount());
            // 执行查询
            flag = prepstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 获得所有商品类别信息
    public List<GoodsClass> queryAllGoodsClass() {

        List<GoodsClass> goodsClassList = new ArrayList<GoodsClass>();

        // 定义sql语句
        String sqlStr = "select  id, classname from goodsclass;";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();

            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);

            while (rs.next()) {
                int goodsClassId = rs.getInt(1);
                String goodsClassName = rs.getString(2);
                GoodsClass goodsClass = new GoodsClass(goodsClassId, goodsClassName);
                goodsClassList.add(goodsClass);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsClassList;
    }

    // 删除商品
    public boolean delGoods(int goodsId) {
        boolean flag = false;
        String sqlStr = "delete from goods where id = '" + goodsId + "'";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();

            // 执行sql ture or false
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 更新商品
    public boolean updateGoods(Goods goods) {
        boolean flag = false;

        String sqlStr = "update goods set goodName='" + goods.getGoodsName() + "'" +
                ",price='" + goods.getPrice() + "'" +
                ",leave_amount='" + goods.getLeave_amount() + "'" +
                 "where id = '" + goods.getGoodsId() + "'" ;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();

            // 执行sql ture or false
            flag =   stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 根据商品 id 获得商品剩余数目
    public int getLeaveAmount(int goodsId) {
        // 商品剩余数目
        int leaveAmount = 0;

        String sqlStr = "select leave_amount from goods where id =  " + goodsId;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);

            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                leaveAmount = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaveAmount;
    }

    /*注意别忘了了要修改good表中库存量的值*/
    public boolean updateLeaveAmount(List<IndentList> nowbuyList ) {
        for(IndentList  iList : nowbuyList) {
            String sqlStr = "update goods set leave_amount=leave_amount-" + iList.getAmount() + " where id=" + iList.getGoodsId();
            try {
                // 获取数据库连接
                // 获得Statement
                Statement stmt = DBConnectionManager.getConnection().createStatement();
                stmt.executeUpdate(sqlStr);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
