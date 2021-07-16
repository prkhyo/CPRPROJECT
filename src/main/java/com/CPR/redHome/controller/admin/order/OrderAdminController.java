package com.CPR.redHome.controller.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.service.admin.order.OrderAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class OrderAdminController {

    private final OrderAdminService orderAdminService;

    // 주문 페이지 조회
    @GetMapping("/admin/order")
    public String adminOrder(Model model){
        List<OrderDto> orderDtos = orderAdminService.SelectAllOrders();
        model.addAttribute("orderDtos",orderDtos);
        return "admin/adminOrder";
    }

    // 주문 수정 페이지 조회
    @Transactional(readOnly = true)
    @GetMapping("/admin/order/update/{orderId}")
    public String adminOrdertModify(@PathVariable int orderId, Model model){
        model.addAttribute("orderDetails", orderAdminService.selectOrderByOrderId(orderId));
        return "/admin/order/orderUpdate";
    }

    // order 수정 내용 update
    @Transactional
    @PostMapping("/admin/order/update")
    public String adminOrderUpdate(OrderDto orderDto){
        orderAdminService.updateOrder(orderDto);
        return "redirect:/admin/order";
    }

    // 주문페이지 통계
    @GetMapping("/admin/order/chart")
    public String adminMemberChart() {
        return "admin/chart/orderChart";
    }
}
