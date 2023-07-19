package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.HouselistMapper;
import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.HouseListService;
import com.mtx.utils.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class HouseListServiceImpl implements HouseListService {
    @Autowired
    private HouselistMapper houselistMapper;

    @Override
    public HashMap<String, Object> findAllHouse(SearchBean searchBean) {
        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        //开始分页
        PageHelper.startPage(cur,size); //此代码的下一行一定是真正的数据库查询过程！！！！！！
        //调用查询
        List<Houselist> houselists = houselistMapper.findAllHouse(searchBean);
        //开始封装PageInfo对象
        PageInfo<Houselist> pageInfo = new PageInfo<>(houselists);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    //添加房源信息
    @Override
    public Result addHouse(Houselist houselist) {
        int i = houselistMapper.addHouse(houselist);
        if (i == 0){
            return new Result(100,"添加房源失败");
        }else {
            return new Result(200,"添加成功");
        }

    }

    //修改房源信息
    @Override
    public Result editHouse(Houselist houselist) {
        int i = houselistMapper.editHouse(houselist);
        if (i == 0){
            return new Result(100,"修改房源信息失败");
        }else {
            return new Result(200,"修改房源信息成功");
        }
    }

    //删除房源信息    应该在前面加一个查询的操作，这样逻辑好一点
    @Override
    public void deleteHouse(String[] houseids) {
        houselistMapper.deleteHouse(houseids);
    }
}
