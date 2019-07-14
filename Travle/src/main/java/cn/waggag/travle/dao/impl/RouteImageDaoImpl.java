package cn.waggag.travle.dao.impl;

import cn.waggag.travle.dao.RouteImageDao;
import cn.waggag.travle.domain.RouteImg;
import cn.waggag.travle.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 17:25
 */
public class RouteImageDaoImpl implements RouteImageDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<RouteImg> findImages(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImg> routeImgList = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return routeImgList;
    }
}
