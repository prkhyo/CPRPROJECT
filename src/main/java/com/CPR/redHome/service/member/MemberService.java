package com.CPR.redHome.service.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.MemberJoinDto;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


public interface MemberService {

    //memberId로 멤버가져오기
    MemberDto selectMemberByMemberId(Long memberId);

    //아이디와 패스워드로 멤버가져오기
    MemberDto selectMemberByAccountIdAndPassword(String accountId,String Password) throws NoSuchAlgorithmException;

    //아이디 중복 확인
    String checkAccountIdDuplicate(String accountId);

    //회원가입시
    void joinMember(MemberJoinDto memberJoinDto) throws NoSuchAlgorithmException;

    //회원탈퇴
    void deleteMember(Long memberId);

    //회원 수정
    void updateMember(MemberDto memberDto);



}
