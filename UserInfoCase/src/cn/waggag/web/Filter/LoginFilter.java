package cn.waggag.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆验证的Filter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取资源请求路径
        String uri = request.getRequestURI();
        //3.判断是否包含登录相关的路径
        if(uri.contains("/login.jsp") || uri.contains("/LoginServlet")){
            chain.doFilter(req,resp);
        }else{
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if(user != null){
                chain.doFilter(req,resp);
            }else {
                request.setAttribute("login_msg","你未登录，请登录再访问！");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
        req.setCharacterEncoding("utf-8");

        chain.doFilter(req, resp);
    }


    public void destroy() {

    }

}
