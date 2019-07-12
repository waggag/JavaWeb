package cn.waggag.web.Servlet;

import cn.waggag.domain.PageBean;
import cn.waggag.domain.User;
import cn.waggag.service.UserService;
import cn.waggag.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页记录数
        String rows = request.getParameter("rows");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //获取条件查询参数
        Map<String, String[]> map = request.getParameterMap();

        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(currentPage, rows, map);
        //2将查询对象存入request
        request.setAttribute("page",pageBean);
        request.setAttribute("condition",map);
        //3.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
