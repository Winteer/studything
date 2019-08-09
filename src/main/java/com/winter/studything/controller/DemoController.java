package com.winter.studything.controller;

import com.winter.studything.Entity.UserEntity;
import com.winter.studything.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winter
 * @title: demoController
 * @projectName study
 * @description: TODO
 * @date 2019/7/2 12:28
 **/
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@Controller
@RequestMapping("demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    /**
     * 根据id获取user信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getUserInfo(String id){
        Map<String,Object> map = demoService.getUserInfo(id);
        System.out.println(map);
        ResponseEntity responseEntity = new ResponseEntity<>(map,
                HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 获取用户数量
     * @return
     */
    @RequestMapping("getUserCount")
    @ResponseBody
    public Map<String,Object> getUserCount(){
        Map<String,Object> retMap = new HashMap<>();
        retMap = demoService.getUserCount();
        return retMap;
    }

    @RequestMapping(value="/postTest", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserCountByPost(UserEntity user,HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println(user.getId());
        Map<String,Object> retMap = new HashMap<>();
        retMap = demoService.getUserCount();
        return retMap;
    }
}
