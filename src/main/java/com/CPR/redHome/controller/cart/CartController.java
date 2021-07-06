package com.CPR.redHome.controller.cart;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.service.cart.CartService;
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
    public String getCart(Long memberId, Model model) {

        // 임시 memberId
        memberId = 1L;

        model.addAttribute("carts", cartService.getCartList(memberId));

        return "carts/cart";
    }

    @PostMapping("/cart/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCart(@RequestBody List<OrderDto> OrderDto) {

        log.info("cartDto = " + OrderDto);
        cartService.cartDelete(OrderDto);
    }


    @RequestMapping("/cart/payment")
    public String getPayment(@RequestParam(value = "selectNo", required = false) List<String> ids, @ModelAttribute OrderDto orderDto, Model model) throws NullPointerException {

        log.info("payment Id = " + ids);

        model.addAttribute("point", cartService.findMemberId(Long.parseLong(ids.get(0))));
        model.addAttribute("orderDetail", cartService.getPayment(ids));

        return "carts/payment";
    }


    @PostMapping("/cart/payments")
    @ResponseStatus(HttpStatus.OK)
    public void test(@RequestBody List<OrderDto> orderDto) {
        OrderDto forPoint = new OrderDto();

        // 결제 내역에 추가
        cartService.insertOrders(orderDto);
        // 결제 완료로 인해 카트에서 제거
        cartService.cartDelete(orderDto);

        // 포인트 차감 및 제품 보유 수량 감소
        orderDto.forEach(orderDtos ->  cartService.deductedPoint(orderDtos));

        //적립 포인트
        Long memberId = orderDto.get(0).getMemberId();
        Integer point = orderDto.get(0).getAddPoint();

        forPoint.setAddPoint(point);
        forPoint.setMemberId(memberId);

        cartService.addPoint(forPoint);
    }
}
