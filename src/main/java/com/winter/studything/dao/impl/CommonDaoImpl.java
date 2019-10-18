package com.winter.studything.dao.impl;

import com.winter.studything.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
