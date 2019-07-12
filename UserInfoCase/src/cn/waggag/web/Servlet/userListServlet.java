package cn.waggag.web.Servlet;

import cn.waggag.domain.User;
import cn.waggag.service.UserService;
import cn.waggag.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class userListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service完成查询
        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        //2.将用户存储到request域中
        request.setAttribute("users",users);
        //3.转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
