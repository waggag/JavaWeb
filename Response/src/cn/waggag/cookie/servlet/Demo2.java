package cn.waggag.cookie.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseDemo2")
public class Demo2 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post...");
        //获取流对象之前，设置流的默认编码ISO-8859-1 设置为GBK
        resp.setCharacterEncoding("UTF-8");
        //告诉浏览器，服务器发送消息的数据编码，建议浏览器使用该编码解码
        resp.setHeader("context-type","text/html;charset=utf-8");
        // 简单的形式设置编码
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.write("你好，heja");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
