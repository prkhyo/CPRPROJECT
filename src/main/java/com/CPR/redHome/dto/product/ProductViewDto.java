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

    private String  address; //제품 판매자 주소

    private String productTitle;

    private LocalDateTime createdDate;

    private Integer productPrice;

    private Integer productDeliveryCharge;

    private Integer productQuantity;

    private String productCategoryName;

    private Integer productThemeId;

    private String productThemeName;

    private String productDescription;

    private String productMainImage;

    private List<ProductImageDto> productImageDtos;





}
