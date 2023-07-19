package com.mtx.service;

import com.mtx.model.entity.Apply;
import com.mtx.model.entity.Applyout;
import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.SearchBean;
import com.mtx.utils.Result;

import java.util.HashMap;
import java.util.List;

public interface ApplylistService {
    //查找全部的房源信息
    //看房记录
    public HashMap<String, Object> findAll(SearchBean searchBean);

    //退房记录
    public HashMap<String, Object> findOut(SearchBean searchBean);

    //同意退租
    Result agreeOut(Integer id);

    //拒绝退租
    Result jujueApplyout(Integer aoid);

    //删除
    Result delApplyOut(Integer house_id);
}
