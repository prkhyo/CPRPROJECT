package com.CPR.redHome.dto.review;

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
@Alias("ReviewViewDto")
public class ReviewViewDto {


    private Long reviewId;

    private Integer reviewGrade;

    private String reviewImg;

    private String reviewContents;

    private LocalDateTime reviewCreatedDate;

    private Long productId;

    private String accountId;

    private Long help;

    private String helpState; //helpful, unhelpful



}