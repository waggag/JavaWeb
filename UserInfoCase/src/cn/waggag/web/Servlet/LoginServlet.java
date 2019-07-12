package cn.waggag.web.Servlet;

import cn.waggag.domain.User;
import cn.waggag.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/9 9:37
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.���ñ���
        req.setCharacterEncoding("UTF-8");
        //2.У����֤��
        //2.1ҳ���ȡ����֤��
        String verifycode = req.getParameter("verifycode");
        //2.2���ɵ���֤��
        String checkCode = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");
        if(!checkCode.equalsIgnoreCase(verifycode)){
            //������ʾ��Ϣ
            req.setAttribute("msg","��֤�����");
            //��ת����½����
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //3.��װUser����
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.����LoginService
        UserServiceImpl userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        //5.�ж��Ƿ��¼
        if(loginUser != null){
            req.getSession().setAttribute("user",loginUser);
            //��½�ɹ�
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            //������ʾ��Ϣ
            req.setAttribute("msg","�û������������");
            //��ת����½����
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

    }
}
