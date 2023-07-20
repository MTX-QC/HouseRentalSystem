package com.mtx.cotroller;


import com.mtx.model.entity.Solve;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Wrong;
import com.mtx.service.SolveService;
import com.mtx.service.UserService;
import com.mtx.service.WrongService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class WrongController {

    @Resource
    WrongService wrongService;

    @Resource
    SolveService solveService;

    @Resource
    UserService userService;

    @RequestMapping(value = "/addwrong", method = RequestMethod.POST)
    public Result addWrong(@RequestBody Wrong wrong) {
        User user = userService.findUserByName(wrong.getName());
        wrong.setUserId(user.getId());
        wrong.setStatus("待处理");
        return wrongService.addWrong(wrong);
    }

    @RequestMapping(value = "/wronglist", method = RequestMethod.GET)
    public Result wrongList(String name) {
        if ("mtx".equals(name)) {
            return wrongService.selectAllWrongByUserName();
        }
        return wrongService.selectWrongByUserName(name);
    }

    @RequestMapping(value = "/handleWrong", method = RequestMethod.GET)
    public Result handleWrong(int id) {
        // 获取要处理的故障
        Wrong wrong = wrongService.selectWrongById(id);
        // 将信息封装到Solve对象中，方便后续添加到已处理表格
        Solve solve = new Solve();
        solve.setAddress(wrong.getAddress());
        solve.setDate(wrong.getDate());
        solve.setDetail(wrong.getDetail());
        solve.setName(wrong.getName());
        solve.setStatus("已处理");
        solve.setHouseId(wrong.getHouseId());
        solve.setUserId(wrong.getUserId());
        // 添加一条数据到已处理表中
        Result addSolve = solveService.addSolve(solve);
        // 从待处理表中删除该数据
        Result wrongById = wrongService.deleteWrongById(id);
        // 根据添加和删除结果判断是否处理成功
        if (addSolve.getCode() == 200 && wrongById.getCode() == 200) {
            return new Result(200, "处理成功！");
        } else {
            return new Result(100, "处理失败！");
        }
    }
}
