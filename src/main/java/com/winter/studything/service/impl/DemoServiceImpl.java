package com.winter.studything.service.impl;

import com.winter.studything.dao.DemoDao;
import com.winter.studything.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Winter
 * @title: demoServiceImpl
 * @projectName studything
 * @description: TODO
 * @date 2019/7/2210:25
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoDao demoDao;

    @Override
    public Map<String,Object> getUserInfo(String id){
        Map<String,Object> map = demoDao.getUserInfo(id);
        return map;
    }

    @Override
    public Map<String,Object> getUserCount(){
        Map<String,Object> retMap = new HashMap<>();
        retMap = demoDao.getUserCount();
        return retMap;
    }
}
