package com.CPR.redHome.mapper.cart;

import com.CPR.redHome.dto.cart.CartDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    // 장바구니 조회
    List<CartDto> getCartList(Long memberId);

    // 장바구니 삭제
    int cartDelete(Long id);


}
