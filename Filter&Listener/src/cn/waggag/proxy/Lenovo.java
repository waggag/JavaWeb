package cn.waggag.proxy;

/**
 * @description: ��ʵ����
 * @author: waggag
 * @time: 2019/7/11 0:12
 */
public class Lenovo implements SaleComputer {

    @Override
    public String sale(double money) {
        System.out.println("Lenovo,����"+money+"���˵���");
        return "Lenovo";
    }

    @Override
    public void show() {
        System.out.println("show...Lenovo");
    }
}
