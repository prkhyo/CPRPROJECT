package com.CPR.redHome.controller.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.admin.order.OrderAdminService;
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
public class OrderAdminController {

    private final OrderAdminService orderAdminService;

    // 주문 페이지 조회
    @GetMapping("/admin/order")
    public String adminOrder(@ModelAttribute Criteria criteria, Model model,
                             @RequestParam(defaultValue = "1") int currentPageNo){

        List<OrderDto> orderDtos = Collections.emptyList();
        int totalCnt = orderAdminService.countAll(criteria);

        criteria.setCurrentPageNo(currentPageNo);
        Pagination pagination = new Pagination(criteria, totalCnt, 5, 5);

        int firstRecordIndex = pagination.getFirstRecordIndex();

        if(totalCnt>0){
            orderDtos = orderAdminService.getOrderList(firstRecordIndex, criteria);
        }

        model.addAttribute("pageMaker", pagination);
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

    // order delete
    @Transactional
    @GetMapping(value = "/admin/order/delete/{orderId}")
    public String adminOrderDelete(@PathVariable int orderId) {
        orderAdminService.deleteOrder(orderId);
        return "redirect:/admin/order";
    }

    // 주문페이지 통계
    @GetMapping("/admin/order/chart")
    public String adminMemberChart() {
        return "admin/chart/orderChart";
    }
}
