package cn.waggag.travle.service;

import cn.waggag.travle.domain.PageBean;
import cn.waggag.travle.domain.Route;

public interface RouteService {

    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据rid查询线路具体信息
     * @param rid
     * @return
     */
    Route findOne(int rid);
}
