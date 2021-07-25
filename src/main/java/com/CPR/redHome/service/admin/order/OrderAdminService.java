package com.CPR.redHome.service.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.paging.Criteria;
import org.json.simple.JSONObject;

import java.util.List;

public interface OrderAdminService {

    int countAll(Criteria criteria);

    // 전체 주문 조회
    List<OrderDto> getOrderList(int firstRecordIndex, Criteria criteria);

    // orderId로 주문 조회
    OrderDto selectOrderByOrderId(int orderId);

    // 수정한 order update
    void updateOrder(OrderDto orderDto);

    // order delete
    void deleteOrder(int orderId);

    // 월별 주문량 조회
    JSONObject selectOrderByMonth();

    // 상태별 주문 조회
    JSONObject selectOrderByState();
}
