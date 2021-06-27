package com.CPR.redHome.service.admin;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public List<MemberDto> selectAllMember() {
        return adminMapper.selectAllMember();
    }
}
