package com.CPR.redHome.service.cart;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.mapper.cart.CartMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    @DisplayName("cart id 값들을 결제페이지로 옮기기")
    public void testForEach() {

        //given
        List<String> ids = new ArrayList<>();
        ids.add("28");
        ids.add("29");

        System.out.println(cartMapper.getPayment(ids));
        //when
        cartMapper.getPayment(ids);


        // then

        Assertions.assertThat(ids.get(0)).isEqualTo("28");

    }

    @Test
    @DisplayName("memberId  조회 ")
    public void testFindMemberId() {

        List<String> ids = new ArrayList<>();
        ids.add("28");
        ids.add("29");
        System.out.println("asd  " + Long.parseLong(ids.get(0)));

        System.out.println("wowowowowo = " + cartMapper.findMemberId(Long.parseLong(ids.get(0))));


    }


}