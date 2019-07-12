package cn.waggag.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ��½��֤��Filter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.ǿ��ת��
        HttpServletRequest request = (HttpServletRequest) req;
        //2.��ȡ��Դ����·��
        String uri = request.getRequestURI();
        //3.�ж��Ƿ������¼��ص�·��
        if(uri.contains("/login.jsp") || uri.contains("/LoginServlet")){
            chain.doFilter(req,resp);
        }else{
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if(user != null){
                chain.doFilter(req,resp);
            }else {
                request.setAttribute("login_msg","��δ��¼�����¼�ٷ��ʣ�");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
        req.setCharacterEncoding("utf-8");

        chain.doFilter(req, resp);
    }


    public void destroy() {

    }

}
