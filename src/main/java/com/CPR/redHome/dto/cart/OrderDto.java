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
public class OrderDto {

    private Long orderId;
    private Long memberId;
    private Long gradeId;

    private Integer currentPoint;
    private Integer usedPoint;
    private Integer discountRate;

    private String phoneNumber;
    private String address;
    private String memberName;
    private String orderRequest;

}
