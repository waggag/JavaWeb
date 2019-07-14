package cn.waggag.travle.dao;

import cn.waggag.travle.domain.RouteImg;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 17:25
 */
public interface RouteImageDao {

    /**
     * 根据Rid查询所有的RouteInmage
     * @param rid
     * @return List<RouteImg>
     */
    List<RouteImg> findImages(int rid);
}
