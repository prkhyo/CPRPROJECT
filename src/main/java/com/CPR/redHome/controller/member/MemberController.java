package com.CPR.redHome.controller.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.MemberJoinDto;
import com.CPR.redHome.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String loginMember(HttpSession request, Model model, @RequestParam("id") String memberId, @RequestParam("password") String memberPassword) {

        MemberDto member = memberService.selectMemberByAccountIdAndPassword(memberId, memberPassword);

        if (member == null) {
            return "member/login";
        } else {
            request.setAttribute("member", member);
            request.setMaxInactiveInterval(600);
            return "redirect:main";
        }
    }

    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member",new MemberJoinDto());

        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(@Validated @ModelAttribute("member") MemberJoinDto member,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "member/join";
        }

        //성공시
        MemberDto memberDto = new MemberDto();
        memberDto.setAccountId(member.getAccountId());
        memberDto.setMemberPassword(member.getMemberPassword());
        memberDto.setName(member.getName());
        memberDto.setTelephone(member.getTelephone());
        memberDto.setEmail(member.getEmail());
        memberDto.setAddress(member.getAddress());
        memberDto.setBirthdate(member.getBirthdate());

        memberService.

        return "member/login";
    }

}