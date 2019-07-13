package cn.waggag.travle.dao;

import cn.waggag.travle.domain.User;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
