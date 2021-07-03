package com.CPR.redHome.service.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public MemberDto selectMemberByAccountIdAndPassword(String accountId, String password) {
        MemberDto member = memberMapper.selectMemberByAccountId(accountId);

        if (member == null) {
            log.info("xxxxxxxxxxxxxxxx  아이디가 존재하지 않습니다  xxxxxxxxxxxxxx");

        } else if (!(member.getMemberPassword().equals(password))) {
            log.info("==============비밀번호가 일치하지 않습니다 ================");
            member = null;

        } else {
            log.info("ooooooooooooooo  로그인 성공! oooooooooooooooooooo");
        }
        return member;
    }




    @Override
    public void joinMember(MemberDto memberDto) {
        memberMapper.joinMember(memberDto);
    }


}
