package cn.waggag.travle.service.impl;

import cn.waggag.travle.dao.CategoryDao;
import cn.waggag.travle.dao.impl.CategoryDaoImpl;
import cn.waggag.travle.domain.Category;
import cn.waggag.travle.service.CategoryService;
import cn.waggag.travle.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 0:22
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从Redis中查询数据
        Jedis jedis = JedisUtil.getJedis();
        Set<String> categorys = jedis.zrange("category", 0, -1);
        List<Category> categoryList = null;
        //2.判断查询的集合是否为空
        if(categorys == null || categorys.size() == 0){
            //2.1查询的结果为0，从数据库查询，并将其存入redis
            categoryList = categoryDao.findAll();
            for (Category category : categoryList) {
                jedis.zadd("category",category.getCid(),category.getCname());
            }
        }else{
            //如果不为空，将Set数据存入List返回
            categoryList = new ArrayList<Category>();
            for (String name : categorys) {
                Category category = new Category();
                category.setCname(name);
                categoryList.add(category);
            }
        }
        return  categoryList;
    }

}
