package com.CPR.redHome.dto.community;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Alias("CommentsDto")
public class CommentsDto {

    private Long commentId;

    private Long communityId;

    private String commentContents;

    private LocalDateTime commentCreatedDate;

    private Long memberId;



    //comment insert 성공 유무
    private boolean success;

    //comment list 불러올 때 사용
    private int commentCurrentPage;
    

}
