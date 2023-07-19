package com.mtx.service;

import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.SearchBean;
import com.mtx.utils.Result;

import java.util.HashMap;

public interface HouseListService {
    //查找全部的房源信息
    public HashMap<String, Object> findAllHouse(SearchBean searchBean);

    //添加房源信息
    Result addHouse(Houselist houselist);

    //修改房源信息
    Result editHouse(Houselist houselist);

    //删除房源信息
    void deleteHouse(String[] houseids);
}
