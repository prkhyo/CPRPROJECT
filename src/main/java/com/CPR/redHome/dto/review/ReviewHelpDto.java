package com.CPR.redHome.dto.review;

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
@Alias("ReviewHelpDto")
public class ReviewHelpDto {

    private Long helpId;

    private Long reviewId;

    private Long memberId;

    private String helpState;


}
