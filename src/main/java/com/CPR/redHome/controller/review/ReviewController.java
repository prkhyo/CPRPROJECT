package com.CPR.redHome.controller.review;


import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.service.order.OrderService;
import com.CPR.redHome.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;

    //[참고]리뷰 작성 가능한 목록은 orderController에서 구현

    //리뷰쓰기 클릭시
    @GetMapping("/review/write/{orderId}")
    public String writeReview(@PathVariable Long orderId, Model model){
       OrderedDto order =  orderService.selectOrderByOrderId(orderId);
        model.addAttribute("order",order);


        return "review/review_form";
    }
}
