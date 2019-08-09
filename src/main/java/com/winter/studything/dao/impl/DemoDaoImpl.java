package com.winter.studything.dao.impl;

import com.winter.studything.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Winter
 * @title: demoDaoImpl
 * @projectName studything
 * @description: TODO
 * @date 2019/7/22 10:24
 **/
@Repository
public class DemoDaoImpl implements DemoDao {

    @Autowired
    JdbcTemplate jdbcMySQLTemplate;

    @Override
    public Map<String,Object> getUserInfo(String id){
        Map<String,Object> map = jdbcMySQLTemplate.queryForMap("select * FROM users WHERE id = '"+id+"'");
        return map;
    }

    @Override
    public Map<String,Object> getUserCount(){
        Map<String,Object> retMap = new HashMap<>();
        retMap = jdbcMySQLTemplate.queryForMap("select count(*) as count FROM users");
        return retMap;
    }



}
