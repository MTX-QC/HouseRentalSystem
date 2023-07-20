package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.*;
import com.mtx.model.entity.*;
import com.mtx.model.vo.CheckoutBean;
import com.mtx.model.vo.SearchBean;
import com.mtx.model.vo.ZulistBean;
import com.mtx.service.RentalInformationService;
import com.mtx.utils.JwtUtils;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class RentalInformationServiceImpl implements RentalInformationService {

    @Autowired
    ZulistMapper zulistMapper;

    @Autowired
    HetongMapper hetongMapper;

    @Autowired
    CheckoutMapper checkoutMapper;

    @Autowired
    ApplyoutMapper applyoutMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserMapper userMapper;

    @Override
    public HashMap<String, Object> findAllRental(SearchBean searchBean) {
        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        //开始分页
        PageHelper.startPage(cur,size); //此代码的下一行一定是真正的数据库查询过程！！！！！！
        //调用查询
        List<ZulistBean> allRental = zulistMapper.findRentalDetails(searchBean);
        //开始封装PageInfo对象
        PageInfo<ZulistBean> pageInfo = new PageInfo<>(allRental);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    //查找合同信息
    @Override
    public Hetong findHetong(String house_id) {
        Hetong hetong = hetongMapper.findHetong(house_id);
        return hetong;
    }

    //修改合同信息
    @Override
    public Result editHeTong(Hetong hetong) {
        int i = hetongMapper.editHeTong(hetong);
        int i1 = hetongMapper.editZulist(hetong);
        if (i ==0){
            return new Result(100,"修改合同信息失败");
        }
        return new Result(200,"修改成功");
    }

    //终止合同
    @Override
    public Result deleteHeTong(String house_id) {
        int i = zulistMapper.deleteHeTong(house_id);
        if (i == 0){

        }
        return new Result(200,"终止合同成功");
    }

    //查询全部的已退租的信息
    @Override
    public HashMap<String, Object> findAllSurrender(SearchBean searchBean) {
        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        //开始分页
        PageHelper.startPage(cur,size); //此代码的下一行一定是真正的数据库查询过程！！！！！！
        //调用查询
        List<CheckoutBean> allRental = zulistMapper.findAllSurrender(searchBean);
        //开始封装PageInfo对象
        PageInfo<CheckoutBean> pageInfo = new PageInfo<>(allRental);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result deleteCheck(String[] house_ids) {
        int i = checkoutMapper.deleteCheck(house_ids);
        if (i>0){
            System.out.println(i);
            return new Result(200,"删除成功");

        }
        return new Result(100,"删除失败");
    }

    //申请看房
    @Override
    public Result applyCheckUserList(Hetong hetong,HttpServletRequest request) {
        //获取token头
        String token = request.getHeader("token");
        System.out.println(token);
        //根据用户id去查询用户信息
        String uuid = jwtUtils.getUsernameFromToken(token);
        Integer user_id = Integer.parseInt(uuid);


        String house_id = hetong.getHouseId();
        String address = hetong.getAddress();
        Double area = hetongMapper.findArea(hetong.getHouseId());
        Double price = hetong.getPrice();
        String fromdate = hetong.getFromdate();
        //添加一条信息到apply中
        int i = hetongMapper.applyCheckUserList(house_id, address, area, price, fromdate, user_id);
        //修改houselist表中的状态
        int i1 = hetongMapper.editHouseListStatus(house_id);
        if (i==0){
            return new Result(100,"申请失败");
        }
        return new Result(200,"申请成功");
    }


    //申请退租
    @Override
    public Result addApplyOut(String house_id,HttpServletRequest request) {
        //获取token头
        String token = request.getHeader("token");
        System.out.println(token);
        //根据用户id去查询用户信息
        String uuid = jwtUtils.getUsernameFromToken(token);
        Integer user_id = Integer.parseInt(uuid);
        System.out.println(user_id);
        String address = applyoutMapper.findHouseList(house_id);
        System.out.println(address);
        if (address != null){
            int i = applyoutMapper.addApplyOut(house_id,address,user_id);
            if (i>0){
                return new Result(200,"申请成功");
            }
        }
        return new Result(100,"申请失败");
    }
}
