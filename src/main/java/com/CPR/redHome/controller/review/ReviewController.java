package com.CPR.redHome.controller.review;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.review.ReviewService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review/help/increase")
    public String reviewHelpIncrease(@RequestParam Long productId, @RequestParam Long reviewId, @Login MemberDto loginMember,
                                   @RequestParam(defaultValue = "1") int questionCurrentPageNo, @RequestParam(defaultValue = "1") int reviewCurrentPageNo){

        reviewService.updateHelpCntIncrease(reviewId);
        System.out.println("증가");
        reviewService.insertReviewHelp(reviewId, loginMember.getMemberId());
        System.out.println("추가완료");

        return "redirect:/product/detail?productId="+productId+"&questionCurrentPageNo="+questionCurrentPageNo+"&reviewCurrentPageNo="+reviewCurrentPageNo;
    }


    @GetMapping("/review/help/decrease")
    public String reviewHelpDecrease(@RequestParam Long productId, @RequestParam Long reviewId, @Login MemberDto loginMember,
                                   @RequestParam(defaultValue = "1") int questionCurrentPageNo, @RequestParam(defaultValue = "1") int reviewCurrentPageNo){

        reviewService.updateHelpCntDecrease(reviewId);
        System.out.println("감소");
        reviewService.deleteReviewHelp(reviewId, loginMember.getMemberId());
        System.out.println("삭제완ㄽ");

        return "redirect:/product/detail?productId="+productId+"&questionCurrentPageNo="+questionCurrentPageNo+"&reviewCurrentPageNo="+reviewCurrentPageNo;

    }



}
