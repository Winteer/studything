package com.winter.studything.dao;

import java.util.Map;

/**
 * @author Winter
 * @title: demoDao
 * @projectName study
 * @description: TODO
 * @date 2019/6/212:29
 **/
public interface DemoDao {

    public Map<String,Object> getUserInfo(String id);

    public Map<String,Object> getUserCount();
}
