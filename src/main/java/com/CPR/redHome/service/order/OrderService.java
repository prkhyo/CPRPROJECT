package com.CPR.redHome.service.order;


import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;

import java.util.List;


public interface OrderService {

   //구매목록 가져오기
   List<OrderedDto> selectAllOrdersById(Long memberId, Criteria criteria,int firstRecordIndex);

   Integer countOrderByMemberId(Long memberId);

   List<OrderedDto> selectReviewWriteList(Long memberId);

   OrderedDto selectOrderByOrderId(Long orderId);

   void reviewExist(Long orderId);

   void receivedCheck(Long orderId);


}
