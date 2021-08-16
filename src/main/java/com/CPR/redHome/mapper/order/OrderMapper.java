package com.CPR.redHome.mapper.order;

import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface OrderMapper {
    //구매 내역을 가져오거나 조회가능 한지 구현


    //orderId로 구매내역가져오기
    OrderedDto selectOrderByOrderId(Long orderId);

    //모든 구매내역 가져오기
    List<OrderedDto> selectAllOrdersByMemberId(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria, @Param("firstRecordIndex") int firstRecordIndex);

    //구매 목록 세기
    Integer countOrders(Long memberId);

    //배송 완료이면서, 리뷰가 없는 주문 가져오기
    List<OrderedDto> selectReviewWriteList(Long memberId);

    //review 가 있다고 바꿔주기
    void reviewExist(Long orderId);

    //물건 수령 확정
    void receivedCheck(Long orderId);

    //배송중으로 전환하기.
    void orderStateChange(Long orderId);

}


