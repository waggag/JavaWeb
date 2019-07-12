package cn.waggag.service;

import cn.waggag.domain.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> findAll();

    String findAllJson();
}
