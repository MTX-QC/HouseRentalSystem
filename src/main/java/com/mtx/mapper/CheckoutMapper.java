package com.mtx.mapper;

import com.mtx.model.entity.Checkout;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CheckoutMapper {
/*    int deleteByPrimaryKey(Integer cid);

    int insert(Checkout record);

    int insertSelective(Checkout record);

    Checkout selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Checkout record);

    int updateByPrimaryKey(Checkout record);*/

    //删除已退租的信息
    @Delete("<script>" +
            "delete from checkout where house_id in " +
            "<foreach collection='house_ids' open='(' item='item' separator=',' close=')'> #{item}" +
            "</foreach>" +
            "</script>")
    int deleteCheck(@Param("house_ids") String[] house_ids);
}