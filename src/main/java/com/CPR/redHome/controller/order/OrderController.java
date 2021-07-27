package com.CPR.redHome.controller.order;


import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.service.order.OrderService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @GetMapping("/order")
    public String orderList(@Login MemberDto loginMember, Model model){

       List<OrderedDto> orderList =  orderService.selectAllOrdersById(loginMember.getMemberId());
       model.addAttribute("orderList",orderList);

        return "order/order_list";
    }
}
