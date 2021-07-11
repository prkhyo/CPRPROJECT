package com.CPR.redHome.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
@NoArgsConstructor
@ToString
public class MemberLoginDto {

    @NotEmpty(message = "아이디 입력하세요")
    private String accountId;

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String memberPassword;
}
