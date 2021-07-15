package com.CPR.redHome.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainAdminController {
    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String adminHome(){
        return "admin/adminHome";
    }

    // 관리자 통계 페이지
    @GetMapping("/admin/statistics")
    public String adminStatisticsHome(){
        return "admin/chart/chartMain";
    }
}
