package com.CPR.redHome.service.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.MemberJoinDto;
import com.CPR.redHome.encrypt.EncryptPassword;
import com.CPR.redHome.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final EncryptPassword encryptPassword;


    @Override
    public MemberDto selectMemberByMemberId(Long memberId) {
        return  memberMapper.selectMemberByMemberId(memberId);
    }


    @Override
    public MemberDto selectMemberByAccountIdAndPassword(String accountId, String password) throws NoSuchAlgorithmException {
        String encryptedPassword = encryptPassword.encrypt(password);
        MemberDto member = memberMapper.selectMemberByAccountId(accountId);

        if (member == null) {
            log.info("xxxxxxxxxxxxxxxx  아이디가 존재하지 않습니다  xxxxxxxxxxxxxx");

        } else if (!(member.getMemberPassword().equals(encryptedPassword))) {
            log.info("==============비밀번호가 일치하지 않습니다 ================");
            member = null;

        } else {
            log.info("ooooooooooooooo  로그인 성공! oooooooooooooooooooo");
        }
        return member;
    }


    @Override
    public String checkAccountIdDuplicate(String accountId) {
        Long memberId = memberMapper.checkAccountIdDuplicate(accountId);

        if(memberId==null)
            return "true";

        return "false";
    }

    @Override
    public void joinMember(MemberJoinDto memberJoinDto) throws NoSuchAlgorithmException{
        String encryptedPassword = encryptPassword.encrypt(memberJoinDto.getMemberPassword());
        memberJoinDto.setMemberPassword(encryptedPassword);

        memberMapper.joinMember(memberJoinDto);
    }


    @Override
    public void deleteMember(Long memberId) {
        memberMapper.deleteMember(memberId);
    }

    @Override
    public void updateMember(MemberDto memberDto) {

        memberMapper.updateMember(memberDto);
    }


}
