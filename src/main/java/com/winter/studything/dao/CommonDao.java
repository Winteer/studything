package com.winter.studything.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {

    public int execute(String sql);

    public Map<String, Object> getInfoByID(String sql);

    public List<Map<String, Object>> getInfoList(String sql);
}
