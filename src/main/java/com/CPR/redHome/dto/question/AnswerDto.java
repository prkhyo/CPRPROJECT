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
@Alias("AnswerDto")
public class AnswerDto {


    private Long answerId;

    private String answerContents;

    private Long memberId;

    private Long questionId;

    private LocalDateTime answerDate;


}
