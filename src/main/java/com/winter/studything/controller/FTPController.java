package com.winter.studything.controller;


import com.winter.studything.utils.FTPUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@Controller
@RequestMapping("ftp")
public class FTPController {

//    /**
//     * 根据图片路径上传到服务器
//     * @param path
//     * @return
//     */
//    @RequestMapping(value = "uploadFileByPath", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity getUserInfo(String path){
//        boolean flag = FTPUtils.uploadFile("F:/ApacheSoftware/ftpop/", "test.jpg",);
//        ResponseEntity responseEntity = new ResponseEntity<>(flag,
//                HttpStatus.OK);
//        return responseEntity;
//    }

}
