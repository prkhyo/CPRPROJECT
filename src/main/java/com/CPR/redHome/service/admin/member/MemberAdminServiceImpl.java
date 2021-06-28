package com.CPR.redHome.service.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.mapper.admin.member.MemberAdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberAdminServiceImpl implements MemberAdminService {

    private final MemberAdminMapper memberAdminMapper;

    // 전체회원 조회
    @Override
    public List<MemberDto> selectAllMember() {
        return memberAdminMapper.selectAllMember();
    }


    // 나이대별 회원 조회
    @Override
    public Map<String, Integer> selectMemberByAge() {
        return memberAdminMapper.selectMemberByAge();
    }
}
