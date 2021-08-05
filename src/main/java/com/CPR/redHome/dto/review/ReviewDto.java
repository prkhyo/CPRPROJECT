package com.CPR.redHome.dto.review;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
@Alias("ReviewDto")
public class ReviewDto {

    private Long reviewId;
    private Integer reviewGrade;
    private String reviewImg;
    private String reviewContents;
    private LocalDateTime reviewCreatedDate;
    private Long productId;
    private Long memberId;
}
