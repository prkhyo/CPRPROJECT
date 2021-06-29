package com.CPR.redHome.controller.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.admin.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String adminHome(){
        return "admin/adminHome";
    }

    // 전체 회원 조회
    @GetMapping("/admin/member")
    public String adminMember(Model model){
        List<MemberDto> memberDtos = memberAdminService.selectAllMember();
        model.addAttribute("memberDtos", memberDtos);
        return "admin/adminMember";
    }

    // 멤버페이지 통계
    @GetMapping("admin/member/chart")
    public String adminMemberChart() {
        return "admin/Chart";
    }





    // =================== 아래 추후 분리 예정 ===================
    // 상품페이지 조회
    @GetMapping("/admin/product")
    public String adminProduct(){
        return "admin/adminProduct";
    }

    // 주문 페이지 조회
    @GetMapping("/admin/order")
    public String adminOrder(){
        return "admin/adminOrder";
    }

    // 문의 페이지 조회
    @GetMapping("/admin/qna")
    public String adminQna(){
        return "admin/adminQna";
    }



    // 상품 등록
    @GetMapping("/adminMember/adminProduct/enroll")
    public String adminProductEnroll() {
        return "admin/product/productRegist";
    }

    // 상품 수정
    @GetMapping("/adminMember/adminProduct/edit")
    public String adminProductEdit() {
        return "admin/product/productEdit";
    }
}
