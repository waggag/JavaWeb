package cn.waggag.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 代理对象的使用
 * @author: waggag
 * @time: 2019/7/11 0:15
 */
public class ProxyTest {

    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();

        //2.动态代理增强Lenovo对象
         /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        SaleComputer saleComputer = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /*
               代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
                   参数：
                       1. proxy:代理对象
                       2. method：代理对象调用的方法，被封装为的对象
                       3. args:代理对象调用的方法时，传递的实际参数
            */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理方法增强了");
                //使用真是对象调用该方法
                Object obj = method.invoke(lenovo, args);
                return obj;
            }
        });


        //3.调用方法
        String computer = saleComputer.sale(800);
        System.out.println(computer);
    }
}
