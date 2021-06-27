package com.CPR.redHome.mapper.admin;

import com.CPR.redHome.dto.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<MemberDto> selectAllMember();
}
