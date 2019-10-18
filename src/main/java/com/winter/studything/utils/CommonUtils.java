package com.winter.studything.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

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

}
