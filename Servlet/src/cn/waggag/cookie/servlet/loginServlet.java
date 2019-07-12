package cn.waggag.cookie.servlet;

import cn.waggag.cookie.dao.UserDao;
import cn.waggag.cookie.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
        Map<String, String[]> map = req.getParameterMap();
        //3.1创建一个User对象
        User user = new User();
        //3.2使用BeanUtils封装数据
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }


        User login = new UserDao().login(user);
        if(login != null) {
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        }else{
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        }
    }
}
