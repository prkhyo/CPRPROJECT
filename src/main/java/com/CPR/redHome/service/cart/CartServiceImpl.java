package com.CPR.redHome.service.cart;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.mapper.cart.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

    @Override
    public List<CartDto> getCartList(Long memberId) {

        log.info("회원 번호 "+ memberId+"님의 장바구니를 조회 합니다. " );

        return cartMapper.getCartList(memberId);
    }

    @Override
    public int cartDelete(List<OrderDto> orderDto) {

        log.info("삭제되는 cart id = " + orderDto);
        return cartMapper.cartDelete(orderDto);
    }

    @Override
    public List<OrderDto> getPayment(List<String> ids) {

        log.info("회원번호 1 의 제품번호 : " + ids + "결제 창 요청 중...");

        return cartMapper.getPayment(ids);
    }

    @Override
    public int findMemberId(Long memberId) {

        return cartMapper.findMemberId(memberId);
    }

    @Override
    public int insertOrders(List<OrderDto> orderDto) {

        log.info("::::::::  결제 후 내역 추가 ::::::::");

        return cartMapper.insertOrders(orderDto);
    }


    @Override
    public int deductedPoint(OrderDto orderDto) {

        log.info("::::::: 포인트 차감 및 제품 보유 수량 감소 합니당  :::::");
        return cartMapper.deductedPoint(orderDto);
    }

    @Override
    public int addPoint(OrderDto orderDto) {
        return cartMapper.addPoint(orderDto);
    }

}

