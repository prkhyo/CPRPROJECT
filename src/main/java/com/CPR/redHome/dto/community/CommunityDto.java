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
@Alias("CommunityDto")
public class CommunityDto   {

    private Long communityId;

    private Long memberId;

    private String communityTitle;

    private String communityContents;

    private String communityImg;

    private LocalDateTime communityCreatedDate;

    private String communityState;

    private Long communityHit;



    /*커뮤니티 글 수정 시 업로드 한 파일 취소 여부 파악할 때 사용*/
    private String fileAnnulation;


}
