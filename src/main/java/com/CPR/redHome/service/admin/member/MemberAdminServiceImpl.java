package com.CPR.redHome.service.admin.member;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.mapper.admin.member.MemberAdminMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.admin.util.MakeJsonForChartServcie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberAdminServiceImpl implements MemberAdminService {

    private final MemberAdminMapper memberAdminMapper;
    private final MakeJsonForChartServcie makeJsonForChartServcie;
    Criteria criteria;
    Pagination pagination;

    // 오늘
    @Override
    public int countAll(Criteria criteria) {
        int cnt = memberAdminMapper.selectTotalCnt(criteria);
        return cnt;
    }

    // list 불러오기
    @Override
    public List<MemberDto> getMemberList(int firstRecordIndex, Criteria criteria) {
        List<MemberDto> memberDtos = memberAdminMapper.selectMembers(firstRecordIndex, criteria);

        return memberDtos;
    }





    // 전체회원 조회
    @Override
    public List<MemberDto> selectAllMembers() {
        return memberAdminMapper.selectAllMembers();
    }

    // memberId로 회원 조회
    @Override
    public MemberDto selectMemberById(int memberId) {
        return memberAdminMapper.selectMemberById(memberId);
    }

    // 수정된 member update
    @Override
    public void updateMember(MemberDto memberDto) {
        memberAdminMapper.updateMember(memberDto);
    }


    // 연령대 별 회원 수 조회
    @Override
    public JSONObject selectMemberByAge() {

        // 나이대 별 회원수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = memberAdminMapper.selectMemberByAge();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "연령대", "회원수");

        return data;
    }

    // 지역 별 회원 수 조회
    @Override
    public JSONObject selectMemberByLocation() {
        // 지역 별 회원 수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = memberAdminMapper.selectMemberByLocation();

        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "지역", "회원수");

        return data;
    }

    // 등급 별 회원 수 조회
    @Override
    public JSONObject selectMemberByGrade() {

        LinkedHashMap<String, Integer> map = memberAdminMapper.selectMemberByGrade();

        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "등급", "회원수");

        return data;
    }


}
