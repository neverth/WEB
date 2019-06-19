package dao;

import db.DBConnectionManager;
import entity.Indent;
import entity.ShopUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class IndentDaoImpl {
    /*生成订单编号，其中为了看起来更有意义，使其生成时包含了userid*/
    public String getIndentNo(int userid) {
        String indentNo = null;
        String sqlStr = "select max(id) from indent";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                indentNo = "HYD-" + userid + "-" + rs.getInt(1);
            } else {
                indentNo = "HYD" + userid + "-0";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indentNo;
    }

    // 根据用户id 查找 订单列表
    public List<Indent> getIndentInfo(int userid) {
        List<Indent> indents = new ArrayList<Indent>();
        // 从订单表
        String sqlStr = "select * from indent where userid = " + userid;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);

            while (rs.next()) {
                int indentId = rs.getInt("id");
                int userId = rs.getInt("userId");
                float totalPrice = rs.getFloat("totalPrice");
                Indent indent = new Indent();
                indent.setIndentId(indentId);
                indent.setTotalPrice(totalPrice);

                // 订单表中的 user
                ShopUser shopUser = new ShopUser();
                shopUser.setUserid(userId);
                indent.setUser(shopUser);


                indents.add(indent);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indents;
    }

    // 获得所有 订单
    public List<Indent> getAllIndents() {
        List<Indent> indents = new ArrayList<Indent>();
        //  联合查询, 因为要控制 用户id 和 订单里面的用户id想匹配
        String sqlStr = "select indent.id,totalPrice,users.id,users.username " +
                "from indent,users where indent.userId=users.id ";

        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int indentId = rs.getInt(1);
                Float totalPrice = rs.getFloat(2);
                int userId = rs.getInt(3);
                String username = rs.getString(4);
                Indent indent = new Indent();
                indent.setIndentId(indentId);
                indent.setTotalPrice(totalPrice);

                // 订单表中的 user
                ShopUser shopUser = new ShopUser();
                shopUser.setUserid(userId);
                shopUser.setUsername(username);
                indent.setUser(shopUser);

                indents.add(indent);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indents;
    }

    // 删除 订单
    public boolean delIndent(int indentId) {
        boolean flag = false;
        String sqlStr = "delete from indent where id = '" + indentId + "'";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 添加 订单
    public boolean addIndent(String indentNo, int userid) {
        String sqlStr = "insert into indent(indentNo,userId,totalPrice) values (?,?,?)";
        try {
            // 获取数据库连接
            // 获得PreparedStatement
            PreparedStatement prepstmt;

            prepstmt = DBConnectionManager.getConnection().prepareStatement(sqlStr);
            prepstmt.setString(1, indentNo);
            prepstmt.setInt(2, userid);
            prepstmt.setInt(3, 123);

            if (prepstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //根据indentId获得用户id
    public Integer getIndentUserId(int indentId) {
        // 显示最近一次订单
        String sqlStr = "select userid from indent where id = " + indentId;
        int userid = 0;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            if(rs.next()) {
                userid = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }
}
