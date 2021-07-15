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

   private Long product_image_id;

   private String productImg;

   private String productThumbnail;

   private Long productId;

}
