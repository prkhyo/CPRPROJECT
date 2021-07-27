package com.CPR.redHome.service.order;


import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.mapper.order.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderMapper orderMapper;

    @Override
    public List<OrderedDto> selectAllOrdersById(Long memberId) {
        return orderMapper.selectAllOrdersByMemberId(memberId);
    }
}
