package com.CPR.redHome.dto.cart;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("orderDto")
public class OrderDto extends CartDto {

    // javscript ajax로 json데이터 전송 시 필요한 객체들 생성
    private String orderNo;
    private Long gradeId;

    // 해당 계정 정보
    private Integer currentPoint; // 현재 포인트
    private Double usedPoint;   // 사용한 포인트
    private Integer accumulationRate; // 적립금
    private Integer addPoint;

    private String phoneNumber; // 구매자 정보 : 핸드폰 번호
    private String accountId;   // 구매자 정보 : 계정 아이디 (뷰단에서 사용)
    private String address;     // 주소
    private String receiver;     //받는 사람
    private String orderRequest; //주문 요청 사항
    private String email;       //이메일 (등록된 이메일로 전달)

    private Integer orderId;
    private LocalDateTime orderDate;
    private String orderState;

}