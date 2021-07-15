package com.CPR.redHome.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("productDto")
public class ProductDto {

    private Integer productId;
    private String productTitle;
    private String productImg;
    private Integer productPrice;
    private Integer productDeliveryCharge;
    private Integer productQuantity;

    private Long productCategoryId;
    private Long memberId;
    private Long productThemeId;
}
