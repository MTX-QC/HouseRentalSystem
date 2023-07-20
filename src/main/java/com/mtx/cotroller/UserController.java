package com.mtx.cotroller;

import com.mtx.model.entity.Schedule;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Userlist;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.UserService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController   //设置响应的数据格式为JSON
@RequestMapping("/api/*")
@CrossOrigin
public class UserController {

    /*    @Autowired
        private UserService userService;*/
    @Resource
    private UserService userService;

    //注册功能
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        Result result = userService.addUser(user);
        return result;
    }

    //登录功能
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result Login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    //查看用户的个人信息
/*    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public Result getUserList(HttpServletRequest request){
        Result result = userService.getUserList(request);
        return result;
    }*/

//    @GetMapping("getUserList")
/*    @RequestMapping(value = "getUserList",method = RequestMethod.GET)
    public Result userInfo(HttpServletRequest request) {
        Result result = userService.userInfo(request);
        return result;
    }*/


    @GetMapping("getUserList")
    public Result userInfo(HttpServletRequest request) {
        Result result = userService.userInfo(request);
        return result;
    }

    @PostMapping(value = "editUser")
    public Result putInfo(@RequestBody Userlist userlist, HttpServletRequest request) {
        Result result = userService.putInfo(userlist, request);
        return result;
    }

    @PostMapping(value = "editUserfd")
    public Result putInfofd(@RequestBody Userlist userlist, HttpServletRequest request) {
        Result result = userService.putInfo(userlist, request);
        return result;
    }

     @PostMapping("editUserPass")
        public Result putpassword(@RequestBody User user,HttpServletRequest request){
            Result result = userService.putPassword(user, request);
            return result;
        }
        @PostMapping("editUserPassfd")
        public Result putpasswordfd(@RequestBody User user,HttpServletRequest request){
            Result result = userService.putPassword(user, request);
            return result;
        }
    @PostMapping("insertschedule")
    public Result insertschedule(@RequestBody Schedule schedule) {
        Result result = userService.insertschedule(schedule);
        return result;
    }

    @GetMapping("schedulelist")
    public Object findschedule(SearchBean searchBean) {
        HashMap<String, Object> map = userService.findschedule(searchBean);
        return map;
    }

    @PostMapping("delSchedule")
    public Result delSchedule(@RequestBody Integer[] rows) {
        Result result = userService.delSchedule(rows);
        return result;
    }

    @PostMapping("editschedule")
    public Result editschedule(@RequestBody Schedule schedule) {
        Result result = userService.editschedule(schedule);
        return result;
    }
}
