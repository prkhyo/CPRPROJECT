package com.CPR.redHome.service.admin.util;

import org.json.simple.JSONObject;

import java.util.Map;

public interface MakeJsonForChartServcie {

    // Chart를 위한 JSON 데이터 만들어주기
    JSONObject makeJsonForChart(Map<String,Integer> map, String column1, String column2);
}
