package com.CPR.redHome.dto.cart;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("cartDto")
@ToString
public class CartDto {

    private Long cartId;
    private Long memberId;
    private Long productId;

// 상품 정보
    private String title;
    private String img;

    private Integer price;
    private Integer quantity;
    private Integer deliveryCharge;

// 회원 정보
    private String name;

}
