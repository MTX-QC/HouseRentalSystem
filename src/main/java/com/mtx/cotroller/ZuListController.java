package com.mtx.cotroller;

import com.mtx.model.vo.SearchBean;
import com.mtx.service.ZulistService;
import javafx.beans.binding.ObjectBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class ZuListController {

    @Autowired
    ZulistService zulistService;

    // 查询用户对应的租房信息
    @RequestMapping(value = "/zulist", method = RequestMethod.GET)
    public HashMap<String, Object> zulist(SearchBean searchBean) {

        return zulistService.findZulistByUserid(searchBean);
    }
}
