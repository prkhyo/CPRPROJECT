package com.CPR.redHome.controller.review;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.review.ReviewService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Controller
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review/help/increase")
    @ResponseBody
    public void reviewHelpIncrease(@RequestBody HashMap<String, Long> map, @Login MemberDto loginMember){
        System.out.println(map.get("reviewId"));
        reviewService.updateHelpCntIncrease(map.get("reviewId"));
        System.out.println("증가");
        reviewService.insertReviewHelp(map.get("reviewId"), loginMember.getMemberId());
        System.out.println("추가완료");

    }


    @PostMapping("/review/help/decrease")
    @ResponseBody
    public void reviewHelpDecrease(@RequestBody HashMap<String, Long> map, @Login MemberDto loginMember){
        System.out.println(map.get("reviewId"));
        reviewService.updateHelpCntDecrease(map.get("reviewId"));
        System.out.println("감소");
        reviewService.deleteReviewHelp(map.get("reviewId"), loginMember.getMemberId());
        System.out.println("삭제완료");


    }

}