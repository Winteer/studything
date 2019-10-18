package com.winter.studything.controller;

import com.winter.studything.service.TTBChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 有关统计
 */
@CrossOrigin(origins = "http://localhost:20007", maxAge = 3600)
@RestController
@RequestMapping("/statistic")
public class TTBChartController {

    @Autowired
    TTBChartService ttbChartService;

    @RequestMapping(value = "/getStatisticByRange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStatisticByRange(int dateRange) {
        List<Map<String, Object>> results = new ArrayList<>();
        results = ttbChartService.getStatisticByRange(dateRange);
        ResponseEntity<List<Map<String, String>>> responseEntity = new ResponseEntity(results,
                HttpStatus.OK);
        return responseEntity;
    }


}
