package com.CPR.redHome.dto.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Alias("OrderedDto")
@ToString
public class OrderedDto {

    private Long orderId;
    private String orderNumber;
    private String productId;
    private String orderReceiver;
    private LocalDateTime orderDate;
    private Integer orderDeliveryCharge;
    private Long orderPointUsed;
    private String orderRequest;
    private String orderAddress;
    private String orderState;
    private Integer orderProductQuantity;

    private Integer orderUnitPrice;
    private String productMainImage;
    private String productTitle;


}
