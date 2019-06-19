package servlet;


import dao.GoodsDaoImpl;
import entity.Goods;
import entity.GoodsClass;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
//        if (action.equals("showAllGoods")){showAllGoods(req, resp);}
//
//        else if (action.equals("addGoods")){addGoods(req, resp);}
//
//        else if (action.equals("delGoods")){delGoods(req, resp);}
//
//        else if (action.equals("updateGoods")){updateGoods(req, resp);}
//
//        else if (action.equals("addToCart")){addToCart(req, resp);}
//
//        else if (action.equals("showAllCart")){showAllCart(req, resp);}
//
//        else if (action.equals("updateCart")){updateCart(req, resp);}
//
//        else if (action.equals("delCart")){delCart(req, resp);}

        switch (action) {
            // 显示所有商品，返回用户首页
            case "showAllGoods": {
                showAllGoods(req, resp);
            }
            break;
            // 添加商品
            case "addGoods": {
                addGoods(req, resp);
            }
            break;
            // 删除商品
            case "delGoods": {
                delGoods(req, resp);
            }
            break;
            // 更新商品
            case "updateGoods": {
                updateGoods(req, resp);
            }
            break;
            // 加入购物车
            case "addToCart": {
                addToCart(req, resp);
            }
            break;
            // 显示购物车商品
            case "showAllCart": {
                showAllCart(req, resp);
            }
            break;
            // 更新购物车
            case "updateCart": {
                updateCart(req, resp);
            }
            break;
            // 删除购物车
            case "delCart": {
                delCart(req, resp);
            }
            break;
        }
    }

    private void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        GoodsDaoImpl goodsDao = new GoodsDaoImpl();

        int goodsId = Integer.parseInt(filter(req.getParameter("goodsId")));
        String goodsName = filter(req.getParameter("goodsName"));
        float goodsPrice = Float.parseFloat(filter(req.getParameter("goodsPrice")));
        int leave_amount = Integer.parseInt(filter(req.getParameter("leave_amount")));
        Goods oldGoods = new Goods();
        oldGoods.setGoodsId(goodsId);
        oldGoods.setGoodsName(goodsName);
        oldGoods.setPrice(goodsPrice);
        oldGoods.setLeave_amount(leave_amount);
        if(goodsDao.updateGoods(oldGoods)) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('修改成功');");
            printWriter.println("window.location.href='adManage.jsp';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('修改成功');");
            printWriter.println("window.location.href='adManage.jsp';");
            printWriter.println("</script>");
            printWriter.close();
        }
        printWriter.close();
    }

    private void delGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        GoodsDaoImpl goodsDao = new GoodsDaoImpl();

        int goodsId = Integer.parseInt(filter(req.getParameter("goodsId")));
        if(goodsDao.delGoods(goodsId)) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除成功');");
            printWriter.println("window.location.href='GoodsServlet?action=showAllGoods';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('删除失败');");
            printWriter.println("window.location.href='GoodsServlet?action=showAllGoods';");
            printWriter.println("</script>");
            printWriter.close();
        }

    }

    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        GoodsDaoImpl goodsDao = new GoodsDaoImpl();

        String goodsName = filter(req.getParameter("goodsName"));
        float goodsPrice = Float.parseFloat(filter(req.getParameter("goodsPrice")));
        String goodsImg = filter(req.getParameter("goodsImg"));
        int goodsClassId = Integer.parseInt(filter(req.getParameter("goodsClassId")));
        int amount = Integer.parseInt(filter(req.getParameter("amount")));
        int leave_amount = Integer.parseInt(filter(req.getParameter("leave_amount")));
        String className = filter(req.getParameter("className"));
        Goods newGoods = new Goods();
        newGoods.setGoodsName(goodsName);
        newGoods.setPrice(goodsPrice);
        newGoods.setImg(goodsImg);
        newGoods.setAmount(amount);
        newGoods.setLeave_amount(leave_amount);
        newGoods.setGoodsClass(new GoodsClass(goodsClassId, className));

        if(goodsDao.addGoods(newGoods)) {
            printWriter.print("增加商品成功");
        } else {
            printWriter.print("增加商品失败");
        }
        printWriter.close();
    }

    public void showAllGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        GoodsDaoImpl goodsDao = new GoodsDaoImpl();


        List<GoodsClass> goodsClassList = goodsDao.queryAllGoodsClass();
        if (goodsClassList != null) {
            req.getSession().setAttribute("goodsClassList", goodsClassList);
            List<Goods> goodsList = goodsDao.queryAllGoods();
            if (goodsList != null && goodsList.size() != 0) {
                req.getSession().setAttribute("goodsList", goodsList);
            }
        } else {
            req.getSession().setAttribute("goodsList", null);
        }
        RequestDispatcher rd = req.getRequestDispatcher("getAllGoods.jsp");
        rd.forward(req, resp);

    }

    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        GoodsDaoImpl goodsDao = new GoodsDaoImpl();

        HttpSession session = req.getSession();
        if (session.getAttribute("userid") == null) {
            printWriter.flush();
            printWriter.println("<script>");
            printWriter.println("alert('您还没有登录！');");
            printWriter.println("window.location.href='index.jsp';");
            printWriter.println("</script>");
            printWriter.close();
        } else {
            /*将商品加入购物车（设置session中一属性，作为记录购物车信息的载体）*/
            int goodsId = Integer.parseInt(req.getParameter("goodsId"));
            int saleAmount = Integer.parseInt(req.getParameter("saleAmount"));

            /*剩余的商品数量是否能满足用户的需求*/
            int leaveAmount = goodsDao.getLeaveAmount(goodsId);
            if (saleAmount > leaveAmount) {
                printWriter.flush();
                printWriter.println("<script>");
                printWriter.println("alert('库存不足！');");
                printWriter.println("window.location.href='userCenter.jsp';");
                printWriter.println("</script>");
                printWriter.close();
            }

            /*保存购物车中信息*/
            List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");
            IndentList indentList = new IndentList();

            indentList.setGoodsId(goodsId);

            indentList.setAmount(saleAmount);
            /*判断是否是已经将该商品加入过购物车，是则添加，否则只是数量增加*/
            boolean flag = false; /*是否购买过该商品*/
            if (purchaseList == null) {    // 购物车为空
                purchaseList = new LinkedList<IndentList>();
                purchaseList.add(indentList);
            }
            else {
                for (int i = 0; i < purchaseList.size(); i++) {
                    IndentList iList = purchaseList.get(i);

                    //加入过，购物车中已有该商品，则找到并增加其数量
                    if (iList.getGoodsId() == indentList.getGoodsId()) {
                        iList.setAmount(iList.getAmount() + indentList.getAmount());
                        purchaseList.set(i, iList);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {   //购物车中还没有该商品
                    purchaseList.add(indentList);
                }
            }
            for (int i = 0; i < purchaseList.size(); i++) {
                IndentList iList = purchaseList.get(i);
                // 把商品信息添加进去
                iList.setGoods(goodsDao.queryGoodsInfo(iList.getGoodsId()));

            }
            /*将结果更新到session中*/
            session.setAttribute("shopcart", purchaseList);

        }
        // 加入完成
        printWriter.flush();
        printWriter.println("<script>");
        printWriter.println("alert('成功加入购物车！');");
        printWriter.println("window.location.href='userCenter.jsp';");
        printWriter.println("</script>");
        printWriter.close();
    }

    // 显示 购物车 返回 userCart.jsp
    public void showAllCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession(false);

        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");

        req.getSession().setAttribute("shopcart", purchaseList);

        RequestDispatcher rd = req.getRequestDispatcher("userCart.jsp");

        rd.forward(req, resp);
    }

    public void updateCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession(false);
        /*保存购物车中信息*/
        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");

        String goodsIds = req.getParameter("goodsId");

        String saleAmountList = req.getParameter("saleAmount");
        for(IndentList purchase : purchaseList){
            if(purchase.getGoodsId() == Integer.parseInt(goodsIds)){
                purchase.setAmount(Integer.parseInt(saleAmountList));
            }
        }
        req.getSession().setAttribute("shopcart", purchaseList);

        RequestDispatcher rd = req.getRequestDispatcher("userCart.jsp");
        rd.forward(req, resp);

    }

    public void delCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession(false);
        /*保存购物车中信息*/
        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");

        List<IndentList> newpurchaseList = new ArrayList<>();

        String goodsIds = req.getParameter("goodsId");

        for(IndentList purchase : purchaseList){
            if(purchase.getGoodsId() == Integer.parseInt(goodsIds)){
                continue;
            }
            newpurchaseList.add(purchase);
        }
        req.getSession().setAttribute("shopcart", newpurchaseList);

        RequestDispatcher rd = req.getRequestDispatcher("userCart.jsp");
        rd.forward(req, resp);
    }

    //处理页面乱码
    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }
}

