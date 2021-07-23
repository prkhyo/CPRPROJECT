package com.CPR.redHome.controller.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.admin.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    // 전체 회원 조회
    @GetMapping("/admin/member")
    public String adminMember(@ModelAttribute Criteria criteria, Model model,
                              @RequestParam(defaultValue = "1") int currentPageNo){

        List<MemberDto> memberDtos = Collections.emptyList();
        int totalCnt = memberAdminService.countAll(criteria);
        System.out.println("totalCnt = " + totalCnt);

        criteria.setCurrentPageNo(currentPageNo);
        Pagination pagination = new Pagination(criteria, totalCnt, 5, 5);

        int firstRecordIndex = pagination.getFirstRecordIndex();

        if(totalCnt>0){
            memberDtos = memberAdminService.getMemberList(firstRecordIndex, criteria);
        }

        model.addAttribute("pageMaker", pagination);
        model.addAttribute("memberDtos", memberDtos);

        return "admin/adminMember";
    }

    // memberId로 회원 조회
    @Transactional(readOnly = true)
    @GetMapping("/admin/member/update/{memberId}")
    public String adminSelectMemberById(@PathVariable int memberId, Model model){
        model.addAttribute("memberDetails",memberAdminService.selectMemberById(memberId));
        return "admin/member/memberUpdate";
    }
    
    // member 수정 내용 update
    @Transactional
    @PostMapping("/admin/member/update")
    public String adminMemberUpdate(MemberDto memberDto){
        memberAdminService.updateMember(memberDto);
        return "redirect:/admin/member";
    }

    // 멤버페이지 통계
    @GetMapping("/admin/member/chart")
    public String adminMemberChart() {
        return "admin/chart/memberChart";
    }

}
