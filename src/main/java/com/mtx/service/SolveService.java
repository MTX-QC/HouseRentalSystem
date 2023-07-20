package com.mtx.service;

import com.mtx.model.entity.Solve;
import com.mtx.model.vo.SearchBean;
import com.mtx.utils.Result;

import java.util.HashMap;

public interface SolveService {
    Result selectSolveByUserName(String username);

    Result addSolve(Solve solve);

    HashMap<String,Object> searchSolve(SearchBean searchBean,String date,String date1);

    Result deleteSolveById(int id);

    Result deleteBatchSolve(int[] list);
}
