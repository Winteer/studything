package com.winter.studything.controller;

import com.winter.studything.Entity.DepartInfo;
import com.winter.studything.Entity.PersonInfo;
import com.winter.studything.service.OrganizeService;
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
@RequestMapping("/organ")
public class OrganizeController {

    @Autowired
    OrganizeService booKInfoService;

    /**
     * 成员信息
     * @param companyCode
     * @param sortColumn
     * @param sortMethod
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getInfoByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByPage(String companyCode,String sortColumn, String sortMethod, int pageNum, int pageSize) {
        List<Map<String, Object>> results = new ArrayList<>();
        results = booKInfoService.getInfoByPage(companyCode,sortColumn,sortMethod,pageNum,pageSize);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        System.out.println(results);
        return responseEntity;
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCount(String companyCode) {
        int count = 0;
        count = booKInfoService.getCount(companyCode);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(count,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/deleteByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteByID(String id) {
        booKInfoService.deleteByID(id);
        System.out.println("删除id为"+id+"的数据成功！");
    }

    @RequestMapping(value = "/getDepartInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepartInfo() {
        List<Map<String, Object>> results = new ArrayList<>();
        results .add(booKInfoService.getDepartInfo());
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 修改部门信息
     * @param departInfo
     * @return
     */
    @RequestMapping(value = "/updateDepart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDepart(DepartInfo departInfo) {
        int flag = -1;
        flag = booKInfoService.updateDepart(departInfo);
        System.out.println(flag);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(flag,
                HttpStatus.OK);
        return responseEntity;
    }
    /**
     * 修改部门信息
     * @param departInfo
     * @return
     */
    @RequestMapping(value = "/addDepart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDepart(DepartInfo departInfo) {
        int flag = -1;
        flag = booKInfoService.addDepart(departInfo);
        System.out.println(flag);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(flag,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/getAllCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCompany() {
        List<Map<String, Object>> results = new ArrayList<>();
        results = booKInfoService.getAllCompany();
        System.out.println(results);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 获取公司下的部门
     * @param companyCode
     * @return
     */
    @RequestMapping(value = "/getDpartByComp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDpartByComp(String companyCode) {
        List<Map<String, Object>> results = new ArrayList<>();
        results = booKInfoService.getDpartByComp(companyCode);
        System.out.println(results);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(value = "/getDepartInfoByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepartInfoByID(DepartInfo departInfo) {
        Map<String, Object> map = new HashMap<>();
        map = booKInfoService.getDepartInfoByID(departInfo);
        System.out.println(map);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(map,
                HttpStatus.OK);
        return responseEntity;
    }



    @RequestMapping(value = "/insertBookInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int insertBookInfo(PersonInfo bookInfo) {
        int flag = booKInfoService.insertBookInfo(bookInfo);
        if(flag >0){
            System.out.println("插入成功！");
        }
        return flag;
    }

    //getInfoByID
    @RequestMapping(value = "/getInfoByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoByID(PersonInfo bookInfo) {
        Map<String, Object> map = new HashMap<>();
        map = booKInfoService.getInfoByID(bookInfo);
        System.out.println(map);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(map,
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateForm(PersonInfo personInfo) {
        int flag = -1;
        flag = booKInfoService.updateForm(personInfo);
        System.out.println(flag);
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(flag,
                HttpStatus.OK);
        return responseEntity;
    }
}
