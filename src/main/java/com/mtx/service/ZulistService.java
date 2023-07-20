package com.mtx.service;

import com.mtx.model.vo.SearchBean;

import java.util.HashMap;

public interface ZulistService {
    //这个是保障模块加的
    HashMap<String,Object> findZulistByUserid(SearchBean searchBean);

}
