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
        List<CartDto> cartDtos = new ArrayList<>();

        // 임시 memberId
        memberId = 1L;

        cartDtos = cartService.getCartList(memberId);


        model.addAttribute("carts", cartDtos);

        return "carts/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteCart(@RequestParam(value = "selectNo", required = false) List<String> ids, @RequestParam(value = "memberId", required = false) Long memberId) {

        log.info("cart Id = " + ids);
        if (ids != null) {
            for (String idStr : ids) {
                Long id = Long.parseLong(idStr);
                cartService.cartDelete(id);
            }

        } else {
            return "redirect:/cart/" + memberId;
        }

        return "redirect:/cart/" + memberId;
    }

    @RequestMapping("/cart/payment")
    public String getPayment(@RequestParam(value = "selectNo", required = false) List<String> ids, @ModelAttribute OrderDto orderDto, Model model) throws Exception {

        log.info("payment Id = " + ids);
        log.info("memberId = " + cartService.findMemberId(Long.parseLong(ids.get(0))));

        model.addAttribute("point", cartService.findMemberId(Long.parseLong(ids.get(0))));
        model.addAttribute("orderDetail", cartService.getPayment(ids));

        return "carts/payment";
    }

    /*    @PostMapping("/cart/test")
        public void test(@RequestBody OrderDto orderDto) {
            log.info("여기로 전달 되려나 테스트   " + orderDto);
            System.out.println("imp_uid = " + orderDto);

        }*/
    @PostMapping("/cart/test")
    @ResponseStatus(HttpStatus.OK)
    public void test(@RequestBody List<OrderDto> orderDto) {

        log.info("여기로 전달 되려나 테스트   " + orderDto);

        cartService.insertOrders(orderDto);

    }
}
