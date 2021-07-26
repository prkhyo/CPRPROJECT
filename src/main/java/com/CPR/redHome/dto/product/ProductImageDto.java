package com.CPR.redHome.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Alias("ProductImageDto")
public class ProductImageDto {

   private Long productImageId;

   private String productImg;

   private Long productId;


}
