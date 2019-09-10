package com.winter.studything.dao;

import com.winter.studything.Entity.BookInfo;

import java.util.List;
import java.util.Map;

public interface BookInfoDao {

    public List<String> finSex();

    public Map<String,Object> getCount();
    public List<Map<String,Object>> getAllInfo();
    public void deleteByID(int id);
    public int insertBookInfo(BookInfo bookInfo);
    public Map<String, Object> getInfoByID(String sql);
    public int updateForm(String sql);
    public int getCount(String sql);
    public List<Map<String, Object>> getInfoByPage(String sql);
}