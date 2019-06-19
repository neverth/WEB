package servlet;

import dao.GoodsDaoImpl;
import dao.UserDaoImpl;
import entity.Goods;
import entity.GoodsClass;
import entity.ShopUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("adLogin")){login(req, resp, 1);}

        else if (action.equals("login")){login(req, resp, 0);}
        else if (action.equals("loginOut")){loginOut(req, resp, 0);}
        else if (action.equals("showAllUsers")){showAllUsers(req, resp);}
        else if (action.equals("delUser")){delUser(req, resp);}
        else if (action.equals("updateUser")){updateUser(req, resp);}
        else if (action.equals("insertUser")){insertUser(req, resp);}

        switch (action) {
            case "adLogin": {
                // 判断管理员密码是否正确
                login(req, resp, 1);
            }
            break;
            case "login": {
                // 判断用户密码是否正确
                login(req, resp, 0);
            }
            break;
            case "loginOut": {
                // 退出登录
                loginOut(req, resp, 0);
            }
            break;
            case "showAllUsers": {
                // 显示所有用户
                showAllUsers(req, resp);
            }
            break;
            case "delUser": {
                // 删除用户
                delUser(req, resp);
            }
            break;
            case "updateUser": {
                // 更新
                updateUser(req, resp);
            }
            break;
            case "insertUser": {
                // 插入用户
                insertUser(req, resp);
            }
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp, int type) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter printWriter = resp.getWriter();

        // 获得dao类
        UserDaoImpl userDao = new UserDaoImpl();
        // 获得属性
        String username = filter(req.getParameter("username"));
        String password = filter(req.getParameter("password"));
        boolean sign = true;
        HttpSession session = req.getSession();
        if (type == 0) { // 判断是用户登录 type为 0
            if (userDao.login(username, password, 0)) {
                sign = true;
                // session中loginState为0 代表用户
                session.setAttribute("loginState", "0");
            } else {
                sign = false;
            }
        } else {
            if (userDao.login(username, password, 1)) {
                sign = true;
                // session中loginState为1 代表管理员
                session.setAttribute("loginState", "1");
            } else {
                sign = false;
            }
        }
        // 验证通过
        if (sign) {// 验证通过

            // 获得dao类
            GoodsDaoImpl goodsDao = new GoodsDaoImpl();

            List<GoodsClass> goodsClassList = goodsDao.queryAllGoodsClass();
            // 创建商品类别 session
            req.getSession().setAttribute("goodsClassList", goodsClassList);

            List<Goods> goodsList = goodsDao.queryAllGoods();

            // 创建商品 session
            if (goodsList != null && goodsList.size() != 0) {
                req.getSession().setAttribute("goodsList", goodsList);
            }


            Integer userid = new UserDaoImpl().getUserid(username);
            // 创建用户id  name session
            session.setAttribute("userid", userid);
            session.setAttribute("username", username);

            // 显示提示窗口
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('登录成功');");// 根据type不同返回不同的 jsp页面
            printWriter.println("window.location.href='" + (type == 0 ? "userCenter.jsp" : "adManage.jsp") + "';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('密码错误');");
            printWriter.println("window.location.href='index.jsp';");
            printWriter.println("</script>");
            printWriter.close();
        }
    }
    private void loginOut(HttpServletRequest req, HttpServletResponse resp, int type) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession(false);

        // 删除session
        session.setAttribute("userid", null);
        session.setAttribute("username", null);
        session.setAttribute("loginState", null);
        printWriter.flush();
        printWriter.println("<script>");
        printWriter.println("alert('注销成功');");
        printWriter.println("window.location.href='index.jsp';");
        printWriter.println("</script>");
        printWriter.close();
    }



    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        UserDaoImpl userDao = new UserDaoImpl();
        // filter 防止乱码
        int userId = Integer.parseInt(filter(req.getParameter("userId")));
        String userName = filter(req.getParameter("username"));
        String password = filter(req.getParameter("password"));

        ShopUser user = new ShopUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.setUserid(userId);

        if (userDao.updateUser(user)) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('修改成功');");
            printWriter.println("window.location.href='UserServlet?action=showAllUsers';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('修改成功');");
            printWriter.println("window.location.href='UserServlet?action=showAllUsers';");
            printWriter.println("</script>");
            printWriter.close();
        }
        printWriter.close();
    }

    private void delUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        // 获得dao类
        UserDaoImpl userDao = new UserDaoImpl();

        int userId = Integer.parseInt(filter(req.getParameter("userId")));

        if (userDao.delUser(userId)) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除成功');");
            printWriter.println("window.location.href='UserServlet?action=showAllUsers';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除失败');");
            printWriter.println("window.location.href='UserServlet?action=showAllUsers';");
            printWriter.println("</script>");
            printWriter.close();
        }
    }

    private void showAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        // 获得dao类
        UserDaoImpl userDao = new UserDaoImpl();

        List<ShopUser> userList = userDao.queryAllUsers();
        if (userList != null && userList.size() != 0) {
            req.getSession().setAttribute("userList", userList);
        } else {
            req.getSession().setAttribute("userList", null);
        }
        //请求转发到 getAllUser.jsp
        RequestDispatcher rd = req.getRequestDispatcher("getAllUser.jsp");
        rd.forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        // 获得dao类
        UserDaoImpl userDao = new UserDaoImpl();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ShopUser user = new ShopUser();
        user.setUsername(username);
        user.setPassword(password);

        printWriter.flush();
        printWriter.println("<script>");
        if (userDao.insertUser(user)) {
            printWriter.println("alert('注册成功');");
            printWriter.println("window.location.href='index.jsp';");
            printWriter.println("</script>");
        } else {
            printWriter.println("alert('注册失败');");
            printWriter.println("window.location.href='index.jsp';");
            printWriter.println("</script>");
        }

    }

    //处理页面乱码
    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }

}
