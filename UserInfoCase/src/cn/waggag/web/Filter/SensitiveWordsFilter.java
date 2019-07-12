package cn.waggag.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * ���дʻ������
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    //������дʻ��list
    private List<String> list = new ArrayList<String>();

    public void init(FilterConfig config) throws ServletException {
        try{
            //1.��ȡ�ļ���ʵ·��
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/���дʻ�.txt");
            //2.��ȡ�ļ�
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.���ļ���ÿһ��������ӵ�list��
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }
            br.close();
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.�����������,��ǿgetParamenter����
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //1.��ǿgetParamenter����
                //�ж��Ƿ���getParameter����
                if(method.getName().equals("getParameter")){
                    //��ǿ����ֵ,��ȡ����ֵ
                    String value = (String) method.invoke(req,args);
                    if(value != null){
                        for (String str : list) {
                            if(value.contains(str)){
                                value = value.replaceAll(str,"**");
                            }
                        }
                    }
                    return  value;
                }

                //�жϷ������Ƿ��� getParameterMap

                //�жϷ������Ƿ��� getParameterValue

                return method.invoke(req,args);
            }
        });
        //2.����
        chain.doFilter(proxy_request, resp);
    }


    public void destroy() {

    }

}
