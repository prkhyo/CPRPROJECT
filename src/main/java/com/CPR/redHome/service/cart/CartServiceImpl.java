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

        return cartMapper.getCartList(memberId);
    }

    @Override
    public int cartDelete(List<OrderDto> orderDto) {

        return cartMapper.cartDelete(orderDto);
    }

    @Override
    public List<OrderDto> getPayment(List<String> ids) {

        return cartMapper.getPayment(ids);
    }

    @Override
    public int findMemberId(Long memberId) {

        return cartMapper.findMemberId(memberId);
    }

    @Override
    public int insertOrders(List<OrderDto> orderDto) {

        return cartMapper.insertOrders(orderDto);
    }


    @Override
    public int deductedPoint(OrderDto orderDto) {

        return cartMapper.deductedPoint(orderDto);
    }

    @Override
    public int addPoint(OrderDto orderDto) {
        return cartMapper.addPoint(orderDto);
    }

}

