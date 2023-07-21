package com.mtx.mapper;


import com.mtx.model.entity.Hetong;
import com.mtx.model.vo.ApplyBean;
import com.mtx.model.vo.SearchBean;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ApplyMapper {
    //查找全部的房源信息
    public List<ApplyBean> findAll(SearchBean searchBean);

    //管理员同意租赁   应该添加一条数据到zulist表
    @Insert("insert into Hetong(chuzu,chuzu_idcard,zuke,zuke_idcard,fromdate,todate,price,address,house_id,payday) values(#{chuzu},#{chuzuIdcard},#{zuke},#{zukeIdcard},#{fromdate},#{todate},#{price},#{address},#{houseId},#{payday})")
    int addhetong(Hetong hetong);

    @Insert("insert into zulist(house_id,price,address,user_id,contract_id) values (#{houseId},#{price},#{address},#{zukeUserId},'52') ")
    int addzulist(String houseId,Double price,String address,Integer zukeUserId);


    //通过前端传进来的zuke名字去查询zuke的user_id
    @Select("SELECT user_id from userlist where nickname=#{zuke}")
    int findUserIdFromZuKe(String zuke);


    //管理员拒绝租赁
//    @Update("UPDATE apply SET status = '已拒绝' WHERE house_id = #{house_id}")
    @Delete("DELETE from apply WHERE house_id = #{house_id}")
    int noApply(String house_id);

    //还要删除一条zulist表中信息
    @Delete("DELETE from zulist WHERE house_id = #{house_id}")
    int deletezulist(String house_id);


}