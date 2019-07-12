package cn.waggag.text;

import cn.waggag.service.UserService;
import cn.waggag.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/9 1:17
 */
public class TestService {

    @Test
    public void TestUserService(){
        UserService userService = new UserServiceImpl();
        System.out.println(userService.findAll());
    }
}
