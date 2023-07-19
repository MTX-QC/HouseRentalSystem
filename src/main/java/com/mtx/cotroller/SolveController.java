package com.mtx.cotroller;

import com.mtx.model.vo.SearchBean;
import com.mtx.service.SolveService;
import com.mtx.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;


@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class SolveController {

    @Resource
    SolveService solveService;

    @RequestMapping(value = "/solvelistU", method = RequestMethod.GET)
    public Result solvelistU(String name) {
        return solveService.selectSolveByUserName(name);
    }

    @RequestMapping(value = "/solvelist", method = RequestMethod.GET)
    public HashMap<String, Object> solvelist(SearchBean searchBean, String date, String date1) {
        return solveService.searchSolve(searchBean, date, date1);
    }

    @RequestMapping(value = "/delsolve", method = RequestMethod.POST)
    public Result delsolve(@RequestBody int[] list) {
        return solveService.deleteBatchSolve(list);
    }
}
