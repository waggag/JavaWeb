package cn.waggag.text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/11 0:56
 */
public class MapValue {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("张三1", "男");
        map.put("张三2", "男");
        map.put("张三3", "男");
        map.put("张三4", "男");
        map.put("张三5", "男");

        //第一种遍历map的方法，通过加强for循环map.keySet()，然后通过键key获取到value值
        Set<String> set = map.keySet();
        for (String key : set) {
            System.out.println("key : " + key + " value : " + map.get(key));
        }
        System.out.println("====================================");

        //第二种只遍历键或者值，通过加强for循环
        for (String s1 : map.keySet()) {//遍历map的键
            System.out.println("键key ：" + s1);
        }
        for (String s2 : map.values()) {//遍历map的值
            System.out.println("值value ：" + s2);
        }
        System.out.println("====================================");

        //第三种方式Map.Entry<String, String>的加强for循环遍历输出键key和值value
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("键 key ：" + entry.getKey() + " 值value ：" + entry.getValue());
        }
        System.out.println("====================================");

        //第四种Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("键key ：" + entry.getKey() + " value ：" + entry.getValue());

        }
        System.out.println("====================================");
    }

}
