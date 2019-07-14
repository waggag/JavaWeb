package cn.waggag.travle.dao;

import cn.waggag.travle.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据rid查询route对象
     * @param rid
     * @return  Route对象
     */
    Route findOne(int rid);
}
