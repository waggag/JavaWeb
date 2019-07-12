package cn.waggag.jedis;

import cn.waggag.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: Jedis快速入门
 * @author: waggag
 * @time: 2019/7/12 9:57
 */
public class JedisTest {

    @Test
    public void test1() {
        //1.获取连接
        Jedis jedis = new Jedis("106.12.55.66", 6379);
        //通过jedis赋值
        jedis.set("Jedis", "Jedis");
        //取值
        String string = jedis.get("Jedis");
        System.out.println(string);
        jedis.close();
    }

    //使用Jedis连接池连接数据库
    @Test
    public void jedisPool() {

        //0.创建一个config对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);

        //JedisPool
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"106.12.55.66", 6379);
        //通过数据库连接池获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.set("jedisPool", "JedisPool连接成功");
        System.out.println(jedis.get("jedisPool"));
        //先关闭jedis
        jedis.close();
        //再关闭jedis连接池
        jedisPool.close();
    }

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("106.12.55.66", 6379);
        // jedis.auth("password"); //如果需要密码
        int i = 0; // 记录操作次数
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start >= 10000) {
                break;
            }
            i++;
            jedis.set("test" + i, i + " ");
        }
        System.out.println("redis每秒操作" + i + "次");
    }


    @Test
    public void testJedisPoolUtils(){
        JedisPoolUtils jedisPoolUtils = new JedisPoolUtils();
        Jedis jedis = jedisPoolUtils.getJedis();
        jedis.set("hello","word");
        jedis.close();
    }
}
