package cn.waggag.travle.web.servlet;

import cn.waggag.travle.domain.ResultInfo;
import cn.waggag.travle.domain.User;
import cn.waggag.travle.service.UserService;
import cn.waggag.travle.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private ObjectMapper mapper = new ObjectMapper();
    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            //1.获取参数
            Map<String, String[]> map = request.getParameterMap();
            //2.封装对象
            user = new User();
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用Service查询
        User login_user = userService.login(user);
        ResultInfo info = new ResultInfo();
        //4.判断用户对象是否为null
        if (login_user == null) {
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }
        //5.判断用户是否激活
        if (login_user != null && "N".equals(login_user.getStatus())) {
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        //6.判断登录成功
        if (login_user != null && "Y".equals(login_user.getStatus())) {
            //设置登录标识
            request.getSession().setAttribute("user", login_user);
            //登录成功
            info.setFlag(true);
        }
        //响应数据
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        //从sessiion中获取验证码
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");//保证验证码使用一次
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码没有通过
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误，请重新输入！");
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        } else {
            try {
                //1.获取数据
                Map<String, String[]> map = request.getParameterMap();
                //2.封装对象
                User user = new User();
                BeanUtils.populate(user, map);
                //3.调用service完成注册
                Boolean flag = userService.regist(user);
                //4.响应结果
                ResultInfo resultInfo = new ResultInfo();
                if (flag) {
                    //注册成功
                    resultInfo.setFlag(flag);
                } else {
                    //注册失败
                    resultInfo.setFlag(flag);
                    resultInfo.setErrorMsg("注册失败,用户名可能重复！");
                }
                String json = mapper.writeValueAsString(resultInfo);
                //设置content-type
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        //将User写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(user);
        response.getWriter().write(json);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁Session
        request.getSession().invalidate();
        //重定向到login.html
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码参数
        String code = request.getParameter("code");
        //根据激活码查询用户
        if(code != null){
            //2.调用service完成激活
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

