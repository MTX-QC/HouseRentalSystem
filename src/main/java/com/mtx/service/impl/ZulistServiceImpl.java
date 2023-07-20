package com.mtx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtx.mapper.UserMapper;
import com.mtx.mapper.ZulistMapper;
import com.mtx.model.entity.User;
import com.mtx.model.entity.Zulist;
import com.mtx.model.vo.SearchBean;
import com.mtx.model.vo.ZulistBean;
import com.mtx.service.ZulistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ZulistServiceImpl implements ZulistService {

    @Autowired
    private ZulistMapper zulistMapper;
    @Autowired
    private UserMapper userMapper;

    //这个是保障模块加的
    @Override
    public HashMap<String, Object> findZulistByUserid(SearchBean searchBean) {

        //获取当前页码和每页显示数据量的参数
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();
        // 获取用户名
        String username = searchBean.getUsername();
        User user = userMapper.selectByName(username);
        //开始分页
        PageHelper.startPage(cur, size);

        List<ZulistBean> zulists = zulistMapper.selectByUserId(user.getId());
        PageInfo<ZulistBean> pageInfo = new PageInfo<>(zulists);
        //构建HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }


}
