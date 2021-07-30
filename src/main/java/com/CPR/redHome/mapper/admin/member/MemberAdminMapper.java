package com.CPR.redHome.mapper.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberAdminMapper {
    // 오늘
    int selectTotalCnt(@RequestParam Criteria criteria);

    List<MemberDto> selectMembers(@RequestParam int firstRecordIndex, @RequestParam Criteria criteria);

    // memberId로 회원 조회
    MemberDto selectMemberById(Long memberId);

    // 수정 member update
    void updateMember(MemberDto memberDto);

    // member delete
    void deleteMember(Long memberId);

    // 판매자 신청
    void updateMemberRole(String accountId);

    // 판매 권한 승인
    void permitNewSeller(Long memberId);

    // 판매 권한 반려
    void rejectNewSeller(Long memberId);


    // 나이대 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByAge();

    // 지역 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByLocation();

    // 등급 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByGrade();
}
