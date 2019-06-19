package dao;

import dao.GoodsDaoImpl;
import db.DBConnectionManager;
import entity.Goods;
import entity.IndentList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class IndentListDaoImpl  {

    //根据UserId获得订单列表 id // 显示最近一次订单
    public Integer getIndentId(int userid) {
        Integer indentId = null;
        // 显示最近一次订单
        String sqlStr = "select max(id) from indent where userid = " + userid;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            if(rs.next()) {
                indentId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indentId;
    }

    //根据 订单id获取 商品信息
    public List<Goods> getIndentListInfo(int indentId) {
        GoodsDaoImpl goodDao = new GoodsDaoImpl();
        List<Goods> goodsList = new ArrayList<Goods>();

        // 从订单列表中选择
        String sqlStr = "select * from indentlist where indentId = " + indentId;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);

            while (rs.next()) {
                int goodId =  rs.getInt("goodId");
                // 获得商品信息
                Goods goods = goodDao.queryGoodsInfo(goodId);
                goodsList.add(goods);
                // 获得购买数目
                int amount = rs.getInt("amount");
                goods.setSaleNum(amount);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    // 订单id获取删除 订单列表
    public boolean delIndentList(int indentId) {
        boolean flag = false;

        String sqlStr = "delete from indentlist where indentId = '" + indentId + "'";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    // 添加订单
    public boolean addIndentList(int userid, List<IndentList> nowbuyList) {
        Integer indentId = this.getIndentId(userid);
        if(indentId != null) {
            for(IndentList iList : nowbuyList) {
                String sqlStr = "insert into indentlist(indentId,goodId,amount) values (?,?,?)";
                try {
                    // 获取数据库连接
                    // 获得PreparedStatement
                    PreparedStatement prepstmt;

                    prepstmt = DBConnectionManager.getConnection().prepareStatement(sqlStr);
                    prepstmt.setInt(1,indentId);
                    prepstmt.setInt(2,iList.getGoodsId());
                    prepstmt.setInt(3,iList.getAmount());
                    prepstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

}
