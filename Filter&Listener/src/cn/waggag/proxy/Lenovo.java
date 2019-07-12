package cn.waggag.proxy;

/**
 * @description: 真实对象
 * @author: waggag
 * @time: 2019/7/11 0:12
 */
public class Lenovo implements SaleComputer {

    @Override
    public String sale(double money) {
        System.out.println("Lenovo,花了"+money+"买了电脑");
        return "Lenovo";
    }

    @Override
    public void show() {
        System.out.println("show...Lenovo");
    }
}
