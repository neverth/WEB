package dao;

import db.DBConnectionManager;
import entity.ShopUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl {

    // 登录验证
    public boolean login(String username, String password, int type) {
        String sqlStr = "";
        if(type == 1){ // type 为 1 时 代表是管理员登录，使用管理员的sql语句
             sqlStr = "select * from ad_users where username = ? and password = ?";
        }
        else{// type 为 2 时 代表是用户登录，使用用户的sql语句
             sqlStr = "select * from users where username = ? and password = ?";
        }
        try {
            // 获取数据库连接
            // 获得PreparedStatement
            PreparedStatement prepstmt;

            prepstmt = DBConnectionManager.getConnection().prepareStatement(sqlStr);
            prepstmt.setString(1, username);
            prepstmt.setString(2, password);
            ResultSet rs = prepstmt.executeQuery();
            if (rs.next()) { // 结果集中有数据
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查找所有的用户
    public List<ShopUser> queryAllUsers() {
        List<ShopUser> userList = new ArrayList<ShopUser>();

        String sqlStr = "select * from users;";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                ShopUser user = new ShopUser();
                user.setUserid(userId);
                user.setUsername(userName);
                user.setPassword(password);

                // 加入列表中
                userList.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // 删除的用户
    public boolean delUser(int userId) {
        boolean flag = false;
        String sqlStr = "delete from users where id = '" + userId + "'";
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

    // 修改的用户
    public boolean updateUser(ShopUser user) {
        boolean flag = false;

        String sqlStr = "update users set username='" + user.getUsername() + "'" +
                ",password='" + user.getPassword() + "'" +
                "where id = '" + user.getUserid() + "'";
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

    // 根据用户id查找用户信息
    public ShopUser queryUserInfo(int userId) {
        ShopUser user = new ShopUser();

        String sqlStr = "select * from users where id=" + userId;
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);

                user = new ShopUser();
                user.setUserid(userId);
                user.setUsername(userName);
                user.setPassword(password);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    // 插入
    public boolean insertUser(ShopUser user) {
        boolean flag = false;

        String sqlStr = "INSERT INTO users (username, password) VALUES ('" +
                user.getUsername() + "', '" +
                user.getPassword() + "')";
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

    // 根据用户姓名找到用户id
    public Integer getUserid(String username) {
        Integer userid = null ;  //没有找到，则返回null；
        String sqlStr = "select id from users where username='"+ username +"'";
        try {
            // 获取数据库连接
            // 获得Statement
            Statement stmt = DBConnectionManager.getConnection().createStatement();
            // 执行sql获得结果集
            ResultSet rs = stmt.executeQuery(sqlStr);
            while(rs.next()) {
                userid = rs.getInt(1);
            }
            rs.close();
            return userid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }


}
