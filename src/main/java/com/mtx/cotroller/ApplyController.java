package com.mtx.cotroller;

import com.mtx.mapper.ApplyMapper;
import com.mtx.model.entity.*;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.ApplylistService;
import com.mtx.service.HouseListService;
import com.mtx.service.UserService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class ApplyController {
    @Autowired
    private ApplylistService applylistService;

    //看房记录
    @RequestMapping(value = "/applylist",method = RequestMethod.GET)
    public HashMap<String,Object> findAll(SearchBean searchBean) {
        HashMap<String, Object> map = applylistService.findAll(searchBean);
        return map;
    }

    //管理员同意租赁
    @RequestMapping(value = "/addhetong",method = RequestMethod.POST)
    public Result addhetong(@RequestBody Hetong hetong){
        System.out.println(hetong);
        Result result = applylistService.addhetong(hetong);
        return result;
    }

    //管理员拒绝租赁
    @RequestMapping(value = "/Noapply",method = RequestMethod.GET)
    public Result noApply(String house_id){
        Result result = applylistService.noApply(house_id);
        return result;
    }

    //退租记录
    @RequestMapping(value = "/applyout",method = RequestMethod.GET)
    public HashMap<String,Object> findOut(SearchBean searchBean) {
        HashMap<String, Object> map = applylistService.findOut(searchBean);
        return map;
    }

    //同意退租
    @RequestMapping(value = "/agreeapplyout",method = RequestMethod.GET)
    public Result agreeOut(String house_id){
        Result result = applylistService.agreeOut(house_id);
        return result;
    }

    //拒绝退租
    @RequestMapping(value = "/jujueApplyout",method = RequestMethod.GET)
    public Result jujueApplyout(String house_id){
        Result result = applylistService.jujueApplyout(house_id);
        return result;
    }

    //删除
    @RequestMapping(value = "/delapplyout",method = RequestMethod.GET)
    public Result delapplyout(String house_id){
        Result result = applylistService.delapplyout(house_id);
        return result;
    }

}
