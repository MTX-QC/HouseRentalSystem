package com.mtx.mapper;

import com.mtx.model.entity.Schedule;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Userlist;
import com.mtx.model.vo.SearchBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    public User selectByName(String username);

    @Insert("INSERT INTO user (username, password, type, nickname) VALUES (#{username}, #{password}, #{type}, #{nickname})")
    public int addUser(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    //查询用户的个人信息
//    @Select("select * from userlist where user_id=#{user_id}")
//    Userlist findByUid(Integer user_id);

//    @Select("select userlist.*,user.password from userlist join user on userlist.user_id = user.id where user_id = #{userId}")
//    Userlist findByUid(Integer userId);



    @Select("select * from user where username = #{username} and password = #{password}")
    User findUser(User user);

    @Insert("insert into user (username,password,type) values(#{username},#{password},'用户')")
    void newUser(User user);

//    @Select("select userlist.*,user.password from userlist join user on userlist.user_id = user.id where user_id = #{userId}")
    @Select("select userlist.*,user.password from userlist join user on userlist.user_id=user.id where userlist.user_id = #{user_id}")
    Userlist findByUid(Integer user_id);

    @Update("update userlist set phone = #{phone} , idcard = #{id} , nickname = #{nickname} where user_id = #{userId}")
    void putInfo(@Param("phone") String phone, @Param("id") String idcard, @Param("nickname")String nickname, Integer userId);

    @Update("update user set password = #{pass} where id = #{userId}")
    void putPassword(@Param("pass")String password, Integer userId );

    @Insert("insert into schedule (date,content) values(#{date},#{content})")
    void newSchedule(Schedule schedule);

    @Select("select * from schedule")
    List<Schedule> findschedule(SearchBean searchBean);

    @Select("select * from schedule where date = #{date}")
    List<Schedule> selectByDate(SearchBean searchBean);

    @Select("select * from schedule where content like CONCAT('%',#{content},'%')")
    List<Schedule> selectByContent(SearchBean searchBean);

    @Delete("delete from schedule where id = #{row}")
    void delSchedule(@Param("row") Integer row);

    @Update("update schedule set date = #{date}, content = #{content} where id = #{id}")
    void editschedule(Schedule schedule);



}