package cn.waggag.web.Servlet;

import cn.waggag.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.��ȡ���е�uid
        String[] uids = request.getParameterValues("uid");
        //2.����serviceɾ��
        UserServiceImpl userService = new UserServiceImpl();
        userService.deleteUsers(uids);
        //3.��ת��ѯ����Servlet
        response.sendRedirect(request.getContextPath()+"/userListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
