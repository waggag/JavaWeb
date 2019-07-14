package cn.waggag.travle.service.impl;

import cn.waggag.travle.dao.FavoriteDao;
import cn.waggag.travle.dao.impl.FavoriteDaoImpl;
import cn.waggag.travle.domain.Favorite;
import cn.waggag.travle.service.FavoriteService;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 19:50
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        //对象有值，返回true
        return favorite != null;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.addFavorite(rid,uid);
    }
}
