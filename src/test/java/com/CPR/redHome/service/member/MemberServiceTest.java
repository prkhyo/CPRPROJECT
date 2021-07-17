package com.CPR.redHome.service.member;

import com.CPR.redHome.dto.member.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("아이디에 맞는 비밀번호 찾기")
    public void selectMemberByAccountIdAndPasswordTest() throws NoSuchAlgorithmException {

        //given
        String accountId1 = "꼬미";
        String passWord = "1234";

        String accountId2 = "user1";
        String passWord2 = "12345";

        String accountId3 = "user1";
        String passWord3 = "1234";


        //when
        MemberDto member1 = memberService.selectMemberByAccountIdAndPassword(accountId1, passWord);
        MemberDto member2 = memberService.selectMemberByAccountIdAndPassword(accountId2, passWord2);
        MemberDto member3 = memberService.selectMemberByAccountIdAndPassword(accountId3, passWord3);

        //then
        System.out.println("member1 = " + member1);
        System.out.println("member2 = " + member2);
        System.out.println("member3 = " + member3);

    }


    @Test
    @DisplayName("중복된 아이디가 있으면 false반환")
    public void test() {

        //given
/*        String accountIdExist = "user1";
        String accountId = "newId";

        //when
        boolean exist = memberService.checkAccountIdDuplicate(accountIdExist);
        boolean noneExist = memberService.checkAccountIdDuplicate(accountId);


        //then
        Assertions.assertThat(exist).isFalse();
        Assertions.assertThat(noneExist).isTrue();*/

    }

}