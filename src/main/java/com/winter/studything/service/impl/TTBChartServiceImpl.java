package com.winter.studything.service.impl;

import com.winter.studything.dao.CommonDao;
import com.winter.studything.service.TTBChartService;
import com.winter.studything.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TTBChartServiceImpl implements TTBChartService {

    @Autowired
    CommonDao commonDao;

    /**
     * 根据日期范围返回统计数据
     *
     * @param dateRange
     * @return
     */
    @Override
    public Map<String, Object> getStatisticByRange(int dateRange) {
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, Object>> retList = new ArrayList<>();
        List<Map<String, Object>> tmpList = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        Map<String,Object> sumMap = new HashMap<>();
        List<Map<String,Object>> sumList = new ArrayList<>();
        List<Float> sumData = new ArrayList<>();
        int sumFlag = 0;
        nameList.add("大宋奇案");
        nameList.add("玉观音");
        nameList.add("鬼娃学校");
        String sql = "";
        if (dateRange == -6 ||  dateRange == -14  ) {//近一周或近半个月
            xAxisData = CommonUtils.getBeforeDate(null,dateRange);
            if(dateRange == -6){
                dateRange = dateRange + 13;
            }else{
                dateRange = dateRange + 29;
            }
            sql = "select a.* from (select date_format(book_time, '%Y-%m-%d') as book_time,room,sum(income) as income from book_info group by book_time,room) a where a.book_time > DATE_SUB(date_format(curdate(), '%Y-%m-%d'),INTERVAL "+dateRange+" DAY) group by a.book_time,a.room order by a.book_time asc";
            tmpList = commonDao.getInfoList(sql);
        } else if (dateRange == -5 || dateRange == -11) {//近六个月或近一年
            xAxisData = CommonUtils.getMonthBeforeDate(null,dateRange);
            if(dateRange == -5){
                dateRange = dateRange + 11;
            }else{
                dateRange = dateRange + 23;
            }
            sql = "SELECT b.book_time,b.room,sum(b.income) AS income FROM(SELECT date_format(book_time, '%Y-%m') AS book_time,room,sum(income) AS income FROM book_info WHERE book_time > date_format(date_sub(curdate(), INTERVAL "+dateRange+" MONTH),'%Y-%m') GROUP BY book_time,room) b GROUP BY b.book_time,b.room ORDER BY b.book_time ASC";
            tmpList = commonDao.getInfoList(sql);
        }
        /*************************************/
        //数据格式处理
        /*************************************/
        for(String item : nameList){
            Map<String,Object> tmpMap = new HashMap<>();
            List<Float> incomeList = new ArrayList();
            for(String date : xAxisData){
                int findFlag = -1;
                for(Map<String,Object> dataMap : tmpList){
                    if(date.equals(dataMap.get("book_time").toString()) && item.equals(dataMap.get("room").toString())){
                        incomeList.add(Float.parseFloat(dataMap.get("income").toString()));
                        findFlag = 0;
                        break;
                    }
                }
                if(findFlag != 0){
                    incomeList.add(Float.parseFloat("0"));
                }
            }
            tmpMap.put("name",item);
            tmpMap.put("type","bar");
            tmpMap.put("data",incomeList.toArray());
            retList.add(tmpMap);
            /********************************************************/
            //计算总数
            /********************************************************/
            if(sumFlag == 0){
                sumData = incomeList;
            }else{
                for(int i=0; i < incomeList.size();i++){
                    sumData.set(i,sumData.get(i)+incomeList.get(i));
                }
            }
            sumFlag ++;
        }
        sumMap.put("name","总数");
        sumMap.put("type","line");
        sumMap.put("yAxisIndex",1);
        sumMap.put("data",sumData.toArray());

        retList.add(sumMap);
        sumMap.remove("yAxisIndex");
        sumList.add(sumMap);
        retMap.put("xdata",xAxisData);
        retMap.put("data",retList);
        retMap.put("sumData",sumList);
        return retMap;
    }
}
