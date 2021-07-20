package com.CPR.redHome.dto.question;

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
@Alias("QuestionDto")
public class QuestionDto {


    private Long questionId;

    private String questionContents;

    private String secretQuestion;

    private LocalDateTime questionDate;

    private String questionState;

    private int questionCategoryId;

    private Long productId;

    private Long memberId;



}
