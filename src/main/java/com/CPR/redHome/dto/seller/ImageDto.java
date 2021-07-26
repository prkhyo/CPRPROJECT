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
@Alias("ImageDto")
public class ImageDto {

    private String productImageId;
    private String imageUrl;
    private String mainImageUrl;


}
