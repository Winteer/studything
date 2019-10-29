package com.winter.studything.service;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.Persons;

import java.util.List;
import java.util.Map;

public interface BookInfoService {
    public Map<String,Object> getCount();
    public void deleteByID(int id);
    public int insertBookInfo(BookInfo bookInfo);
    public Map<String, Object> getInfoByID(BookInfo bookInfo);
    public int updateForm(BookInfo bookInfo);
    public int getCount(String searchWord,String date);
    public List<Map<String, Object>> getInfoByPage(String searchWord,String current_date, String sortColumn, String sortMethod, int pageNum, int pageSize);
    public List<Map<String,Object>> roomStatistic(String statisticType,String timeType);
}
