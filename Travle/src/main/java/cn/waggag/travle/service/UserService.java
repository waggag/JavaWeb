package cn.waggag.travle.service;

import cn.waggag.travle.domain.User;

public interface UserService {
    Boolean regist(User user);

    Boolean active(String code);

    User login(User user);
}
