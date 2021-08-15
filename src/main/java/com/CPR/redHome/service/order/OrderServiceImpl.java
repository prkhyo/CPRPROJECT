package com.CPR.redHome.service.order;


import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.mapper.order.OrderMapper;
import com.CPR.redHome.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderMapper orderMapper;


    @Override
    public List<OrderedDto> selectAllOrdersById(Long memberId, Criteria criteria,int firstRecordIndex) {


        return orderMapper.selectAllOrdersByMemberId(memberId,criteria,firstRecordIndex);
    }

    @Override
    public Integer countOrderByMemberId(Long memberId) {
        return orderMapper.countOrders(memberId);
    }

    @Override
    public List<OrderedDto> selectReviewWriteList(Long memberId) {
        return orderMapper.selectReviewWriteList(memberId);
    }

    @Override
    public OrderedDto selectOrderByOrderId(Long orderId) {
        return orderMapper.selectOrderByOrderId(orderId);
    }

    @Override
    public void reviewExist(Long orderId) {
        orderMapper.reviewExist(orderId);
    }

    @Override
    public void orderStateChange(Long orderId) {
        orderMapper.orderStateChange(orderId);
    }
}
