package com.winter.studything.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class SqlUtils {

    public static Map<String, Object> BeanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        BeanMap beanMap = BeanMap.create(object);
        for (Object key : beanMap.keySet()) {
            if (beanMap.get(key) != null ) {
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
                //book_time,start_time,end_time
                if("book_time".equals(key) || "start_time".equals(key) ||"end_time".equals(key)){
                    colSql = colSql + key + ",";
                    valSql = valSql +"date_format('"+tmpMap.get(key)+"','%Y-%m-%d %H:%i:%s'),";
                }else{
                    colSql = colSql + key + ",";
                    valSql = valSql + "'" + tmpMap.get(key) + "',";
                }
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
        String whereSql = " WHERE " + primaryKey + "='" + tmpMap.get(primaryKey)+"'";
        tmpMap.remove(primaryKey);
        int size = tmpMap.size();
        /******************/
        //特殊字段处理位置
        /******************/
        for (Object key : tmpMap.keySet()) {
                if("book_time".equals(key) || "start_time".equals(key) ||"end_time".equals(key)){
                    if("null".equals(tmpMap.get(key).toString())){
                        colSql = colSql + key + "= null,";
                    }else{
                        colSql = colSql + key + "= date_format('"+tmpMap.get(key)+"','%Y-%m-%d %H:%i:%s'),";
                    }
                }else{
                    if("null".equals(tmpMap.get(key).toString())){
                        colSql = colSql + key + "= null,";
                    }else{
                        colSql = colSql + key + "='" + tmpMap.get(key) + "',";
                    }
                }
        }
        int flag = colSql.lastIndexOf(",");
        colSql = colSql.substring(0,colSql.length()-1);
        sql = "UPDATE " + tableName + " SET " + colSql + " " + whereSql;
        return sql;
    }

    public static String makeCountSql(String tableName, String searchWord,String date, String... searchCol) {
        String sql = "";
        String whereSql = "";
        String dateSql = "book_time >=" +  "str_to_date('"+date+" 00:00:00'"+", '%Y-%m-%d %H:%i:%s')" + " and book_time <= " + "str_to_date('"+date+" 23:59:59'"+", '%Y-%m-%d %H:%i:%s')";
        if (searchWord != null && searchWord != "" && searchCol.length > 0) {
            whereSql = " flag";
            for (String col : searchCol) {
                whereSql = whereSql + " or " + col + " like '%"+searchWord+"%' ";
            }
            whereSql= whereSql.replace(" flag or","");
            whereSql = " where ("+whereSql+") ";
        }
        if(searchWord != null && searchWord != "" && searchCol.length > 0 && !"null".equals(date)){
            whereSql = whereSql + " and "+dateSql;
        }else if((searchWord == null || searchWord == "") && !"null".equals(date)){
            whereSql = whereSql + " where  "+dateSql;
        }
        sql = "select count(*) as count from " + tableName + whereSql;
        return sql;
    }


    /**
     * 制作select语句的where部分
     * @param searchWord 查找内容
     * @param date 日期时间字段 --预约时间book_time
     * @param searchCol
     * @return
     */
    public static String makeSelectWhereSql(String searchWord, String date,String... searchCol) {
        String whereSql = "";
        String dateSql = "book_time >=" +  "str_to_date('"+date+" 00:00:00'"+", '%Y-%m-%d %H:%i:%s')" + " and book_time <= " + "str_to_date('"+date+" 23:59:59'"+", '%Y-%m-%d %H:%i:%s')";
        if (searchWord != null && searchWord != "" && searchCol.length > 0 ) {
            whereSql = " flag";
            for (String col : searchCol) {
                whereSql = whereSql + " or " + col + " like '%"+searchWord+"%' ";
            }
            whereSql= whereSql.replace(" flag or","");
            whereSql = " where ("+whereSql+") ";
        }
        if(searchWord != null && searchWord != "" && searchCol.length > 0 && !"null".equals(date)){
            whereSql = whereSql + " and "+dateSql;
        }else if((searchWord == null || searchWord == "") && !"null".equals(date)){
            whereSql = whereSql + " where  "+dateSql;
        }
        return whereSql;
    }

}
