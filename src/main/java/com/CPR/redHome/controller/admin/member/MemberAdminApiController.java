package com.CPR.redHome.controller.admin.member;

import com.CPR.redHome.service.admin.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class MemberAdminApiController {

    private final MemberAdminService memberAdminService;

    // 연령대 별 회원 수
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/member/chart/byAge")
    public ResponseEntity<JSONObject> listByAge() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = memberAdminService.selectMemberByAge();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 지역 별 회원 수
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/member/chart/byLocation")
    public ResponseEntity<JSONObject> listByLocation() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = memberAdminService.selectMemberByLocation();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 등급 별 회원 수
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/member/chart/byGrade")
    public ResponseEntity<JSONObject> listByGrade() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = memberAdminService.selectMemberByGrade();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

}
