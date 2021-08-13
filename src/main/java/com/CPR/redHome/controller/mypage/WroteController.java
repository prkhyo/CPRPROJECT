package com.CPR.redHome.controller.mypage;


import com.CPR.redHome.dto.community.CommunityViewDto;
import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.question.QuestionSmallViewDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.review.ReviewSmallViewDto;
import com.CPR.redHome.service.community.CommunityService;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.service.review.ReviewService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WroteController {

    private final ReviewService reviewService;
    private final CommunityService communityService;
    private final QuestionService questionService;


    //작성한 리뷰 리스트
    @GetMapping("/mypage/reviewed/list")
    public String selectReviewService(@Login MemberDto loginMember, Model model) {

        Long memberId = loginMember.getMemberId();
        List<ReviewSmallViewDto> reviewList = reviewService.selectReviewSmallView(memberId);

        model.addAttribute("reviewList",reviewList);

        return "/mypage/reviewed_list";
    }

    //작성한 커뮤니티 리스트 가져오기
    @GetMapping("/mypage/community/list")
    public String selectCommuinityByMemberId(@Login MemberDto loginMember,Model model){
        Long memberId = loginMember.getMemberId();
        List<CommunityViewDto> communityList = communityService.selectCommunityByMemberId(memberId);

        model.addAttribute("communityList",communityList);
        return "/mypage/community_list";
    }

    //작성한 문의 글 가져오기
    @GetMapping("/mypage/question/list")
    public String selectQuestionByMemberId(@Login MemberDto loginMember,Model model){
        Long memberId = loginMember.getMemberId();
        List<QuestionSmallViewDto> questionList = questionService.selectQuestionByMemberId(memberId);

        model.addAttribute("questionList",questionList);

        return "/mypage/question";
    }


}
