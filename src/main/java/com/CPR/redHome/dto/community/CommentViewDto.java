package com.CPR.redHome.dto.community;

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
@Alias("CommentViewDto")
public class CommentViewDto {

    private Long commentId;

    private Long communityId;

    private String commentContents;

    private LocalDateTime commentCreatedDate;

    private String accountId;










}
