package com.CPR.redHome.controller.review;


import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.dto.review.ReviewDto;
import com.CPR.redHome.service.order.OrderService;
import com.CPR.redHome.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;

    //[참고]리뷰 작성 가능한 목록은 orderController에서 구현

    //리뷰쓰기 클릭시
    @GetMapping("/mypage/review/write/{orderId}")
    public String writeReview(@PathVariable Long orderId, Model model){
       OrderedDto order =  orderService.selectOrderByOrderId(orderId);
        model.addAttribute("order",order);


        return "review/review_form";
    }



    @PostMapping("/mypage/review/submit/{orderId}")
    public String insertReview(@RequestParam("imgFile") MultipartFile file,
                               ReviewDto reviewDto,
                               @PathVariable Long orderId,
                               HttpServletRequest request){
        System.out.println("file = " + file);
        try {
            reviewService.insertReview(reviewDto, file, request);
            orderService.reviewExist(orderId);
            System.out.println("==============================controller에서 잘 넘어갔다===================================");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("==============================문제가 일어났따===================================");
        }


        return "redirect:/mypage/review/list";


    }
}
