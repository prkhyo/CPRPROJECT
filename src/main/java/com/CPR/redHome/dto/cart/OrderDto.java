package com.CPR.redHome.dto.cart;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("orders")
public class OrderDto extends CartDto {

    private String orderId;
    private Long memberId;
    private Long cartId;
    private Long productId;
    private Long gradeId;

    private Integer currentPoint;
    private Integer usedPoint;
    private Integer price;
    private Integer accumulationRate;
    private Integer deliveryCharge;
    private Integer totalPoint;

    private String phoneNumber;
    private String accountId;
    private String address;
    private String receiver;
    private String orderRequest;
    private String email;

}
