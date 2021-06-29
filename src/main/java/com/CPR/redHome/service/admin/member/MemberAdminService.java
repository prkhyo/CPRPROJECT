package com.CPR.redHome.service.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface MemberAdminService {

    // 전체 회원 조회
    List<MemberDto> selectAllMember();


    // 회원 통계
    // 나이대 별 회원 조회
    JSONObject selectMemberByAge();


}
