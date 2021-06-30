package com.CPR.redHome.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@NoArgsConstructor
@Alias("memberUpdateDto")
public class MemberUpdateDto {


    private Long memberId;
    private String memberPassword;
    private String name;
    private String telephone;
    private String email;
    private String address;


}
