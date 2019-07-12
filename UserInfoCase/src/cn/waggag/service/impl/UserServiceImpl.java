package cn.waggag.service.impl;

import cn.waggag.dao.UserDao;
import cn.waggag.dao.impl.UserDaoImpl;
import cn.waggag.domain.PageBean;
import cn.waggag.domain.User;
import cn.waggag.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/9 0:53
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String userId) {
        return userDao.findUserById(Integer.parseInt(userId));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUsers(String[] uids) {
        //先对提交的数据做个校验
        if (uids != null && uids.length > 0) {
            for (String uid : uids) {
                userDao.deleteUser(Integer.parseInt(uid));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> map) {

        PageBean<User> pageBean = new PageBean<>();
        Integer currentPage = Integer.parseInt(_currentPage);
        Integer rows = Integer.parseInt(_rows);
        Integer totalCount = userDao.findTotalCount(map);
        Integer start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start, rows, map);

        int totalPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;

        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
