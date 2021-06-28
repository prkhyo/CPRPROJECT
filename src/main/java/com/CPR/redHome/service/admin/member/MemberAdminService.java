package com.CPR.redHome.service.admin.member;

import com.CPR.redHome.dto.member.MemberDto;

import java.util.List;
import java.util.Map;

public interface MemberAdminService {

    // 전체 회원 조회
    List<MemberDto> selectAllMember();


    // 회원 통계
    // 나이대 별 회원 조회
    Map<String, Integer> selectMemberByAge();
}
