package com.CPR.redHome.mapper.order;

import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.paging.Criteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    @DisplayName("모든 조회 내역을 불러올 수 있어요.")
    public void selectOrderTest() {

        //given
        Long memberId = 1L;
        Criteria criteria = new Criteria();
        criteria.setPageSize(4);
        criteria.setRecordsPerPage(2);
        criteria.setCurrentPageNo(2);
        int x = 2;
        //when
       List<OrderedDto> list= orderMapper.selectAllOrdersByMemberId(memberId,criteria,x);
        for (OrderedDto orderedDto : list) {
            System.out.println("orderedDto = " + orderedDto);
            
        }

        //then
        assertThat(orderMapper.selectAllOrdersByMemberId(memberId,criteria,x).size()).isEqualTo(2);


    }

    @Test
    @DisplayName("배송 완료이면서, 리뷰가 없는 주문 가져오기")
    public void test() {

        //given
        Long memberId = 1L;


        //when
        List<OrderedDto> orderList = orderMapper.selectReviewWriteList(memberId);

        //then
        assertThat(orderList.get(0).getOrderState()).isEqualTo("배송 완료");
        assertThat(orderList.get(0).isReviewExist()).isFalse();



    }

    @Test
    @DisplayName("orderId로 구매내역 가져오기")
    public void orderIdtest() {

        //given
        Long orderId = 1L;

        //when
        OrderedDto order = orderMapper.selectOrderByOrderId(1L);


        //then
        assertThat(order.getOrderId()).isEqualTo(1L);

    }

}