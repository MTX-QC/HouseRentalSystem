package com.mtx.service;



import com.mtx.model.entity.Topaid;
import com.mtx.utils.Result;

import java.util.HashMap;

public interface PaidService {
    Result findToPaidByU(String name);


    HashMap<String ,Object> selectall(String search, String date, String date1, Integer cur, Integer size, String name);

    HashMap<String, Object> findZulist(String search, Integer cur, Integer size);

    Result addtopaid(Topaid topaid);

    Result findAllTopaid(String search);

    Result gotopay(String id);


    Result delPaidById(Integer[] list);
}
