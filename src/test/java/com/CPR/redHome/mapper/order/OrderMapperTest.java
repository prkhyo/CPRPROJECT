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
        Criteria criteria = null;
        criteria.setPageSize(4);
        criteria.setRecordsPerPage(2);
        criteria.setCurrentPageNo(1);
        int x = 1;
        //when
       List<OrderedDto> list= orderMapper.selectAllOrdersByMemberId(memberId,criteria,x);
        System.out.println(list);

        //then
        Assertions.assertThat(orderMapper.selectAllOrdersByMemberId(memberId,criteria,x).size()).isEqualTo(2);


    }

}