package com.mtx.mapper;

import com.mtx.model.entity.Houselist;
import com.mtx.model.entity.HouselistKey;
import com.mtx.model.vo.SearchBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouselistMapper {
    //分页查询数据--房源列表
    //关键词   下拉列表的值  没有任何值null
    public List<Houselist> findAllHouse(SearchBean searchBean);

    //添加房源信息
    @Insert("insert into houselist (houseid,address,area,price,status) values (#{houseid},#{address},#{area},#{price},#{status})")
    int addHouse(Houselist houselist);

    //修改房源信息
    @Update("UPDATE houselist SET address = #{address}, area = #{area}, price = #{price}, status = #{status} WHERE houseid = #{houseid}")
    int editHouse(Houselist houselist);

    //删除房源信息
//    @Delete("DELETE FROM houselist WHERE houseid = #{houseids}")
    @Delete("<script>" +
            "delete from houselist where houseid in " +
            "<foreach collection='houseids' open='(' item='item' separator=',' close=')'> #{item}" +
            "</foreach>" +
            "</script>")
    void deleteHouse(@Param("houseids") String[] houseids);
}