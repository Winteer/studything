package com.winter.studything.dao.impl;

import com.winter.studything.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 更新，插入或删除，用此方法。
     * @param sql
     * @return
     */
    @Override
    public int execute(String sql){
        int flag = -1;
        flag = jdbcTemplate.update(sql);
        return flag;
    }

    /**
     * 根据id查询信息
     * @param sql
     * @return
     */
    @Override
    public Map<String, Object> getInfoByID(String sql) {
        Map<String, Object> map = new HashMap<>();
        map = jdbcTemplate.queryForMap(sql);
        return map;
    }

    /**
     * 执行语句获取list
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> getInfoList(String sql){
        List<Map<String, Object>> retList = new ArrayList<>();
        retList = jdbcTemplate.queryForList(sql);
        return retList;
    }
}
