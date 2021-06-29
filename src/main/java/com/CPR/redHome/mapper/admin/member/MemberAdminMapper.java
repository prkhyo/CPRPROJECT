package com.CPR.redHome.mapper.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberAdminMapper {

    // 전체 멤버 조회
    List<MemberDto> selectAllMember();

    // 나이대 별 멤버 수 조회
    LinkedHashMap<String, Integer> selectMemberByAge();

}
