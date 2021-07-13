package com.CPR.redHome.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Alias("memberDto")
@ToString
public class MemberDto {



    private Long memberId;
    private Integer gradeId;
    private String accountId;
    private String memberPassword;
    private String name;
    private String telephone;
    private String email;
    private LocalDateTime createdDate;
    private String address;
    private String birthdate;
    private Integer point;
    private String role;

}