package com.CPR.redHome.controller.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.MemberJoinDto;
import com.CPR.redHome.dto.member.MemberLoginDto;
import com.CPR.redHome.dto.member.SessionUser;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;


    //로그인 창 띄우기
    @GetMapping("/login")
    public String login(Model model) {

        log.info("<==========로그인 창 입니다============>");


        model.addAttribute("memberLoginDto", new MemberLoginDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("memberLoginDto") MemberLoginDto loginData, BindingResult bindingResult, HttpServletRequest request) {

        log.info("login!!{}", loginData.getAccountId());

        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        MemberDto loginMember = memberService.selectMemberByAccountIdAndPassword
                (loginData.getAccountId(), loginData.getMemberPassword());
        log.info("login={}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/login";
        }
        //성공처리

        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionUser.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }


    //로그아웃시 세션 삭제
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //세션 삭제
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }


    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member", new MemberJoinDto());

        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(@Validated @ModelAttribute("member") MemberJoinDto member,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
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

        memberService.joinMember(memberDto);

        return "member/login";
    }

}