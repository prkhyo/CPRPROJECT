package com.CPR.redHome.service.admin;

import com.CPR.redHome.dto.member.MemberDto;

import java.util.List;

public interface AdminService {

    List<MemberDto> selectAllMember();
}
