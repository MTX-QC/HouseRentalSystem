package com.mtx.service;

import com.mtx.model.entity.Wrong;
import com.mtx.utils.Result;

import java.util.List;

public interface WrongService {
    Result addWrong(Wrong wrong);

    Result selectWrongByUserName(String username);

    Wrong selectWrongById(int id);

     Result deleteWrongById(int id);

    Result selectAllWrongByUserName();
}
