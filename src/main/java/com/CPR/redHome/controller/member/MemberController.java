package com.CPR.redHome.controller.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;

    //로그인 창 띄우기
    @GetMapping("/login")
    public String login() {
        log.info("<==========로그인 창 입니다============>");
        return "member/login";
    }

    //로그인 입력값 전송
    @PostMapping("/login")
    public String loginMember(HttpSession request,Model model, @RequestParam("id") String memberId, @RequestParam("password") String memberPassword) {

        MemberDto member = memberService.selectMemberByAccountIdAndPassword(memberId,memberPassword);

        if(member==null){
            return "member/login";
        }else {
            request.setAttribute("member",member);
            request.setMaxInactiveInterval(600);
            return "redirect:main";
        }
        }


}
