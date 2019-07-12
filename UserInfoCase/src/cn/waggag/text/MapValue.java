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
        map.put("����1", "��");
        map.put("����2", "��");
        map.put("����3", "��");
        map.put("����4", "��");
        map.put("����5", "��");

        //��һ�ֱ���map�ķ�����ͨ����ǿforѭ��map.keySet()��Ȼ��ͨ����key��ȡ��valueֵ
        Set<String> set = map.keySet();
        for (String key : set) {
            System.out.println("key : " + key + " value : " + map.get(key));
        }
        System.out.println("====================================");

        //�ڶ���ֻ����������ֵ��ͨ����ǿforѭ��
        for (String s1 : map.keySet()) {//����map�ļ�
            System.out.println("��key ��" + s1);
        }
        for (String s2 : map.values()) {//����map��ֵ
            System.out.println("ֵvalue ��" + s2);
        }
        System.out.println("====================================");

        //�����ַ�ʽMap.Entry<String, String>�ļ�ǿforѭ�����������key��ֵvalue
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("�� key ��" + entry.getKey() + " ֵvalue ��" + entry.getValue());
        }
        System.out.println("====================================");

        //������Iterator������ȡ��Ȼ���ȡ��Map.Entry<String, String>���ٵõ�getKey()��getValue()
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("��key ��" + entry.getKey() + " value ��" + entry.getValue());

        }
        System.out.println("====================================");
    }

}
