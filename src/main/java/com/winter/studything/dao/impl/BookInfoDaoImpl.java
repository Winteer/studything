package com.winter.studything.dao.impl;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.dao.BookInfoDao;
import com.winter.studything.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookInfoDaoImpl implements BookInfoDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String BookInfo_TAB = "book_info"; //预定表表名


    @Override
    public Map<String, Object> getCount() {
        Map<String, Object> retMap = new HashMap<>();
        retMap = jdbcTemplate.queryForMap("select Count(*) as count from book_info b");
        return retMap;
    }


    @Override
    public void deleteByID(int id) {

        String sql = "delete  from "+BookInfo_TAB+" where id =" + id;
        jdbcTemplate.update(sql);
    }

    @Override
    public int insertBookInfo(BookInfo bookInfo) {
        Map<String, Object> map = new HashMap<>();
        int flag = -1;
        map = SqlUtils.BeanToMap(bookInfo);
        map.remove("id");
        if (map.size() > 0) {
            String sql = SqlUtils.makeInsertSql(BookInfo_TAB, map, "id");
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    @Override
    public Map<String, Object> getInfoByID(String sql) {
        Map<String, Object> map = new HashMap<>();
        map = jdbcTemplate.queryForMap(sql);
        return map;
    }

    @Override
    public int updateForm(String sql) {
        int flag =-1;
        flag = jdbcTemplate.update(sql);
        return flag;
    }

    @Override
    public int getCount(String sql){
        int count = 0;
        Map<String,Object> map = new HashMap<>();
        map = jdbcTemplate.queryForMap(sql);
        count = Integer.parseInt(map.get("count").toString());
        return count;
    }

    @Override
    public List<Map<String, Object>> getInfoByPage(String sql){
        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        retList = jdbcTemplate.queryForList(sql);
        return retList;
    }

    @Override
    public List<Map<String, Object>> getListInfo(String sql){
        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        retList = jdbcTemplate.queryForList(sql);
        return retList;
    }

    @Override
    public List<Map<String,Object>> roomStatistic(String sql){
        List<Map<String,Object>> list = new ArrayList();
        list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
