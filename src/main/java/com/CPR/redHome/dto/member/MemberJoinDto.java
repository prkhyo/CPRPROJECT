package com.CPR.redHome.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
@Alias("memberJoinDto")
public class MemberJoinDto {


    @NotEmpty(message = "아이디를 입력해주세요")
    @Length(min=4, max = 20, message = "아이디는 4~20글자 사이로 작성해야 합니다. ")
    private String accountId;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Length(min=4,max=20,message = "비밀번호는 4~20글자 사이로 작성해야 합니다.")
    private String memberPassword;

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$", message = "알맞은 번호가 아닙니다.")
    @NotEmpty(message = "전화번호를 입력해주세요")
    private String telephone;

    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;

    @NotEmpty(message = "주소를 입력해주세요")
    private String address;

    @Pattern(regexp = "^\\d{8}$", message = "생년월일 형식에 맞지 않습니다.")
    @NotEmpty(message = "생년월일을 입력해주세요")
    private String birthdate;
}
