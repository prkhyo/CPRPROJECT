package com.CPR.redHome.mapper.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface OrderAdminMapper {

    int selectTotalCnt(@Param("criteria") Criteria criteria);

    // 주문 조회
    List<OrderDto> selectOrders(@Param("firstRecordIndex") int firstRecordIndex, @Param("criteria") Criteria criteria);

    // orderId로 특정 주문 조회
    OrderDto selectOrderById(int orderId);

    // 수정된 주문 update
    void updateOrder(OrderDto orderDto);

    // order delete
    void deleteOrder(int orderId);

    // 월별 주문량 조회
    LinkedHashMap<String, Integer> selectOrderByMonth();

    // 주문 상태 별 주문 수
    LinkedHashMap<String, Integer> selectOrderByState();
}
