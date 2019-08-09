package com.winter.studything.service;

import java.util.Map;

/**
 * @author Winter
 * @title: demoService
 * @projectName study
 * @description: TODO
 * @date 2019/6/212:29
 **/
public interface DemoService {

    public Map<String,Object> getUserInfo(String id);

    public Map<String,Object> getUserCount();
}
