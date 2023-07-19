package com.mtx.cotroller;

import com.mtx.model.entity.Houselist;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.HouseListService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class HouseListController {

    @Autowired
    private HouseListService houseListService;

    //查询数据库中所有的房源信息
    @RequestMapping(value = "/houselist",method = RequestMethod.GET)
    public HashMap<String,Object> findAllHouse(SearchBean searchBean) {
        HashMap<String, Object> houseList = houseListService.findAllHouse(searchBean);
        return houseList;
    }

    //添加房源信息        addHouse
    @RequestMapping(value = "/addhouse",method = RequestMethod.POST)
    public Result addHouse(@RequestBody Houselist houselist) {
        Result result = houseListService.addHouse(houselist);
        return result;
    }

    //修改房源信息        edithouse
    @RequestMapping(value = "/edithouse",method = RequestMethod.POST)
    public Result editHouse(@RequestBody Houselist houselist){
        Result result = houseListService.editHouse(houselist);
        return result;
    }

    //删除房源信息        deletehouse
    @RequestMapping(value = "/deletehouse",method = RequestMethod.POST)
    public Result deleteHouse(@RequestBody String[] houseids){
       houseListService.deleteHouse(houseids);
        return new Result(200,"删除成功");
    }

}
