package com.CPR.redHome.controller.admin.order;

import com.CPR.redHome.service.admin.order.OrderAdminService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class OrderAdminApiController {

    private final OrderAdminService orderAdminService;

    // 월별 주문 수
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/order/chart/byMonth")
    public ResponseEntity<JSONObject> listByMonth() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = orderAdminService.selectOrderByMonth();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 상태별 주문 수
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/order/chart/byState")
    public ResponseEntity<JSONObject> listByState() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = orderAdminService.selectOrderByState();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }



}
