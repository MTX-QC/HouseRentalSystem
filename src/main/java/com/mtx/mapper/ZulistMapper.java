package com.mtx.mapper;

import com.mtx.model.entity.*;
import com.mtx.model.vo.CheckoutBean;
import com.mtx.model.vo.SearchBean;
import com.mtx.model.vo.ZulistBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ZulistMapper {
/*    int deleteByPrimaryKey(ZulistKey key);

    int insert(Zulist record);

    int insertSelective(Zulist record);

    Zulist selectByPrimaryKey(ZulistKey key);

    int updateByPrimaryKeySelective(Zulist record);

    int updateByPrimaryKey(Zulist record);*/


//    @Select("select * from zulist")
    //查询当前用户的所有的租凭信息
    public List<ZulistBean> findRentalDetails(SearchBean searchBean);


    //查询当前用户的已退租信息
    public List<CheckoutBean> findAllSurrender(SearchBean searchBean);

    //终止合同
    @Delete("DELETE FROM zulist WHERE house_id = #{house_id}")
    int deleteHeTong(String house_id);


}