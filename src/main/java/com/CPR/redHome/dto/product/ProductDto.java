package com.CPR.redHome.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Alias("ProductDto")
public class ProductDto {

    private Long productId;

    private Long memberId;

    private String productTitle;

    private LocalDateTime createdDate;

    private Integer productPrice;

    private Integer productDeliveryCharge;

    private Integer productQuantity;

    private Integer productCategoryId; //join

    private Integer productThemeId; //join

    private String productDescription;

    private String productMainImage;








}