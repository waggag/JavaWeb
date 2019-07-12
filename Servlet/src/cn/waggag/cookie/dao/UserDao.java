package cn.waggag.cookie.dao;

import cn.waggag.cookie.domain.User;
import cn.waggag.cookie.utils.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    //JdbcTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 登录方法
     * @return
     */
    public User login(User loginuser){
        //编写sql
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user1 = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginuser.getUsername(), loginuser.getPassword());
            return user1;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
