package com.mtx.service;

import com.mtx.model.entity.Schedule;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Userlist;
import com.mtx.model.vo.SearchBean;
import com.mtx.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {
    //注册用户信息
//    public Result addUser(User user);

    //登录
//    Result login(User user);

//    Result getUserList(HttpServletRequest request);

//    Result userInfo(HttpServletRequest request);



    Result login(User user);

    Result register(User user);

    Result userInfo(HttpServletRequest request);

    Result putInfo(Userlist userlist, HttpServletRequest request);

//    Result putPassword(User user, HttpServletRequest request);

    Result insertschedule(Schedule schedule);

    HashMap<String ,Object> findschedule(SearchBean searchBean);

    Result delSchedule(Integer[] rows);

    Result editschedule(Schedule schedule);
}
