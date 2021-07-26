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
@Alias("QuestionViewDto")
public class QuestionViewDto {


    //문의 dto
    private Long questionId;

    private String questionContents;

    private String secretQuestion;

    private LocalDateTime questionDate;

    private String questionState;

    private String questionCategoryName;

    private Long productId;

    private String userAccountId;


   //답변 dto
    private Long answerId;

    private String answerContents;

    private String sellerAccountId;

    private LocalDateTime answerDate;

}
