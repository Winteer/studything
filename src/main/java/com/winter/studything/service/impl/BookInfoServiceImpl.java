package com.winter.studything.service.impl;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.Persons;
import com.winter.studything.dao.BookInfoDao;
import com.winter.studything.service.BookInfoService;
import com.winter.studything.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    BookInfoDao BookInfoDao;

    @Override
    public List<String> finSex(){
        List<String> retList = BookInfoDao.finSex();
        return retList;
    }
    @Override
    public Map<String,Object> getCount(){
        Map<String,Object> retMap = new HashMap<>();
        retMap = BookInfoDao.getCount();
        return retMap;
    }


    @Override
    public List<Map<String,Object>> getAllInfo(){
        List<Map<String,Object>> retList = new ArrayList<>();
        retList = BookInfoDao.getAllInfo();
        return retList;
    }

    @Override
    public void deleteByID(int id){
        BookInfoDao.deleteByID(id);
    }
    @Override
    public int insertBookInfo(BookInfo bookInfo){
        int flag = BookInfoDao.insertBookInfo(bookInfo);
        return flag;
    }

    @Override
    public Map<String, Object> getInfoByID(Persons person) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select id,name,address,sex,date_format(create_datetime, '%Y-%m-%d %H:%i:%s') as date from persons where id =" + person.getId();
        map = BookInfoDao.getInfoByID(sql);
        return map;
    }

    @Override
    public int updateForm(Persons person){
        int flag = -1;
        String sql = "";
        Map<String,Object> beanMap = new HashMap<>();
        beanMap = SqlUtils.BeanToMap(person);
        sql = SqlUtils.makeUpdateSql("test.persons",beanMap,"id");
        flag = BookInfoDao.updateForm(sql);
        return flag;
    }
//    @Override
//    public Map<String, Object> getInfoByID(Persons person) {
//        Map<String, Object> map = new HashMap<>();
//        String sql = "";
//        Map<String,Object> beanMap = new HashMap<>();
//        beanMap = SqlUtils.BeanToMap(person);
//        sql = SqlUtils.makeUpdateSql("test.persons",beanMap,"id");
//        map = BookInfoDao.getInfoByID(sql);
//        return map;
//    }

    @Override
    public int getCount(String  searchWord){
        String sql = "";
        sql = SqlUtils.makeCountSql("test.persons",searchWord,"name","address");
        return BookInfoDao.getCount(sql);
    }

    @Override
    public List<Map<String, Object>> getInfoByPage(String  searchWord,String sortColumn,String sortMethod,int pageNum,int pageSize){
        String sql = "select id,name,address,sex,date_format(create_datetime, '%Y-%m-%d %H:%i:%s') as date from persons ";
        if("".equals(sortMethod) || "undefined".equals(sortMethod)){ //无排序时，默认按照创建时间逆序排列
            sortMethod = "desc";
            sortColumn = "create_datetime";
        }else if("descending".equals(sortMethod)){
            sortMethod = "desc";
        }else if("ascending".equals(sortMethod)){
            sortMethod = "asc";
        }
        sql = sql + SqlUtils.makeSelectWhereSql(searchWord,"name","address") + " order by "+sortColumn+" "+sortMethod+" limit "+(pageNum-1)*pageSize +","+pageSize;
        return BookInfoDao.getInfoByPage(sql);
    }

}
