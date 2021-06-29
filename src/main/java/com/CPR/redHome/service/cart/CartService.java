package com.CPR.redHome.service.cart;


import com.CPR.redHome.dto.cart.CartDto;

import java.util.List;

public interface CartService {

    // 장바구니 조회
    List<CartDto> getCartList(Long memberId);

    //장바구니 선택 삭제

    int cartDelete(Long id);

}
