package com.CPR.redHome.controller.cart;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.service.cart.CartService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class CartController {

    private final CartService cartService;


    @GetMapping("/cart")
    public String getCart(@Login MemberDto loginMember, Model model) {

        model.addAttribute("carts", cartService.getCartList(loginMember.getMemberId()));

        return "carts/cart";
    }

    @PostMapping("/cart/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCart(@RequestBody List<OrderDto> OrderDto) {

        cartService.cartDelete(OrderDto);
    }


    @RequestMapping("/cart/payment")
    public String getPayment(@RequestParam(value = "selectNo", required = false) List<String> ids, @ModelAttribute OrderDto orderDto, Model model) throws NullPointerException {

        model.addAttribute("point", cartService.findMemberId(Long.parseLong(ids.get(0))));
        model.addAttribute("orderDetail", cartService.getPayment(ids));

        return "carts/payment";
    }


    @PostMapping("/cart/payments")
    @ResponseStatus(HttpStatus.OK)
    public void test(@Login MemberDto loginMember, @RequestBody List<OrderDto> orderDto) {

        // 결제 내역에 추가
        cartService.insertOrders(orderDto);
        // 결제 완료로 인해 카트에서 제거
        cartService.cartDelete(orderDto);

        // 포인트 차감 및 제품 보유 수량 감소
        orderDto.forEach(orderDtos ->  cartService.deductedPoint(orderDtos));

        // 포인트 적립
        OrderDto forPoint = new OrderDto(); // 새로운 생성자 생성
        Integer point = orderDto.get(0).getAddPoint(); // 적립되는 포인트 꺼내기어 넣기

        forPoint.setAddPoint(point);
        forPoint.setMemberId(loginMember.getMemberId());

        cartService.addPoint(forPoint);
    }
}
