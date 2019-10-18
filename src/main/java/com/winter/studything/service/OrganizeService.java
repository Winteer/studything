package com.winter.studything.service;


import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.DepartInfo;
import com.winter.studything.Entity.PersonInfo;

import java.util.List;
import java.util.Map;

public interface OrganizeService {
//    public Map<String,Object> getCount();
    public void deleteByID(String id);
    public int insertBookInfo(PersonInfo personInfo);
    public Map<String, Object> getInfoByID(PersonInfo personInfo);
    public int updateForm(PersonInfo bookInfo);
    public int updateDepart(DepartInfo departInfo);
    public int addDepart(DepartInfo departInfo);
    public int getCount(String companyCode);
    public List<Map<String, Object>> getInfoByPage(String companyCode, String sortColumn, String sortMethod, int pageNum, int pageSize);

    public Map<String, Object> getDepartInfo();
    public Map<String, Object> getDepartInfoUtils(Map<String,Object> map);
    public Map<String, Object> getDepartInfoByID(DepartInfo departInfo);
    public List<Map<String, Object>> getAllCompany();
    public List<Map<String, Object>> getDpartByComp(String companyCode);
}
