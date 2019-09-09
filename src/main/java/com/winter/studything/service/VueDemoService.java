package com.winter.studything.service;

import com.winter.studything.Entity.Persons;

import java.util.List;
import java.util.Map;

public interface VueDemoService {
    public List<String> finSex();
    public Map<String,Object> getCount();
    public List<Map<String,Object>> getAllInfo();
    public void deleteByID(int id);
    public int insertPerson(Persons person);
    public Map<String, Object> getInfoByID(Persons person);
    public int updateForm(Persons person);
    public int getCount(String  searchWord);
    public List<Map<String, Object>> getInfoByPage(String  searchWord,String sortColumn,String sortMethod,int pageNum,int pageSize);
    public Map<String,Object> tinyUploadFile(String path,String fileData,String name);

}
