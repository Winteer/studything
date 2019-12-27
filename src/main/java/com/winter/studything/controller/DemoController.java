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
//
//    @RequestMapping(value="/gongshang", method= RequestMethod.POST)
//    @ResponseBody
//    public String gongshang() {
//        String ret= "";
//        try {
//            String endpoint = "http://172.28.128.138:8081/ssxt_nx/services/CompanyBaseInfoSupport";
//            //创建一个服务（service）调用（call）
//            Service service = new Service();
//            Call call = (Call) service.createCall();
//            //设置service所在的url
//            call.setTargetEndpointAddress(new java.net.URL(endpoint));
//            call.setOperation("getCompanyBaseInfoByCard");
//            String[] str = {"642102197809230013"};
//            call.invoke(new Object[]{str, "8317c797-c6ae-414d-ab65-81c6a3c35a6f"});
//            ret = (String) call.invoke(new Object[]{str, "8317c797-c6ae-414d-ab65-81c6a3c35a6f"});
//            System.out.println("******************************");
//            System.out.println(ret);
//            System.out.println("******************************");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }

}
