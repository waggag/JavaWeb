package cn.waggag.travle.dao.impl;

import cn.waggag.travle.dao.CategoryDao;
import cn.waggag.travle.domain.Category;
import cn.waggag.travle.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 0:23
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        List<Category> categoryList = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return categoryList;
    }
}
