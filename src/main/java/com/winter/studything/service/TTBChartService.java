package com.winter.studything.service;

import java.util.List;
import java.util.Map;

public interface TTBChartService {

    public List<Map<String,Object>> getStatisticByRange(int dateRange);
}
