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
public class CommunityDto  {

    private Long communityId; //auto

    private Long memberId;

    private String communityTitle;

    private String communityContents;

    private String communityImg;

    private LocalDateTime communityCreatedDate;

    private String communityState;

    private Long communityHit;



    /*조인 시 사용*/
    private Long ccommentId;

}
