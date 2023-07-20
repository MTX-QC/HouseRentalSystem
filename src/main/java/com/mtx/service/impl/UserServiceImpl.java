package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.UserMapper;
import com.mtx.model.entity.Schedule;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Userlist;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.UserService;
import com.mtx.utils.JwtUtils;
import com.mtx.utils.Result;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
/*    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;*/

    @Override
    public Result addUser(User user) {
        //用户名不重复
        User user1 = userMapper.selectByName(user.getUsername());
        if (user1 != null){
            return new Result(201,"用户名已被注册，注册失败");
        }else {
            userMapper.addUser(user);
            return new Result(200,"注册成功");
        }
    }

/*    @Override
    public Result login(User user) {
        //根据用户名和密码来完成查询操作
        User user1 = userMapper.login(user);
        if (user1 == null){
            //登录失败
            return new Result(100,"用户名或密码错误");
        }else {
            String uuid = jwtUtils.createToken(user1.getId() + "");
//            String token = jwtUtils.createToken(String.valueOf(user));
            return new Result(200,user1,"登录成功，欢迎您！",uuid);
        }
    }*/

/*    @Override
    public Result getUserList(HttpServletRequest request) {
        //获取token头
        String token = request.getHeader("token");
        //根据用户id去查询用户信息
        String uuid = jwtUtils.getUsernameFromToken(token);
        Integer user_id = Integer.parseInt(uuid);
        Userlist userlist = userMapper.findByUid(user_id);
        return new Result(200,userlist,"查询成功");
    }*/

/*    @Override
    public Result userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println(token);
        String uuid = jwtUtils.getUsernameFromToken(token);
        int user_id = Integer.parseInt(uuid);
        System.out.println(user_id);
        Userlist userlist = userMapper.findByUid(user_id);
        return new Result(200, userlist, "查询成功");
    }*/

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserMapper userMapper;

    //这个是保障模块加的
    @Override
    public User findUserByName(String name) {
        return userMapper.selectByName(name);
    }
    @Override
    public Result login(User user) {
        User user1 = userMapper.findUser(user);
        if (user1 == null){
            return new Result(100,"用户名或密码错误");
        }
        else {
            String token = jwtUtils.createToken(user1.getId()+"");
            return new Result(200, user1, "登陆成功", token);
        }
    }

    @Override
    public Result register(User user) {
        userMapper.newUser(user);
        return new Result(200,"注册成功");
    }

    @Override
    public Result userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        String uuid = jwtUtils.getUsernameFromToken(token);
        int user_id = Integer.parseInt(uuid);
        Userlist userlist = userMapper.findByUid(user_id);
        return new Result(200,userlist,"查询成功");
    }

    @Override
    public Result putInfo(Userlist userlist,HttpServletRequest request) {
        String token = request.getHeader("token");
        String uuid = jwtUtils.getUsernameFromToken(token);
        int user_id = Integer.parseInt(uuid);
        userMapper.putInfo(userlist.getPhone(),userlist.getIdcard(),userlist.getNickname(),user_id);
        return new Result(200,"修改成功");
    }

    /*这里有个问题*/

    @Override
    public Result putPassword(@RequestBody User user, HttpServletRequest request) {
        String token = request.getHeader("token");
        String uuid = jwtUtils.getUsernameFromToken(token);
        int user_id = Integer.parseInt(uuid);
        userMapper.putPassword(user.getPass(),user_id);
        return new Result(200,"修改成功");
    }

    @Override
    public Result insertschedule(Schedule schedule) {
        userMapper.newSchedule(schedule);
        return new Result(200,"添加成功");
    }

    @Override
    public HashMap<String, Object> findschedule(SearchBean searchBean) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        PageHelper.startPage(cur,size);
        List<Schedule> schedulelist = null;
        if (searchBean.getDate() ==null && searchBean.getSearch() == null){
            schedulelist= userMapper.findschedule(searchBean);
        }
        else if (!searchBean.getDate().equals("")){
            schedulelist = userMapper.selectByDate(searchBean);
        }
        else if (searchBean.getContent() !=""){
            schedulelist = userMapper.selectByContent(searchBean);
        }
        PageInfo<Schedule> PageInfo = new PageInfo<>(schedulelist);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",PageInfo.getList());
        map.put("total",PageInfo.getTotal());
        return map;
    }

    @Override
    public Result delSchedule(Integer[] rows) {
        for (Integer row :rows ) {
            userMapper.delSchedule(row);
        }
        return new Result(200,"删除成功");
    }

    @Override
    public Result editschedule(Schedule schedule) {
        userMapper.editschedule(schedule);
        return new Result(200,"修改成功");
    }

//    @Override
//    public Result selectByDate(Schedule schedule) {
//        List<Schedule> schedules = userMapper.selectByDate(schedule);
//        return new Result(200,schedules,"查询成功");
//    }


}
