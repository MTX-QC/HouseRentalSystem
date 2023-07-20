package com.mtx.service;

import com.mtx.model.entity.Apply;
import com.mtx.model.entity.Applyout;
import com.mtx.model.entity.Hetong;
import com.mtx.model.entity.Zulist;
import com.mtx.model.vo.SearchBean;
import com.mtx.model.vo.ZulistBean;
import com.mtx.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface RentalInformationService {
    //查找全部的租凭信息
    public HashMap<String, Object> findAllRental(SearchBean searchBean);

    //查看合同
    Hetong findHetong(String house_id);

    //修改合同信息
    Result editHeTong(Hetong hetong);

    //终止合同
    Result deleteHeTong(String house_id);

    //查找全部的一退租信息
    HashMap<String,Object> findAllSurrender(SearchBean searchBean);

    //删除已退租的信息
    Result deleteCheck(String[] house_ids);

    //申请看房
    Result applyCheckUserList(Hetong hetong,HttpServletRequest request);

    //申请退租
    Result addApplyOut(String house_id,HttpServletRequest request);

}
