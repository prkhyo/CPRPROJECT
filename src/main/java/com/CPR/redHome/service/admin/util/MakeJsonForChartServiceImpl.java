package com.CPR.redHome.service.admin.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class MakeJsonForChartServiceImpl implements MakeJsonForChartServcie {

    @Override
    public JSONObject makeJsonForChart(Map<String, Integer> map, String column1, String column2) {
        // google Chart에 사용할 수 있는 JSON형식으로 만들어 주는 메소드.
        // 사용법.
        // 1. mapper에서 불러온 값이 들어있는 map을 양식에 맞게 파라미터에 넣어줌.
        // 2. 주제에 맞는 column을 2개 입력해준다.(String 형식/ 주제, 카운트 순으로 작성. / ex) column1="연령대", column2="회원수")

        // 최종 data
        JSONObject data = new JSONObject();

        // 컬럼 객체

        // col
        JSONObject col1 = new JSONObject();
        JSONObject col2 = new JSONObject();
        JSONArray title = new JSONArray();
        col1.put("label", column1);
        col1.put("type", "string");
        col2.put("label", column2);
        col2.put("type", "number");

        title.add(col1);
        title.add(col2);

        // 최종 data에 col 입력.
        data.put("cols", title);

        // row
        JSONArray body = new JSONArray();

        for(Map.Entry<String, Integer> entrySet : map.entrySet()){
            JSONObject subject = new JSONObject();
            subject.put("v", entrySet.getKey());
            JSONObject count = new JSONObject();
            count.put("v", entrySet.getValue());

            JSONArray row = new JSONArray();
            row.add(subject);
            row.add(count);

            JSONObject c = new JSONObject();
            c.put("c", row);

            body.add(c);
        }
        // 최종 data에 rows 입력
        data.put("rows", body);

        //System.out.println("data = " + data);

        return data;
    }
}
