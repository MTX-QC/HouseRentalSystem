package com.mtx.service.impl;

import com.mtx.mapper.WrongMapper;
import com.mtx.model.entity.Wrong;
import com.mtx.service.WrongService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class WrongServiceImpl implements WrongService {

    @Resource
    WrongMapper wrongMapper;

    @Override
    public Result addWrong(Wrong wrong) {
        int i = wrongMapper.addWrong(wrong);
        if (i == 0) {
            return new Result(100, "报障失败");
        } else {
            return new Result(200, "报障成功");
        }
    }

    @Override
    public Result selectWrongByUserName(String username) {
        List<Wrong> wrongList = wrongMapper.findWrongByUserName(username);
        if (wrongList.size() > 0) {
            return new Result(200, wrongList,"获取成功");
        } else {
            return new Result(100, wrongList,"获取失败");
        }
    }

    @Override
    public Result deleteWrongById(int id) {
        int i = wrongMapper.deleteWrongById(id);
        if (i == 0) {
            return new Result(100, "删除失败");
        } else {
            return new Result(200, "删除成功");
        }
    }

    @Override
    public Wrong selectWrongById(int id) {
        return wrongMapper.findWrongById(id);
    }

    @Override
    public Result selectAllWrongByUserName() {
        List<Wrong> wrongList = wrongMapper.findAllWrong();
        if (wrongList.size() > 0) {
            return new Result(200, wrongList,"获取成功");
        } else {
            return new Result(100, wrongList,"获取失败");
        }
    }
}
