package com.winter.studything.dao.impl;

import com.winter.studything.Entity.BookInfo;
import com.winter.studything.Entity.PersonInfo;
import com.winter.studything.dao.OrganizeDao;
import com.winter.studything.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrganizeDaoImpl implements OrganizeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String BookInfoTable = "book_info"; //预定表表名

    private String User_TAB = "ach_sys_users"; //用户表
    private String Part_TAB = "ach_sys_deptinfo"; //组织部门表


    @Override
    public Map<String, Object> getCount() {
        Map<String, Object> retMap = new HashMap<>();
        retMap = jdbcTemplate.queryForMap("select Count(*) as count from book_info b");
        return retMap;
    }


    @Override
    public void deleteByID(String id) {
        String sql = "delete  from "+User_TAB+" where serial_no ='" + id+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public int insertBookInfo(PersonInfo personInfo) {
        Map<String, Object> map = new HashMap<>();
        int flag = -1;
        map = SqlUtils.BeanToMap(personInfo);
        if (map.size() > 0) {
            String sql = SqlUtils.makeInsertSql(User_TAB, map, "");
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
    public List<Map<String, Object>> queryForMapList(String sql){
        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        retList = jdbcTemplate.queryForList(sql);
        return retList;
    }

    /**
     * 获取头节点信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getHeadDepart(){
        Map<String, Object> retMap = new HashMap<>();
        String sql = "select serial_no as id , dept_name as label from ach_sys_deptinfo WHERE parent_id = '-1'";
        retMap = jdbcTemplate.queryForMap(sql);
        return retMap;
    }

    /**
     * 获取下一层组织机构层级
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getNextDepartInfo(Map<String,Object> map){
        List<Map<String, Object>> retList = new ArrayList<>();
        String sql = "select serial_no as id , dept_name as label,dept_type from ach_sys_deptinfo WHERE parent_id = '"+map.get("id")+"' order by sort asc";
        retList = jdbcTemplate.queryForList(sql);
        return retList;
    }


}
