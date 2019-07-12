package cn.waggag.cookie.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseDemo1")
public class Demo1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post...");

        //1.第一种方法重定向
        //设置状态码302
        //resp.setStatus(302);
        //设置响应头location
        //resp.setHeader("location","http://www.baidu.com");
        //动态获取虚拟目录
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath+"/responseDemo1");
        //2.第二种方法重定向
        resp.sendRedirect("http://www.taobao.com");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
