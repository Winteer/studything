package com.winter.studything.controller;

import com.winter.studything.Entity.Persons;
import com.winter.studything.service.VueDemoService;
import com.winter.studything.utils.FTPUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:20007", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class VueDemoController {

    @Autowired
    VueDemoService vueDemoService;

    public  String path = "/tinymce/"; //编辑器上传图片存放路径
    public  String BASE_PATH = "/UpLoad/"; //编辑器上传图片存放路径

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

    /**
     * 富文本编辑器上传照片
     * @param uploadFile
     * @param name
     * @return
     */
    @RequestMapping(value = "/persons/tinyUploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> tinyUploadFile(String uploadFile,String name) {
        Map<String, Object> results = new HashMap<>();
        if(uploadFile != null && uploadFile != ""){
            results = vueDemoService.tinyUploadFile(path,uploadFile,name);
        }
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }


    /**
     * 文件上传
     * @param files
     * @return
     */
    @RequestMapping(value = "/persons/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(MultipartFile[] files) {
        Map<String, Object> results = new HashMap<>();
        results.put("state","上传成功！");
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                results.put("state","文件为空！");
            }else{
                //获取文件名
            String fileName = file.getOriginalFilename();
//                String fileName = file.getName();
                //获取文件后缀
                String ext= null;
                if(fileName.contains(".")){
                    ext = fileName.substring(fileName.lastIndexOf("."));
                }else{
                    ext = "";
                }
                try {
                    InputStream in = file.getInputStream();
                    String url = FTPUtils.uploadFile(BASE_PATH,fileName,in);
                    results.put(fileName,url);
                    System.out.println(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    results.put("state",fileName+"上传失败！IO错误！");
                }finally {
                }
            }
            }
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }





}
