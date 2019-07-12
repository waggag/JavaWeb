package cn.waggag.service;

import cn.waggag.domain.PageBean;
import cn.waggag.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @description: �û������ҵ��ӿ�
 * @author: waggag
 * @time: 2019/7/9 0:53
 */
public interface UserService {
    /**
     * ��ѯ���е��û���Ϣ
     * @return List<User>
     */
    List<User> findAll();

    User login(User user);

    void addUser(User user);

    void deleteUser(String id);

    User findUserById(String userId);

    void updateUser(User user);

    //����uidɾ������ѡ�е��û�
    void deleteUsers(String[] uids);

    /**
     * ��ҳ������ѯ
     * @param currentPage
     * @param rows
     * @param map
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> map);
}
