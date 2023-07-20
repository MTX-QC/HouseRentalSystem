package com.mtx.mapper;

import com.mtx.model.entity.Solve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SolveMapper {
    List<Solve> findSolveByUserName(String username);

    int addSolve(Solve solve);

    List<Solve> findSolveByUNameAndTime(@Param("username") String username, @Param("date") String date,
                                        @Param("date1") String date1);

    int deleteSolveById(int id);

    int deleteBatchSolve(int[] list);
}