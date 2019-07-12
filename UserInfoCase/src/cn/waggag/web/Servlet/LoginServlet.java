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
        //1.设置编码
        req.setCharacterEncoding("UTF-8");
        //2.校验验证码
        //2.1页面获取的验证码
        String verifycode = req.getParameter("verifycode");
        //2.2生成的验证码
        String checkCode = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");
        if(!checkCode.equalsIgnoreCase(verifycode)){
            //设置提示信息
            req.setAttribute("msg","验证码出错！");
            //跳转到登陆界面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //3.封装User对象
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.调用LoginService
        UserServiceImpl userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        //5.判断是否登录
        if(loginUser != null){
            req.getSession().setAttribute("user",loginUser);
            //登陆成功
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            //设置提示信息
            req.setAttribute("msg","用户名或密码出错！");
            //跳转到登陆界面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

    }
}
