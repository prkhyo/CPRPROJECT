package com.CPR.redHome.service.googleChart;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.admin.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoogleChartServiceImpl implements GoogleChartService{

    private final MemberAdminService adminService;

    @Override
    public JSONObject getChartData() {

        List<MemberDto> items = adminService.selectAllMembers();

        JSONObject data = new JSONObject();

        JSONObject col1 = new JSONObject();
        JSONObject col2 = new JSONObject();

        JSONArray title = new JSONArray();
        col1.put("label","생년월일");
        col1.put("type","date");
        col2.put("label","회원id");
        col2.put("type","number");

        title.add(col1);
        title.add(col2);

        data.put("cols", title);

        JSONArray body = new JSONArray();
        for(MemberDto dto : items){

            JSONObject birth = new JSONObject();
            birth.put("v", dto.getBirthdate());

            JSONObject id = new JSONObject();
            id.put("v", dto.getAccountId());

            JSONArray row = new JSONArray();
            row.add(birth);
            row.add(id);

            JSONObject cell = new JSONObject();
            cell.put("c", row);
            body.add(cell);
        }
        data.put("rows", body);

        return data;
    }
}
