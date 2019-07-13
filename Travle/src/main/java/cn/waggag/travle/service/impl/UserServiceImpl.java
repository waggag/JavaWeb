package cn.waggag.travle.service.impl;

import cn.waggag.travle.dao.UserDao;
import cn.waggag.travle.dao.impl.UserDaoImpl;
import cn.waggag.travle.domain.User;
import cn.waggag.travle.service.UserService;
import cn.waggag.travle.utils.MailUtils;
import cn.waggag.travle.utils.UuidUtil;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/13 11:14
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        //1.根据用户名查询用户信息
        User user1 = userDao.findByUsername(user.getUsername());
        if(user1 == null){
            //用户名不存在
            //2.保存用户信息
            //2.1设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //2.2设置激活状态 N代表未激活 Y代表激活
            user.setStatus("N");
            userDao.save(user);
            //3.激活邮件发送
            String content = "<a href='http://localhost//user/active?code="+user.getCode()+"'>点击激活旅游网站</a>";
            MailUtils.sendMail(user.getEmail(),content,user.getUsername()+"激活邮件");
            return  true;
        }else{
            return  false;
        }
    }

    /**
     * 激活用户
     * @param code 激活码
     * @return
     */
    @Override
    public Boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
            //2.查询到用户修改用户状态
           userDao.updateStatus(user);
           return true;
        }else{
            return false;
        }

    }

    @Override
    public User login(User user) {
        User login_user =  userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return login_user;
    }
}
