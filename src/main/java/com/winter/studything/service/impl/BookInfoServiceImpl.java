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
    private String BookInfo_TAB = "book_info"; //预定信息表表名
    private  String[] roomNames ={"玉观音","大宋奇案","鬼娃学校"};
    private  String[] payWays ={"微信","支付宝","现金","美团","云闪付"};

    @Override
    public Map<String,Object> getCount(){
        Map<String,Object> retMap = new HashMap<>();
        retMap = BookInfoDao.getCount();
        return retMap;
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
    public Map<String, Object> getInfoByID(BookInfo bookInfo) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select id,date_format(book_time, '%Y-%m-%d %H:%i:%s') as book_time ,phone,room,number,pay_mode,income,date_format(start_time, '%Y-%m-%d %H:%i:%s') as start_time,date_format(end_time, '%Y-%m-%d %H:%i:%s') as end_time,date_format(create_time, '%Y-%m-%d %H:%i:%s') as create_time from "+BookInfo_TAB+" where id =" + bookInfo.getId();
        map = BookInfoDao.getInfoByID(sql);
        return map;
    }

    @Override
    public int updateForm(BookInfo bookInfo){
        int flag = -1;
        String sql = "";
        Map<String,Object> beanMap = new HashMap<>();
        beanMap = SqlUtils.BeanToMap(bookInfo);
        if(!beanMap.isEmpty()){
            sql = SqlUtils.makeUpdateSql(BookInfo_TAB,beanMap,"id");
            flag = BookInfoDao.updateForm(sql);
        }
        return flag;
    }

    @Override
    public int getCount(String  searchWord,String date){
        String sql = "";
        sql = SqlUtils.makeCountSql(BookInfo_TAB,searchWord,date,"phone","room","pay_mode");
        return BookInfoDao.getCount(sql);
    }

    @Override
    public List<Map<String, Object>> getInfoByPage(String  searchWord,String current_date,String sortColumn,String sortMethod,int pageNum,int pageSize){
        String sql = "select id,date_format(book_time, '%Y-%m-%d %H:%i:%s') as book_time,phone,room,number,pay_mode,income,date_format(start_time, '%Y-%m-%d %H:%i:%s') as start_time,date_format(end_time, '%Y-%m-%d %H:%i:%s') as end_time,date_format(create_time, '%Y-%m-%d %H:%i:%s') as create_time from "+BookInfo_TAB+" ";
        if("".equals(sortMethod) || "undefined".equals(sortMethod)){ //无排序时，默认按照创建时间逆序排列
            sortMethod = "desc";
            sortColumn = "create_time";
        }else if("descending".equals(sortMethod)){
            sortMethod = "desc";
        }else if("ascending".equals(sortMethod)){
            sortMethod = "asc";
        }
        sql = sql + SqlUtils.makeSelectWhereSql(searchWord,current_date,"phone","room","pay_mode") + " order by "+sortColumn+" "+sortMethod+" limit "+(pageNum-1)*pageSize +","+pageSize;
        return BookInfoDao.getInfoByPage(sql);
    }

    /******************************************/
    //统计报表
    /******************************************/

    /**
     *
     * @param statisticType 统计维度；按照主题收益或者收款方式统计
     * @param timeType 时间维度：按照最近七天，最近三十天，最近三个月，最近6个月，最近12个月
     * @return
     */
    @Override
    public List<Map<String,Object>> roomStatistic(String statisticType,String timeType){
        List<Map<String,Object>> list = new ArrayList();
        String sql = "";
        String days = "";
        if(timeType != "all" && timeType!=""){
            if("seven".equals(timeType)){
                days = "7";
            }else if("thirty".equals(timeType)){
                days = "30";
            }
            sql = "select book.room,book.book_time,sum(book.income) from " +
                    "(select room,date_format(book_time,'%Y-%m-%d') as book_time,income from "+BookInfo_TAB+" where date_sub(curdate(), INTERVAL "+days+" DAY) <= date(`book_time`)) book " +
                    "GROUP BY book.room,book.book_time order by book.book_time asc";
        }else if(!"".equals(timeType) && "all".equals(timeType)){
            sql = "select book.room,book.book_time,sum(book.income) from " +
                    "(select room,date_format(book_time,'%Y-%m-%d') as book_time,income from "+BookInfo_TAB+") book " +
                    "GROUP BY book.room,book.book_time order by book.book_time asc";
        }
        list = BookInfoDao.roomStatistic(sql);
        if(list.size() > 0){
            for(String room : roomNames ){
                for(Map<String,Object> tmpMap : list){

                }
            }

        }
        return list;
    }

}
