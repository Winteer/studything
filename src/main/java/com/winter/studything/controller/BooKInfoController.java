package com.winter.studything.controller;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.Persons;
import com.winter.studything.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:20007", maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BooKInfoController {

    @Autowired
    BookInfoService booKInfoService;


    @RequestMapping(value = "/getInfoByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByPage(String searchWord,String current_date,String sortColumn,String sortMethod,int pageNum,int pageSize) {
        List<Map<String, Object>> results = new ArrayList<>();
        results = booKInfoService.getInfoByPage(searchWord,current_date,sortColumn,sortMethod,pageNum,pageSize);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCount(String  searchWord,String current_date) {
        int count = 0;
        count = booKInfoService.getCount(searchWord,current_date);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(count,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/deleteByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteByID(int id) {
        booKInfoService.deleteByID(id);
        System.out.println("删除id为"+id+"的数据成功！");
    }


    @RequestMapping(value = "/insertBookInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int insertBookInfo(BookInfo bookInfo) {
        int flag = booKInfoService.insertBookInfo(bookInfo);
        if(flag >0){
            System.out.println("插入成功！");
        }
        return flag;
    }

    //getInfoByID
    @RequestMapping(value = "/getInfoByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByID(BookInfo bookInfo) {
        Map<String, Object> map = new HashMap<>();
        map = booKInfoService.getInfoByID(bookInfo);
        System.out.println(map);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(map,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateForm(BookInfo bookInfo) {
        int flag = -1;
        flag = booKInfoService.updateForm(bookInfo);
        System.out.println(flag);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(flag,
                HttpStatus.OK);
        return responseEntity;
    }



}
