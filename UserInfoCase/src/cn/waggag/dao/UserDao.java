package cn.waggag.dao;

import cn.waggag.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * ��ѯ���е��û���Ϣ
     * @return List
     */
    List<User> findAll();

    User login(User user);

    void addUser(User user);

    void deleteUser(int userId);

    User findUserById(int userId);

    void updateUser(User user);

    Integer findTotalCount(Map<String, String[]> map);

    List<User> findByPage(Integer start, Integer rows, Map<String, String[]> map);
}
