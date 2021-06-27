package com.CPR.redHome.controller.admin;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String adminHome(){
        return "admin/adminHome";
    }

    @GetMapping("/adminMember")
    public String adminMember(Model model){
        List<MemberDto> memberDtos = adminService.selectAllMember();
        model.addAttribute("memberDtos", memberDtos);
        return "admin/adminMember";
    }

    @GetMapping("/adminProduct")
    public String adminProduct(){
        return "admin/adminProduct";
    }

    @GetMapping("/adminOrder")
    public String adminOrder(){
        return "admin/adminOrder";
    }

    @GetMapping("/adminQna")
    public String adminQna(){
        return "admin/adminQna";
    }

    @GetMapping("/adminMember/chart")
    public String adminMemberChart() {
        return "admin/testChart";
    }
    
    @GetMapping("/adminMember/adminProduct/enroll")
    public String adminProductEnroll() {
        return "admin/product/productRegist";
    }

    @GetMapping("/adminMember/adminProduct/edit")
    public String adminProductEdit() {
        return "admin/product/productEdit";
    }
}
