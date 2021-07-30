package com.CPR.redHome.service.admin.order;

import com.CPR.redHome.dto.cart.OrderDto;
import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.mapper.admin.order.OrderAdminMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.service.admin.util.MakeJsonForChartServcie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderAdminServiceImpl implements OrderAdminService{

    private final OrderAdminMapper orderAdminMapper;
    private final MakeJsonForChartServcie makeJsonForChartServcie;

    @Override
    @Transactional(readOnly = true)
    public int countAll(Criteria criteria) {
        int cnt = orderAdminMapper.selectTotalCnt(criteria);
        return cnt;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrderList(int firstRecordIndex, Criteria criteria) {
        List<OrderDto> orderDtos = orderAdminMapper.selectOrders(firstRecordIndex, criteria);
        return orderDtos;
    }

    // 선택 주문 조회
    @Override
    @Transactional(readOnly = true)
    public OrderDto selectOrderByOrderId(int orderId) {
        return orderAdminMapper.selectOrderById(orderId);
    }

    // 수정 주문 update
    @Override
    @Transactional
    public void updateOrder(OrderDto orderDto) {
        orderAdminMapper.updateOrder(orderDto);
    }

    // order delete
    @Override
    @Transactional
    public void deleteOrder(int orderId) {
        orderAdminMapper.deleteOrder(orderId);
    }

    // 월별 주문량 조회
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectOrderByMonth() {
        // 월별 주문수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = orderAdminMapper.selectOrderByMonth();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "월", "주문수");

        return data;
    }

    // 상태 별 주문 수.
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectOrderByState() {
        // 상태별 주문수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = orderAdminMapper.selectOrderByState();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "주문상태", "주문수");

        return data;
    }
}
