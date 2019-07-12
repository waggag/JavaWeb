package cn.waggag.web.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.FileInputStream;

@WebListener()
public class ListenerDemo1 implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    /**
     * ����ServletContext���󴴽��ġ�ServletContext����������������Զ�������
     *
     * �ڷ������������Զ�����
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //������Դ�ļ�
        //1.��ȡServletContext����
        ServletContext servletContext = servletContextEvent.getServletContext();

        //2.������Դ�ļ�
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        //3.��ȡ��ʵ·��
        String realPath = servletContext.getRealPath(contextConfigLocation);

        //4.���ؽ��ڴ�
        try{
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ServletContext���󱻴����ˡ�����");
    }

    /**
     * �ڷ������رպ�ServletContext�������١��������������رպ�÷���������
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext���������ˡ�����");
    }
}
