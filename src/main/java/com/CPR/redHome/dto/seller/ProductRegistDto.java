package com.CPR.redHome.dto.seller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Alias("RegistDto")
public class ProductRegistDto extends ImageDto{

    private Long memberId;

    private String title;
    private String description;

    private Integer price;
    private Integer categoryNo;
    private Integer themeNo;
    private Integer deliveryCharge;
    private Integer totalQuantity;




}
