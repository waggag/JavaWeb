package cn.waggag.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/8 15:43
 */
@WebServlet("/sessionDemo3")
public class SessionDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取Session
        HttpSession session = req.getSession();
        //存储数据
       System.out.println(session);
       session.invalidate();

        Cookie c = new Cookie("JSESSIONID",session.getId());
        c.setMaxAge(60*60);
        resp.addCookie(c);
    }
}
