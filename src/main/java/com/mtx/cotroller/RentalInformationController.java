package com.mtx.cotroller;

import com.mtx.model.entity.*;
import com.mtx.model.vo.SearchBean;
import com.mtx.model.vo.ZulistBean;
import com.mtx.service.RentalInformationService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class RentalInformationController {
    @Autowired
    RentalInformationService rentalInformationService;

    //查询所有的在租列表
    @RequestMapping(value = "/zulist", method = RequestMethod.GET)
    public HashMap<String, Object> findAllRental(SearchBean searchBean) {
        HashMap<String, Object> rentalList = rentalInformationService.findAllRental(searchBean);
        return rentalList;
    }

    //查看合同  findHetong
    @RequestMapping(value = "/findHetong", method = RequestMethod.GET)
    public Result findHetong(String house_id) {
        Hetong hetong = rentalInformationService.findHetong(house_id);
        System.out.println(hetong);
        return new Result(200, hetong, "查询成功");
    }

    //修改合同    edithetong
    @RequestMapping(value = "/edithetong", method = RequestMethod.POST)
    public Result editHeTong(@RequestBody Hetong hetong) {
        System.out.println(hetong);
        Result result = rentalInformationService.editHeTong(hetong);
        return result;
    }
    //终止合同   deletehetong
    @RequestMapping(value = "/deletehetong",method = RequestMethod.GET)
    public Result deleteHeTong(String house_id){
        rentalInformationService.deleteHeTong(house_id);
        return new Result(200,"终止合同成功");
    }

    //已退租列表   checkout
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public HashMap<String, Object> findAllSurrender(SearchBean searchBean) {
        HashMap<String, Object> allSurrender = rentalInformationService.findAllSurrender(searchBean);
        return allSurrender;
    }

    //删除已退租的信息     deleteCheck
    @RequestMapping(value = "/deleteCheck",method = RequestMethod.POST)
    public Result deleteCheck(@RequestBody String[] house_ids){
        Result deleteCheck = rentalInformationService.deleteCheck(house_ids);
        return deleteCheck;
    }

    //申请看房    applycheckuserlist
    @RequestMapping(value = "/applycheckuserlist",method = RequestMethod.POST)
    public Result applyCheckUserList(@RequestBody Hetong hetong){
        Result applyCheckUserList = rentalInformationService.applyCheckUserList(hetong);
        return applyCheckUserList;
    }

    //申请退租    addApplyout      解决啦！
    @RequestMapping(value = "/addApplyout", method = RequestMethod.GET)
    public Result addApplyOut(String house_id,HttpServletRequest request) {
        Result applyOut = rentalInformationService.addApplyOut(house_id,request);
        return applyOut;
    }


}
