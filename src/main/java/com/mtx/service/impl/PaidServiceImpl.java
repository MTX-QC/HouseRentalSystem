package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.mtx.mapper.PaidMapper;
import com.mtx.model.entity.Paid;
import com.mtx.model.entity.Topaid;
import com.mtx.model.vo.ZulistBean;
import com.mtx.service.PaidService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PaidServiceImpl implements PaidService {
    @Autowired
    private PaidMapper paidMapper;

    @Override
    public Result findToPaidByU(String name) {
        List<Topaid> topaidlist  = paidMapper.findToPaidByU(name);
        return new Result(200,topaidlist,"查询成功");
    }

    @Override
    public HashMap<String ,Object> selectall(String search, String date, String date1, Integer cur, Integer size, String name) {
        PageHelper.startPage(cur,size);
        String type = paidMapper.findUTypeByName(name);
        if(type.equals("房东")){
            name=null;
        }
        List<Paid>paidList = paidMapper.findAllPaid(search,date,date1,name);

        PageInfo<Paid> pageInfo = new PageInfo<>(paidList);
        HashMap<String ,Object>map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public HashMap<String, Object> findZulist(String search, Integer cur, Integer size) {
        PageHelper.startPage(cur,size);
        List<ZulistBean>zuList = paidMapper.findZulist(search);
        PageInfo<ZulistBean> pageInfo = new PageInfo<>(zuList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result addtopaid(Topaid topaid) {
        topaid.setStatus("未支付");
        int count = paidMapper.addTopaid(topaid);
        if(count!=1){
            return new Result(201,"提交失敗");
        }
        return new Result(200,"提交成功");
    }

    @Override
    public Result findAllTopaid(String search) {
        List<Topaid> topaids = paidMapper.findAllTopaid(search);
        return new Result(200,topaids,"查询成功");
    }

    @Override
    public Result gotopay(String id) {
        List<Paid> paidlist  = paidMapper.findToPaidByid(id);

        Date date = new Date();
        for (Paid paid : paidlist) {
            paid.setStatus("已支付");
            paid.setPaydate(date);
        }
        int i = paidMapper.deleteTopaidByid(id);
        int j = paidMapper.insertPaids(paidlist);
        return new Result(200,"支付成功");
    }

    @Override
    public Result delPaidById(Integer[] list) {
        paidMapper.delPaidById(list);
        return new Result(200,"刪除成功");
    }


}
