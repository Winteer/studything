package com.winter.studything.utils;

import org.springframework.cglib.beans.BeanMap;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {

    public static Map<String, Object> BeanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        BeanMap beanMap = BeanMap.create(object);
        for (Object key : beanMap.keySet()) {
            if (beanMap.get(key) != null && beanMap.get(key) != "") {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }


    /**
     * 生成insert插入语句
     *
     * @param tableName
     * @param tmpMap
     * @param removeCol
     * @return
     */
    public static String makeInsertSql(String tableName, Map<String, Object> tmpMap, String... removeCol) {
        String sql = "";
        String colSql = "";
        String valSql = "";
        for (String col : removeCol) {
            tmpMap.remove(col);
        }
        int size = tmpMap.size();
        int flag = 1;
        /*****/
        //特殊字段处理位置
        /*****/
        for (Object key : tmpMap.keySet()) {
            if (flag < size) {
                colSql = colSql + key + ",";
                valSql = valSql + "'" + tmpMap.get(key) + "',";
            } else {
                colSql = colSql + key;
                valSql = valSql + "'" + tmpMap.get(key) + "'";
            }
            flag++;
        }
        sql = "INSERT INTO " + tableName + "(" + colSql + ") VALUES(" + valSql + ")";
        return sql;
    }


    /**
     * 生成update更新语句
     *
     * @param tableName
     * @param tmpMap
     * @param primaryKey
     * @return
     */
    public static String makeUpdateSql(String tableName, Map<String, Object> tmpMap, String primaryKey) {
        String sql = "";
        String colSql = "";
        String whereSql = " WHERE " + primaryKey + "=" + tmpMap.get(primaryKey);
        tmpMap.remove(primaryKey);
        int size = tmpMap.size();
        int flag = 1;
        /******************/
        //特殊字段处理位置
        /******************/
        for (Object key : tmpMap.keySet()) {
            if (flag < size) {
                colSql = colSql + key + "='" + tmpMap.get(key) + "',";
            } else {
                colSql = colSql + key + "='" + tmpMap.get(key) + "'";
            }
            flag++;
        }
        sql = "UPDATE " + tableName + " SET " + colSql + " " + whereSql;
        return sql;
    }

    public static String makeCountSql(String tableName, String searchWord, String... searchCol) {
        String sql = "";
        String whereSql = "";
        if (searchWord != null && searchWord != "" && searchCol.length > 0) {
            whereSql = " flag";
            for (String col : searchCol) {
                whereSql = whereSql + " or " + col + " like '%"+searchWord+"%' ";
            }
            whereSql= whereSql.replace(" flag or","");
            whereSql = " where "+whereSql;
        }
        sql = "select count(*) as count from " + tableName + whereSql;
        return sql;
    }


    public static String makeSelectWhereSql(String searchWord, String... searchCol) {
        String whereSql = "";
        if (searchWord != null && searchWord != "" && searchCol.length > 0) {
            whereSql = " flag";
            for (String col : searchCol) {
                whereSql = whereSql + " or " + col + " like '%"+searchWord+"%' ";
            }
            whereSql= whereSql.replace(" flag or","");
            whereSql = " where "+whereSql;
        }
        return whereSql;
    }


    /**
     * 获取当前时间往前往后n的日期
     * eg: date ==null 默认是系统当前时间  否则以date为时间起点
     * n > 0 往后
     * n = 0 当前时间
     * n < 0 往前
     * @author zhangheng5@lenovo.com
     * @param date
     * @param n
     * @return
     */
    public static List<String> getBeforeDate(Date date, int n){
        List<String> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        Date today = new Date();
        if(null != date){
            today = date;
        }
        c.setTime(today);
        if(n > 0){
            for(int i=0;i<=n;i++){
                c.add(Calendar.DATE, i);
                list.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                c.setTime(today);
            }
        }else if (n < 0){
            for(int i=n;i<=0;i++){
                c.add(Calendar.DATE, i);
                list.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                c.setTime(today);
            }
        }else{
            c.add(Calendar.DATE, 0);
            list.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
            c.setTime(today);
        }

        return list;
    }

    public static void main(String[] args) {
        Date d = new Date();
        List<String> dateList = new ArrayList<>();
        dateList = getBeforeDate(null,-1);
        System.out.println(dateList);
    }

}
