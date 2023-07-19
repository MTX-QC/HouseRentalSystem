package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.ApplyMapper;

import com.mtx.mapper.ApplyoutMapper;
import com.mtx.model.entity.Applyout;
import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.ApplyBean;
import com.mtx.model.vo.ApplyoutBean;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.ApplylistService;

import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class ApplylistServiceImpl implements ApplylistService {

    @Autowired(required = false)
    private ApplyMapper applyMapper;
    @Autowired(required = false)
    private ApplyoutMapper applyoutMapper;

//看房记录
    @Override
    public HashMap<String, Object> findAll(SearchBean searchBean) {
        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        //开始分页
        PageHelper.startPage(cur,size);
        //调用查询
        List<ApplyBean> applyBeans = applyMapper.findAll(searchBean);
        PageInfo pageInfo = new PageInfo<>(applyBeans);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

//    退房记录
    @Override
    public HashMap<String, Object> findOut(SearchBean searchBean) {
        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        //开始分页
        PageHelper.startPage(cur,size);
        //调用查询
        List<ApplyoutBean> applyoutBeans = applyoutMapper.findOut(searchBean);
        PageInfo pageInfo = new PageInfo<>(applyoutBeans);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

//    同意退租
    @Override
    public Result agreeOut(Integer house_id) {
        int i = applyoutMapper.agreeOut(house_id);
        if (i == 0){
            return new Result(100,"已拒绝");
        }else {
            return new Result(200,"已同意");
        }
    }

    @Override
    public Result delApplyOut(Integer house_id) {
        int i = applyoutMapper.delApplyOut(house_id);
        if (i == 0){
            return new Result(100,"删除失败");
        }else {
            return new Result(200,"删除成功");
        }
    }

    //拒绝退租
    @Override
    public Result jujueApplyout(Integer house_id) {
        int i = applyoutMapper.jujueApplyout(house_id);
        if (i == 0){
            return new Result(100,"拒绝退租操作失败");
        }else {
            return new Result(200,"已拒绝退租");
        }
    }

}
