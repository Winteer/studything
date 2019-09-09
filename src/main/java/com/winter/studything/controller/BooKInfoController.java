package com.winter.studything.controller;

import com.winter.studything.Entity.Persons;
import com.winter.studything.service.VueDemoService;
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
@RequestMapping("/tesdddt")
public class BooKInfoController {

    @Autowired
    VueDemoService vueDemoService;

    @RequestMapping(value = "/persons/sex", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSexAll() {
        ArrayList<Map<String, String>> results = new ArrayList<>();

        for (Object value :  vueDemoService.finSex()) {

            Map<String, String> sex = new HashMap<>();

            sex.put("label", value.toString());

            sex.put("value", value.toString());

            results.add(sex);
        }

        System.out.println(results);

        ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(value = "/persons/getAllInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllInfo() {
        List<Map<String, Object>> results = new ArrayList<>();
        results = vueDemoService.getAllInfo();
        System.out.println(results);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/persons/getCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCount(String  searchWord) {
        int count = 0;
        count = vueDemoService.getCount(searchWord);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(count,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/persons/deleteByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteByID(int id) {
        vueDemoService.deleteByID(id);
        System.out.println("删除id为"+id+"的数据成功！");
    }


    @RequestMapping(value = "/persons/insertPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int insertPerson(Persons person) {
        int flag = vueDemoService.insertPerson(person);
        if(flag >0){
            System.out.println("插入成功！");
        }
        return flag;
    }

    //getInfoByID
    @RequestMapping(value = "/persons/getInfoByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByID(Persons person) {
        Map<String, Object> map = new HashMap<>();
        map = vueDemoService.getInfoByID(person);
        System.out.println(map);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(map,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/persons/updateForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateForm(Persons person) {
        int flag = -1;
        flag = vueDemoService.updateForm(person);
        System.out.println(flag);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(flag,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/persons/getInfoByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByPage(String searchWord,String sortColumn,String sortMethod,int pageNum,int pageSize) {
        List<Map<String, Object>> results = new ArrayList<>();
        results = vueDemoService.getInfoByPage(searchWord,sortColumn,sortMethod,pageNum,pageSize);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }

}
