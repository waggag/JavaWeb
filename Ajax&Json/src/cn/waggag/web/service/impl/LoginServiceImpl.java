package cn.waggag.web.service.impl;

import cn.waggag.dao.LoginDao;
import cn.waggag.dao.impl.LoginDaoImpl;
import cn.waggag.web.service.LoginService;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/11 23:48
 */
public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao = new LoginDaoImpl();

    @Override
    public Boolean userExsit(String username) {
        return loginDao.userExsit(username);
    }
}
