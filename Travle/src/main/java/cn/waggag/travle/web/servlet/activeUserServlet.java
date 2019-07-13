package cn.waggag.travle.web.servlet;

import cn.waggag.travle.domain.User;
import cn.waggag.travle.service.UserService;
import cn.waggag.travle.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class activeUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码参数
        String code = request.getParameter("code");
        //根据激活码查询用户
        if(code != null){
            //2.调用service完成激活
            UserService userService = new UserServiceImpl();
            Boolean result = userService.active(code);
            //3.判断标记
            String msg = null;
            if(result){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                msg = "激活失败，请联系管理员,邮箱为waggag@outlook,com";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
