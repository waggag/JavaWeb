package cn.waggag.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: ��������ʹ��
 * @author: waggag
 * @time: 2019/7/11 0:15
 */
public class ProxyTest {

    public static void main(String[] args) {
        //1.������ʵ����
        Lenovo lenovo = new Lenovo();

        //2.��̬������ǿLenovo����
         /*
            ����������
                1. �����������ʵ����.getClass().getClassLoader()
                2. �ӿ����飺��ʵ����.getClass().getInterfaces()
                3. ��������new InvocationHandler()
         */
        SaleComputer saleComputer = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /*
               �����߼���д�ķ��������������õ����з������ᴥ���÷���ִ��
                   ������
                       1. proxy:�������
                       2. method�����������õķ���������װΪ�Ķ���
                       3. args:���������õķ���ʱ�����ݵ�ʵ�ʲ���
            */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("��������ǿ��");
                //ʹ�����Ƕ�����ø÷���
                Object obj = method.invoke(lenovo, args);
                return obj;
            }
        });


        //3.���÷���
        String computer = saleComputer.sale(800);
        System.out.println(computer);
    }
}
