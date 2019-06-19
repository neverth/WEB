package servlet;

import dao.GoodsDaoImpl;
import dao.IndentDaoImpl;
import dao.IndentListDaoImpl;
import dao.UserDaoImpl;
import entity.Goods;
import entity.Indent;
import entity.IndentList;
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


public class IndentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("showAllIndents")){showAllIndents(req, resp);}

        else if (action.equals("showAllUserIndents")){showAllUserIndents(req, resp);}
        else if (action.equals("delIndent")){delIndent(req, resp);}
        else if (action.equals("payout")){payout(req, resp);}

        switch (action) {
            // 显示所有订单
            case "showAllIndents": {
                showAllIndents(req, resp);
            }
            break;
            // 显示所有用户订单
            case "showAllUserIndents": {
                showAllUserIndents(req, resp);
            }
            break;
            // 删除订单
            case "delIndent": {
                delIndent(req, resp);
            }
            break;
            // 支付，等价于生成订单
            case "payout": {
                payout(req, resp);
            }
            break;
        }
    }



    private void delIndent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        int indentId = Integer.parseInt(filter(req.getParameter("indentId")));

        IndentListDaoImpl indentListDao = new IndentListDaoImpl();
        IndentDaoImpl indentDao = new IndentDaoImpl();

        // 两个表中的都要删除
        if (indentListDao.delIndentList(indentId) && indentDao.delIndent(indentId)) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除成功');");
            printWriter.println("window.location.href='IndentServlet?action=showAllIndents';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除成功');");
            printWriter.println("window.location.href='IndentServlet?action=showAllIndents';");
            printWriter.println("</script>");
            printWriter.close();
        }
    }

    private void showAllIndents(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        IndentDaoImpl indentDao = new IndentDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        List<Indent> indents = indentDao.getAllIndents();
        for (int i = 0; i < indents.size(); i++) {
            Indent iList = indents.get(i);
            // 把商品信息添加进去
            int userid = indentDao.getIndentUserId(iList.getIndentId());
            iList.setUser(userDao.queryUserInfo(userid));

        }
        if (indents != null && indents.size() != 0) {
            req.getSession().setAttribute("indents", indents);
            RequestDispatcher rd = req.getRequestDispatcher("adIndentManage.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("adManage.jsp");
            rd.forward(req, resp);
        }

    }
    private void showAllUserIndents(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException  {
        resp.setContentType("text/html;charset=utf-8");
        int userid =Integer.parseInt(req.getParameter("userid"));
        IndentListDaoImpl iIndentListDao = new IndentListDaoImpl();
        int indentId = iIndentListDao.getIndentId(userid);
        List<Goods> goodsList = iIndentListDao.getIndentListInfo(indentId);
        if (goodsList != null && goodsList.size() != 0) {
            req.getSession().setAttribute("userGoodsList", goodsList);
            RequestDispatcher rd = req.getRequestDispatcher("userIndent.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("userCenter.jsp");
            rd.forward(req, resp);
        }
    }

    public void payout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();


        IndentListDaoImpl indentListDao = new IndentListDaoImpl();
        IndentDaoImpl indentDao = new IndentDaoImpl();
        GoodsDaoImpl goodsDao = new GoodsDaoImpl();

        HttpSession session = req.getSession(false);
        List<IndentList> nowbuyList = (List<IndentList>) session.getAttribute("shopcart");
        if (nowbuyList == null || nowbuyList.size() <= 0) {
            printWriter.print("Sorry,提交出错，请稍后重试");
        }
        int userid = (int) session.getAttribute("userid");   //用户id
        String indentNo = indentDao.getIndentNo(userid);   //订单号

        /*将数据添加到Indent表中*/
        if (indentDao.addIndent(indentNo, userid)) {
            /*将数据添加到IndentList表中*/
            if (indentListDao.addIndentList(userid, nowbuyList)) {
                /*注意别忘了了要修改good表中库存量的值*/
                if (goodsDao.updateLeaveAmount(nowbuyList)) {
                    /*清空立即购买商品的信息*/
                    session.setAttribute("shopcart", null);
                    printWriter.flush();
                    printWriter.println("<script>");
                    printWriter.println("alert('信息提交成功');");
                    printWriter.println("window.location.href='userCenter.jsp';");
                    printWriter.println("</script>");
                    printWriter.close();
                }
            }
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('提交失败，请稍后再试');");
            printWriter.println("window.location.href='GoodsServlet?action=showAllGoods';");
            printWriter.println("</script>");
            printWriter.close();
        }
        printWriter.close();
    }
    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }
}