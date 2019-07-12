package cn.waggag.dao.impl;

import cn.waggag.dao.LoginDao;
import cn.waggag.domain.User;
import cn.waggag.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/11 23:51
 */
public class LoginDaoImpl  implements LoginDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Boolean userExsit(String username) {
        String sql = "select * from user where username = ?";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class),username);
        return (users != null && users.size() > 0) ? true : false;
    }
}
