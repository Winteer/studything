package com.winter.studything.dao;

import com.winter.studything.Entity.Persons;

import java.util.List;
import java.util.Map;

public interface VueDemoDao {

    public List<String> finSex();

    public Map<String,Object> getCount();
    public List<Map<String,Object>> getAllInfo();
    public void deleteByID(int id);
    public int insertPerson(Persons person);
    public Map<String, Object> getInfoByID(String sql);
    public int updateForm(String sql);
    public int getCount(String sql);
    public List<Map<String, Object>> getInfoByPage(String  sql);
}
