package com.winter.studything.dao;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.PersonInfo;

import java.util.List;
import java.util.Map;

public interface OrganizeDao {

    public Map<String,Object> getCount();
    public void deleteByID(String id);
    public int insertBookInfo(PersonInfo personInfo);
    public Map<String, Object> getInfoByID(String sql);
    public int updateForm(String sql);
    public int getCount(String sql);
    public List<Map<String, Object>> getInfoByPage(String sql);

    public Map<String, Object> getHeadDepart();
    public List<Map<String, Object>> getNextDepartInfo(Map<String,Object> map);
    public List<Map<String, Object>> queryForMapList(String sql);
}
