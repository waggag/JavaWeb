package cn.waggag.web.Servlet;

import cn.waggag.service.UserService;
import cn.waggag.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //���ñ���
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        //2.����Serviceɾ��
        UserService service = new UserServiceImpl();
        service.deleteUser(id);
        //3.��ת��ѯ����Servlet
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
