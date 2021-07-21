package com.CPR.redHome.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Alias("ProductViewDto")
public class ProductViewDto {

    private Long productId;

    private String accountId;

    private String productTitle;

    private LocalDateTime createdDate;

    private Integer productPrice;

    private Integer productDeliveryCharge;

    private Integer productQuantity;

    private String productCategoryName;

    private String productThemeName;

    private List<ProductImageDto> productImageDtos;





}
