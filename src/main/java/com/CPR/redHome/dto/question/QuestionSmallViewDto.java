package com.CPR.redHome.dto.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@ToString
@Alias("QuestionSmallViewDto")
public class QuestionSmallViewDto {


    private Long productId;
    private Long questionId;
    private String questionContents;
    private LocalDateTime questionDate;
    private String questionState;

    //product join
    private String productTitle;
}
