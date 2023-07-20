package com.mtx.mapper;

import com.mtx.model.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface HetongMapper {
    /*    int deleteByPrimaryKey(Integer id);

        int insert(Hetong record);

        int insertSelective(Hetong record);

        Hetong selectByPrimaryKey(Integer id);

        int updateByPrimaryKeySelective(Hetong record);

        int updateByPrimaryKey(Hetong record);*/
    //根据house_id查询合同具体信息
    @Select("select * from hetong where house_id=#{house_id}")
    Hetong findHetong(String house_id);



    //根据house_id修改合同具体信息   这只能修改一个表中的数据
/*    @Update({
            "UPDATE hetong SET",
            "address = #{hetong.address},",
            "chuzu = #{hetong.chuzu},",
            "chuzu_idcard = #{hetong.chuzu_idcard},",
            "zuke = #{hetong.zuke},",
            "zuke_idcard = #{hetong.zuke_idcard},",
            "fromdate = #{hetong.fromdate},",
            "todate = #{hetong.todate},",
            "price = #{hetong.price},",
            "payday = #{hetong.payday}",
            "WHERE house_id = #{hetong.house_id};",
            "UPDATE zulist SET",
            "price = #{zulist.price},",
            "address = #{zulist.address}",
            "WHERE house_id = #{zulist.house_id}"
    })*/
    @Update("UPDATE hetong SET address = #{address}, chuzu = #{chuzu}, chuzu_idcard = #{chuzu_idcard}, zuke = #{zuke} , zuke_idcard = #{zuke_idcard}" +
            ", fromdate = #{fromdate}, todate = #{todate}, price = #{price}, payday = #{payday} WHERE house_id = #{house_id}")
    int editHeTong(Hetong hetong);

    @Update("UPDATE zulist SET address = #{address},price = #{price} WHERE house_id = #{house_id}")
    int editZulist(Hetong hetong);

    //申请看房
//    @Insert("insert into hetong (chuzu,chuzu_idcard,zuke, zuke_idcard, fromdate, todate,price,address,house_id) VALUES " +
//            "(#{chuzu},#{chuzu_idcard},#{zuke}, #{zuke_idcard}, #{fromdate}, #{todate}, #{price}, #{address}, #{house_id})")
    @Insert("insert into apply (house_id,address,area, price, apply_date, status,user_id) VALUES " +
            "(#{house_id},#{address},#{area}, #{price}, #{fromdate}, '申请中', #{user_id})")
    int applyCheckUserList(String house_id, String address, Double area, Double price, String fromdate,Integer user_id);

    //通过houseId查房子的面积
    @Select("select area from houselist where houseid=#{houseId}")
    Double findArea(String houseId);

    //修改houselist表中的状态
    @Update("update houselist set status='申请中' where houseid=#{house_id}")
    int editHouseListStatus(String house_id);

}