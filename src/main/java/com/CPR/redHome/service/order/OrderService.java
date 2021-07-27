package com.CPR.redHome.service.order;


import com.CPR.redHome.dto.order.OrderedDto;

import java.util.List;


public interface OrderService {

   //구매목록 가져오기
   List<OrderedDto> selectAllOrdersById(Long memberId);



}
