package com.CPR.redHome.mapper.member;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.MemberJoinDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    //로그인 (accountId로 멤버가져오기)
    MemberDto selectMemberByAccountId(@Param("accountId") String accountId);

    //회원 정보 가져오기(memberId)
    MemberDto selectMemberByMemberId(Long memberId);

    //회원가입(멤버 등록)
    void joinMember(MemberJoinDto memberJoinDto);

    //멤버리스트 가져오기
    List<MemberDto> selectAllMember();

    //멤버 삭제
    void deleteMember(Long memberId);

    //멤버 수정
    void updateMember(MemberDto memberDto);

    //아이디 중복 확인
    Long checkAccountIdDuplicate(String accountId);
}
