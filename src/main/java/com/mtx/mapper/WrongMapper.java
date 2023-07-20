package com.mtx.mapper;

import com.mtx.model.entity.Wrong;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface WrongMapper {

    @Insert("insert into wrong (house_id,address,date,detail,name,user_id,status) values(#{houseId},#{address},#{date},#{detail},#{name},#{userId},#{status})")
    int addWrong(Wrong record);

    List<Wrong> findWrongByUserName(String username);

    List<Wrong> findAllWrong();

    Wrong findWrongById(int id);

    int deleteWrongById(int id);
}