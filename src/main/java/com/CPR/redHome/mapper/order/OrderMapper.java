package com.CPR.redHome.mapper.order;

import com.CPR.redHome.dto.order.OrderedDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    //구매 내역을 가져오거나 조회가능 한지 구현

    //모든 구매내역 가져오기
    List<OrderedDto> selectAllOrdersByMemberId(Long memberId);

    //구매 목록 세기
    Long countOrders();



}
