package cn.waggag.cookie.servlet;

import cn.waggag.cookie.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downLoadServlet")
public class DownLoadeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String filename = req.getParameter("filename");
        //2.使用字节流加载进内存
        //2.1找到文件的服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);

        System.out.println(realPath);
        //2.2用字节流关联
        FileInputStream inputStream = new FileInputStream(realPath);
        //3.设置response的响应头
        //3.1设置响应头类型content-type
        resp.setHeader("content-type",servletContext.getMimeType(filename));
        //3.2设置响应头打开方式content-disposition，和文件名

        //解决中文文件名问题
        //1.获取user-agent请求头
        String agent = req.getHeader("user-agent");
        //2.使用工具类编码文件名
        filename = DownLoadUtils.getFileName(agent, filename);

        resp.setHeader("content-disposition","attachment;filename="+filename);
        //4.将输入流的数据写到输出流中
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = inputStream.read(buff)) != 0){
            outputStream.write(buff,0,len);
        }
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
