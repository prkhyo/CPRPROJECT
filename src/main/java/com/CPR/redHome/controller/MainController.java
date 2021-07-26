package com.CPR.redHome.controller;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.member.SessionUser;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home(){

        return "redirect:/store";
    }

}



