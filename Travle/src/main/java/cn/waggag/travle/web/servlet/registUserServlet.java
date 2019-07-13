package cn.waggag.travle.web.servlet;

import cn.waggag.travle.domain.ResultInfo;
import cn.waggag.travle.domain.User;
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

@WebServlet("/registUserServlet")
public class registUserServlet extends HttpServlet {

    //序列化对象
    private ObjectMapper mapper = new ObjectMapper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        //从sessiion中获取验证码
        String checkcode_server = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");//保证验证码使用一次
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check) ) {
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
                UserServiceImpl userService = new UserServiceImpl();
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
