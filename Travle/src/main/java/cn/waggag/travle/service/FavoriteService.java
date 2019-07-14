package cn.waggag.travle.service;


public interface FavoriteService {
    /**
     * 判断是否实现
     * @param rid
     * @param uid
     */
    boolean isFavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void addFavorite(int rid, int uid);
}
