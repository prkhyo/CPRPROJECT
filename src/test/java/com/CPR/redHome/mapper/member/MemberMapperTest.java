package com.CPR.redHome.mapper.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("아이디로 찾는다")
    public void loginTest() {

        //given
        MemberDto member = new MemberDto();
        member.setAccountId("user1");
        member.setMemberPassword("1234");



        //when
        MemberDto result = memberMapper.selectMemberByAccountId(member.getAccountId());

        //then
        Assertions.assertThat(result.getMemberPassword()).isEqualTo(member.getMemberPassword());


    }
    @Test
    @DisplayName("회원가입을 가능하게 한다.")
    public void joinMemberTest() {

        //given
        MemberDto memberDto = new MemberDto();
        memberDto.setAccountId("뚱땡");
        memberDto.setMemberPassword("1234");
        memberDto.setName("한뚱땡");
        memberDto.setTelephone("010-1234-1234");
        memberDto.setEmail("fatShort@gmail.fat.com");
        memberDto.setAddress("뚱떙월드");
        memberDto.setBirthdate("1991-11-27");


        //when
        memberMapper.joinMember(memberDto);

        //then
        Assertions.assertThat(memberMapper.selectMemberByAccountId("뚱땡")).isNotNull();


    }

    @Test
    @DisplayName("회원목록을 가져온다")
    public void selectAllTest() {

        //given



        //when
        List<MemberDto> members = memberMapper.selectAllMember();

        //then
        for (MemberDto member : members) {
            System.out.println("member = " + member);
        }

    }

    @Test
    @DisplayName("회원 탈퇴 ")
    public void deleteMemberTest() {

        //given

        long memberId = 12;

        //when
        memberMapper.deleteMember(memberId);

        //then
        Assertions.assertThat(memberMapper.selectMemberByMemberId(memberId)).isNull();

    }

    @Test
    @DisplayName("회원정보 수정")
    public void updateTest() {

        //given
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(12L);
        memberDto.setAddress("떙떙나라");
        memberDto.setMemberPassword("1234");
        memberDto.setName("뚱떙떙");
        memberDto.setTelephone("010-1212-7878");
        memberDto.setEmail("fattyWorld@fakeMail");




        //when
        memberMapper.updateMember(memberDto);

        //then
        Assertions.assertThat(memberMapper.selectMemberByMemberId(12L).getName()).isEqualTo("뚱떙떙");


    }

}