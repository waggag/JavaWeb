package cn.waggag.service.impl;

import cn.waggag.dao.ProvinceDao;
import cn.waggag.dao.impl.ProvinceDaoImpl;
import cn.waggag.domain.Province;
import cn.waggag.service.ProvinceService;
import cn.waggag.utils.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/12 11:16
 */
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao  provinceDao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public String findAllJson() {
        //1.先从redis中查询数据库
        //1.1获取客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String json = jedis.get("province");
        if(json == null || json.length() == 0){
            //查询数据
            List<Province> list = provinceDao.findAll();
            //2.转换为json字符串
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //3.将json存入redis
            jedis.set("province", json);
            jedis.close();
            return json;

        }else{
            jedis.close();
            return json;
        }
    }
}
