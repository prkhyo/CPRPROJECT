package com.CPR.redHome.controller.review;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.review.ReviewService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.dto.review.ReviewDto;
import com.CPR.redHome.service.order.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;

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


    //정연 작성
    // [참고]리뷰 작성 가능한 목록은 orderController에서 구현


    //리뷰쓰기버튼으로 작성 폼 입장
    @GetMapping("/mypage/review/write/{orderId}")
    public String writeReview(@PathVariable Long orderId, Model model){
       OrderedDto order =  orderService.selectOrderByOrderId(orderId);
        model.addAttribute("order",order);


        return "review/review_form";
    }


    //리뷰 제출
    @PostMapping("/mypage/review/submit/{orderId}")
    public String insertReview(@RequestParam("imgFile") MultipartFile file,
                               ReviewDto reviewDto,
                               @PathVariable Long orderId,
                               HttpServletRequest request){

        //뒤로 가기 버튼으로 되돌어 갔을 시 제출 막기
            if(orderService.selectOrderByOrderId(orderId).getReviewExist()==true){
                return "error/submit_error";
            }

        try {
            reviewService.insertReview(reviewDto, file, request);
            orderService.reviewExist(orderId);

        }catch (Exception e){
            e.printStackTrace();
        }


        return "redirect:/mypage/review/list";


    }
}