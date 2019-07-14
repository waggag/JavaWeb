package cn.waggag.travle.dao.impl;

import cn.waggag.travle.dao.RouteDao;
import cn.waggag.travle.domain.Route;
import cn.waggag.travle.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 2:00
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";

        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");
            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = "select * from tab_route where 1 = 1 ";
        //1.定义sql模板
        StringBuilder stringBuilder = new StringBuilder(sql);

        //定义需要查询参数的模板
        List params = new ArrayList();
        if(cid != 0){
            stringBuilder.append( " and cid = ? ");
            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            stringBuilder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        stringBuilder.append("limit ? , ? ");
        sql = stringBuilder.toString();
        params.add(start);
        params.add(pageSize);

        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return route;
    }
}
