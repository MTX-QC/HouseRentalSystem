package com.mtx.mapper;

import com.mtx.model.vo.ApplyoutBean;
import com.mtx.model.vo.SearchBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplyoutMapper {
    //通过house_id查数据
    @Select("select address from houselist where houseid=#{house_id}")
    String findHouseList(String house_id);

    //申请看房
    @Insert("insert into applyout (house_id,address,user_id) values (#{house_id},#{houseList},#{user_id})")
    int addApplyOut(@Param("house_id") String house_id,@Param("houseList") String houseList,@Param("user_id") Integer user_id);

    //查找全部的房源信息
    public List<ApplyoutBean> findOut(SearchBean searchBean);
    //同意退租
    @Update("UPDATE applyout SET status = '已同意' WHERE house_id = #{house_id}")
    //要加一条数据到zulist中
    int agreeOut(String house_id);


    //拒绝退租
    @Update("UPDATE applyout SET status = '已拒绝' WHERE house_id = #{house_id}")
    int jujueApplyout(String house_id);

    //删除
    @Delete("delete from applyout where house_id = #{house_id}")
    int delapplyout(String house_id);

}