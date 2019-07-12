package cn.waggag.service;

import cn.waggag.domain.PageBean;
import cn.waggag.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @description: 用户管理的业务接口
 * @author: waggag
 * @time: 2019/7/9 0:53
 */
public interface UserService {
    /**
     * 查询所有的用户信息
     * @return List<User>
     */
    List<User> findAll();

    User login(User user);

    void addUser(User user);

    void deleteUser(String id);

    User findUserById(String userId);

    void updateUser(User user);

    //根据uid删除所有选中的用户
    void deleteUsers(String[] uids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param map
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> map);
}
