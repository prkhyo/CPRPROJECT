package com.CPR.redHome.mapper.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberAdminMapper {

    // 전체 멤버 조회
    List<MemberDto> selectAllMembers();

    // memberId로 회원 조회
    MemberDto selectMemberById(int memberId);

    // 수정 member update
    void updateMember(MemberDto memberDto);

    // 나이대 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByAge();

    // 지역 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByLocation();

    // 등급 별 회원 수 조회
    LinkedHashMap<String, Integer> selectMemberByGrade();
}
