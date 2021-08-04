package com.CPR.redHome.controller.order;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.order.OrderService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //구매목록
    @GetMapping("/mypage/orders")
    public String neOrderList(@Login MemberDto loginMember, Model model, Criteria criteria){

        int cnt = orderService.countOrderByMemberId(loginMember.getMemberId());

        Pagination pagination = new Pagination(criteria, cnt, 5, 5);

        int firstRecordIndex = pagination.getFirstRecordIndex();


        Long memberId = loginMember.getMemberId();
        System.out.println("================"+memberId+"==================");;
        List<OrderedDto> orderList =
               orderService.selectAllOrdersById(memberId,criteria,firstRecordIndex);


       model.addAttribute("orderList",orderList);
       model.addAttribute("pageMaker", pagination);


        return "order/order_list";
    }


    //리뷰 작성 가능한 목록
    @GetMapping("/mypage/review/list")
    public String reviewList(@Login MemberDto loginMember, Model model){

        List<OrderedDto> orderReviewList= orderService.selectReviewWriteList(loginMember.getMemberId());

        model.addAttribute("orderReviewList",orderReviewList);

    return "review/review_list";
    }
}
