package com.mtx.mapper;

import com.mtx.model.entity.Paid;
import com.mtx.model.entity.Topaid;
import com.mtx.model.vo.ZulistBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaidMapper {
    /*    int deleteByPrimaryKey(Integer id);

        int insert(Paid record);

        int insertSelective(Paid record);

        Paid selectByPrimaryKey(Integer id);

        int updateByPrimaryKeySelective(Paid record);

        int updateByPrimaryKey(Paid record);*/
    @Select("select id,house_id,address,price,date,name,user_id,status from topaid where name=#{name}")
    List<Topaid> findToPaidByU(String name);

    List<Paid> findAllPaid(@Param("search") String search, @Param("date") String date, @Param("date1") String date1, @Param("name") String name);

    List<ZulistBean> findZulist(@Param("search") String search);

    @Insert("insert into topaid values (null,#{house_id},#{address},#{price},#{date},#{name},#{user_id},#{status})")
    int addTopaid(Topaid topaid);

    List<Topaid> findAllTopaid(@Param("search") String search);

    @Delete("delete from topaid where id = #{id}")
    int deleteTopaidByid(String house_id);

    @Select("select house_id,address,price,date,name,user_id,status from topaid where id=#{id}")
    List<Paid> findToPaidByid(String id);

    int insertPaids(@Param("paidlist") List<Paid> paidlist);

    @Select("select type from user where username=#{name}")
    String findUTypeByName(@Param("name") String name);

    void delPaidById(@Param("list") Integer[] list);
}