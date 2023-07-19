package com.mtx.mapper;

import com.mtx.model.entity.Apply;
import com.mtx.model.entity.ApplyKey;
import com.mtx.model.vo.ApplyBean;
import com.mtx.model.vo.SearchBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {
/*    int deleteByPrimaryKey(ApplyKey key);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(ApplyKey key);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);*/


//    @Insert("insert into apply(house_id,address,area,price,status,user_id) values(#{house_id},#{address},#{area},#{price},#{status},#{user_id})")
//    int insertapply(Apply apply);

//    @Update("UPDATE apply SET address = #{address}, area = #{area}, price = #{price},apply_date=#{apply_date}, status = #{status} WHERE house_id = #{house_id}")
//    int updateapply(Apply apply);
//    int updateByPrimaryKey(Apply record);

    //查找全部的房源信息
    public List<ApplyBean> findAll(SearchBean searchBean);


}