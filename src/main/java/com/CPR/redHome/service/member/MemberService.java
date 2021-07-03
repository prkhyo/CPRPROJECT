package com.CPR.redHome.service.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    //아이디와 패스워드로 멤버가져오기
    MemberDto selectMemberByAccountIdAndPassword(String accountId,String Password);

    //회원가입시
    void joinMember(MemberDto memberDto);


}
