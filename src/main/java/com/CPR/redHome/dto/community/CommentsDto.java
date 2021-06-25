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

    private Long commentId; //auto

    private Long communityId;

    private String commentContents;

    private LocalDateTime commentCreatedDate;

}
