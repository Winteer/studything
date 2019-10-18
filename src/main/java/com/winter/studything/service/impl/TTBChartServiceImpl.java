package com.winter.studything.service.impl;

import com.winter.studything.service.TTBChartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TTBChartServiceImpl implements TTBChartService {


    /**
     * 根据日期范围返回统计数据
     *
     * @param dateRange
     * @return
     */
    @Override
    public List<Map<String, Object>> getStatisticByRange(int dateRange) {
        List<Map<String, Object>> retList = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        if (dateRange == 7) {//近一周
            xAxisData = new ArrayList<>(Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日"));

        } else if (dateRange == 15) {//近半个月

        } else if (dateRange == 6) {//近六个月

        } else if (dateRange == 1) {//近一年

        }


        return retList;
    }
}
