package com.CPR.redHome.mapper.cart;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.cart.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CartMapper {

    // 장바구니 조회
    List<CartDto> getCartList(Long memberId);

    // 장바구니 삭제
    int cartDelete(List<OrderDto> orderDto);

    // 장바구니에서 결제창 요청 시
    List<OrderDto> getPayment(List<String> ids);

    //memberId 조회
    int findMemberId(Long id);

    // 주문 결제 후 주문내역 추가
    int insertOrders(List<OrderDto> orderDto);

    // 포인트 차감
    int deductedPoint(OrderDto orderDto);

    // 포인트누적
    int addPoint(OrderDto orderDto);


}
