package cn.waggag.dao.impl;

import cn.waggag.dao.UserDao;
import cn.waggag.domain.User;
import cn.waggag.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/9 0:56
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //1.定义sql
        String sql = "select * from user;";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User login(User user) {

        try {
            //1.定义sql
            String sql = "select * from user where username = ? and password = ?";
            User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        //1.定义Sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //2.执行SQl
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void deleteUser(int userId) {
        //1.定义Sql
        String sql = "delete  from user where id = ?";
        //2.执行删除
        template.update(sql, userId);
    }

    @Override
    public User findUserById(int userId) {
        //1.定义sql
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userId);
    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public Integer findTotalCount(Map<String, String[]> map) {
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = map.keySet();
        ArrayList<String> list = new ArrayList<>();
        for (String key : keySet) {
            //排除分页查询条件
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = map.get(key)[0];
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(map);

        Integer totalCount = template.queryForObject(sb.toString(), Integer.class,list.toArray());
        return totalCount;
    }

    @Override
    public List<User> findByPage(Integer start, Integer rows, Map<String, String[]> map) {
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = map.keySet();
        ArrayList<Object> list = new ArrayList<>();
        for (String key : keySet) {
            //排除分页查询条件
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = map.get(key)[0];
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,? ");
        list.add(start);
        list.add(rows);
        List<User> userList = template.query(sb.toString(), new BeanPropertyRowMapper<>(User.class), list.toArray());
        return userList;
    }
}
