package com.winter.studything.service;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.Persons;

import java.util.List;
import java.util.Map;

public interface BookInfoService {
    public List<String> finSex();
    public Map<String,Object> getCount();
    public List<Map<String,Object>> getAllInfo();
    public void deleteByID(int id);
    public int insertBookInfo(BookInfo bookInfo);
    public Map<String, Object> getInfoByID(BookInfo bookInfo);
    public int updateForm(BookInfo bookInfo);
    public int getCount(String searchWord);
    public List<Map<String, Object>> getInfoByPage(String searchWord, String sortColumn, String sortMethod, int pageNum, int pageSize);

}
