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

    //退租记录
    @RequestMapping(value = "/applyout",method = RequestMethod.GET)
    public HashMap<String,Object> findOut(SearchBean searchBean) {
        HashMap<String, Object> map = applylistService.findOut(searchBean);
        return map;
    }

    //同意退租
    @RequestMapping(value = "/agreeapplyout",method = RequestMethod.GET)
    public Result agreeOut(Integer house_id){
        System.out.println(house_id);
        Result result = applylistService.agreeOut(house_id);
        return result;
    }

    //拒绝退租  delapplyout
    @RequestMapping(value = "/jujueApplyout")
    public Result jujueApplyout(Integer house_id){
        Result result = applylistService.jujueApplyout(house_id);
        return result;
    }

    //删除  delapplyout
    @RequestMapping(value = "/delapplyout",method = RequestMethod.GET)
    public Result delApplyOut(Integer house_id){
        Result result = applylistService.delApplyOut(house_id);
        return result;
    }

}
