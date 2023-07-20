package com.mtx.service;

import com.mtx.model.entity.Apply;
import com.mtx.model.entity.Applyout;
import com.mtx.model.entity.Hetong;
import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.SearchBean;
import com.mtx.utils.Result;

import java.util.HashMap;
import java.util.List;

public interface ApplylistService {
    //查找全部的房源信息
    //看房记录
    public HashMap<String, Object> findAll(SearchBean searchBean);

    //管理员同意租赁
    Result addhetong(Hetong hetong);

    //管理员拒绝租赁
    Result noApply(String house_id);

    //退房记录
    public HashMap<String, Object> findOut(SearchBean searchBean);

    //同意退租
    Result agreeOut(String house_id);

    //拒绝退租
    Result jujueApplyout(String house_id);

    //删除
    Result delapplyout(String house_id);
}
