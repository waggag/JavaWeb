package cn.waggag.travle.service.impl;

import cn.waggag.travle.dao.FavoriteDao;
import cn.waggag.travle.dao.RouteDao;
import cn.waggag.travle.dao.RouteImageDao;
import cn.waggag.travle.dao.SellerDao;
import cn.waggag.travle.dao.impl.FavoriteDaoImpl;
import cn.waggag.travle.dao.impl.RouteDaoImpl;
import cn.waggag.travle.dao.impl.RouteImageDaoImpl;
import cn.waggag.travle.dao.impl.SellerDaoImpl;
import cn.waggag.travle.domain.PageBean;
import cn.waggag.travle.domain.Route;
import cn.waggag.travle.domain.RouteImg;
import cn.waggag.travle.domain.Seller;
import cn.waggag.travle.service.RouteService;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 1:59
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private RouteImageDao routeImageDaoe = new RouteImageDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);
        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(int rid) {
        //根据rid查询Route对象
        Route route = routeDao.findOne(rid);

        //根据id查询所属商家的信息，填充到route中
        Seller seller = sellerDao.findSeller(route.getSid());
        route.setSeller(seller);

        //根据rid查询路线图片
        List<RouteImg> routeImgList = routeImageDaoe.findImages(rid);
        route.setRouteImgList(routeImgList);

        //查询收藏次数
        int count = favoriteDao.findCountByRid(rid);
        route.setCount(count);

        return route;
    }
}
