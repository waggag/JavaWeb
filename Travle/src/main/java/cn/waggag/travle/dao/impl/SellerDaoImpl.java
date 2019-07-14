package cn.waggag.travle.dao.impl;

import cn.waggag.travle.dao.SellerDao;
import cn.waggag.travle.domain.Seller;
import cn.waggag.travle.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 17:24
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findSeller(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        return seller;
    }
}
