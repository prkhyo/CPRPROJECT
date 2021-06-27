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

    private Long orderId;
    private Long memberId;
    private Long gradeId;

    private Integer currentPoint;
    private Integer usedPoint;
    private Integer accumulationRate;

    private String phoneNumber;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String receiver;
    private String orderRequest;

}
