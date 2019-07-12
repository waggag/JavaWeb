package cn.waggag.dao;

import cn.waggag.domain.Province;

import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/12 11:12
 */
public interface ProvinceDao {
    List<Province> findAll();
}
