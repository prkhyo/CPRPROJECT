package com.CPR.redHome.controller.admin.qna;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class QnaAdminController {

//    private final QnaAdminService qnaAdminService;

    // 문의 페이지 조회
    @GetMapping("/admin/qna")
    public String adminQna(){
        return "admin/adminQna";
    }
}
