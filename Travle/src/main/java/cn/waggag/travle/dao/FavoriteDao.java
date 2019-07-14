package cn.waggag.travle.dao;

import cn.waggag.travle.domain.Favorite;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 19:53
 */
public interface FavoriteDao {

    Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void addFavorite(int rid, int uid);
}
