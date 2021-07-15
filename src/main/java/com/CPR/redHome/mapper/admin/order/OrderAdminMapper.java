package com.CPR.redHome.mapper.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderAdminMapper {

    // 전체 주문 조회
    List<OrderDto> selectAllOrders();

    // orderId로 특정 주문 조회
    OrderDto selectOrderById(int orderId);

    // 수정된 주문 update
    void updateOrder(OrderDto orderDto);
}
