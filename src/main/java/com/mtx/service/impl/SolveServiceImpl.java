package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.SolveMapper;
import com.mtx.model.entity.Solve;
import com.mtx.model.vo.SearchBean;
import com.mtx.service.SolveService;
import com.mtx.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SolveServiceImpl implements SolveService {

    @Resource
    SolveMapper solveMapper;

    @Override
    public Result selectSolveByUserName(String username) {
        List<Solve> solveList = solveMapper.findSolveByUserName(username);
        if (solveList.size() > 0) {
            return new Result(200, solveList, "获取成功");
        } else {
            return new Result(100, solveList, "获取失败");
        }
    }

    @Override
    public Result addSolve(Solve solve) {
        int i = solveMapper.addSolve(solve);
        if (i == 0) {
            return new Result(100, "添加失败");
        } else {
            return new Result(200, "添加成功");
        }
    }

    @Override
    public HashMap<String, Object> searchSolve(SearchBean searchBean, String date, String date1) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        PageHelper.startPage(cur, size);
        String search = searchBean.getSearch();
        List<Solve> solveList = solveMapper.findSolveByUNameAndTime(search, date, date1);
        PageInfo<Solve> pageInfo = new PageInfo<>(solveList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public Result deleteSolveById(int id) {
        int i = solveMapper.deleteSolveById(id);
        if (i == 0) {
            return new Result(100, "删除失败");
        } else {
            return new Result(200, "删除成功");
        }
    }

    @Override
    public Result deleteBatchSolve(int[] list) {
        int i = solveMapper.deleteBatchSolve(list);
        if (i == 0) {
            return new Result(100, "删除失败");
        } else {
            return new Result(200, "删除成功");
        }
    }
}
