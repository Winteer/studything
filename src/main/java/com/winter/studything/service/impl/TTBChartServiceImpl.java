package com.winter.studything.service.impl;

import com.winter.studything.service.TTBChartService;
import com.winter.studything.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String sql = "";
        if (dateRange == -6 ||  dateRange == -14  ) {//近一周或近半个月
            xAxisData = new ArrayList<>();
            xAxisData = CommonUtils.getBeforeDate(null,dateRange);
            sql = "select a.* from (select date_format(book_time, '%Y-%m-%d') as book_time,room,sum(income) as income from test.book_info group by book_time,room) a where a.book_time > DATE_SUB(date_format(curdate(), '%Y-%m-%d'),INTERVAL "+dateRange+" DAY) group by a.book_time,a.room order by a.book_time asc";
        } else if (dateRange == -5 || dateRange == -11) {//近六个月或近一年
            xAxisData = new ArrayList<>();
            xAxisData = CommonUtils.getBeforeDate(null,dateRange);
        }
        return retList;
    }
}
