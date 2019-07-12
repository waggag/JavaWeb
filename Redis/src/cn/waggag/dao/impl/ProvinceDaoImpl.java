package cn.waggag.dao.impl;

import cn.waggag.dao.ProvinceDao;
import cn.waggag.domain.Province;
import cn.waggag.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/12 11:12
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        List<Province> provinceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinceList;
    }
}
