# readMe.md

![logo](src/main/resources/static/img/readMe/logo.PNG)

# 레드홈(Layered Home) : 인테리어 상품 판매

---

단순 주거라는 집의 기본 역할에 일과 여가 등 새로운 기능들이 더한 공간으로의 진화라는 의미로 집의 변화를 나타내는 신조어 '레이어드 홈'

2021인테리어 트랜드로 떠오르는 '레이어드 홈'을 오늘의 집과 같은 인테리어 플랫폼에 접목 시켜

테마 별로 구분된 인테리어 상품을 구매 및 판매, 관리할 수 있는 웹 서비스 레드홈을 제작했습니다.

### 팀 구성 : 김준철  박효진  최정연  한성준

---

## 사용 기술 및 라이브러리

**Data Base**

- MySQL

**Back End**

- Java11
- Gradle
- Spring boot
    - Validation
- Mybatis

**Front End**

- Javascript, Ajax, Jquery
- API
    - 결제 - pgAPI
    - 공유 - SNS API
    - 차트 - google chart API
    - 주소 - Daum 우편번호 API
- Thymeleaf

**Cooperation**

- git , sourceTree
- slack, notion

---

## 요구사항

**회원**

- 회원가입
- 로그인 및 로그아웃
- 회원 탈퇴
- 회원정보 수정
- 내가 쓴 글 보기
- 내 주문 내역
- 판매자 권한 신청
- 회원 등급

**상품**

- 상품 등록
- 상품 리스트
- 장바구니 담기
- 상품 결제
- 리뷰
- 상품 문의

**커뮤니티**

- 질문 등록
- 질문 수정 및 삭제
- 댓글 달기
- 공유하기

**결제**

- 결제 시 포인트 적용
- 포인트 적립

**관리자**

- 회원 / 상품 / 주문 관리
- 판매자 권한 신청 승인
- 회원 / 상품 / 주문 통계

---

## ER Diargram

요구분석 사항 토대로 모델링 진행

![logo](src/main/resources/static/img/readMe/ERD.PNG)

[https://www.erdcloud.com/d/RyyET8f2ZA9siudLF](https://www.erdcloud.com/d/RyyET8f2ZA9siudLF)

---

## 구현 페이지



<details>
<summary>회원</summary>
<div markdown="1">

- **회원가입**

![join](src/main/resources/static/img/readMe/member/join1.PNG)

![join2](src/main/resources/static/img/readMe/member/join2.PNG)

---

- **로그인**

![login](src/main/resources/static/img/readMe/member/login.PNG)

---

- **회원수정**

![update](src/main/resources/static/img/readMe/member/update.PNG)

---

- **회원탈퇴**

![delete](src/main/resources/static/img/readMe/member/delete.PNG)

---

- **내가 쓴 글 보기**

- **리뷰 작성**
</div>
</details>
<br>
<details>
<summary>상품</summary>
<div markdown="1">

- **상품 등록**
![regist_product](src/main/resources/static/img/readMe/product/regist_product.png)

![regist_product2](src/main/resources/static/img/readMe/product/regist_product2.PNG)
- 상품 리스트 페이지

  ![product_list](src/main/resources/static/img/readMe/product/product_list.PNG)
- 장바구니 담기

![cart](src/main/resources/static/img/readMe/product/cart.PNG)

- 상품 결제

![payment](src/main/resources/static/img/readMe/product/payment.PNG)

- 상품 리뷰

![review](src/main/resources/static/img/readMe/product/review.PNG)
      
- 상품 문의 등록

![regist_question](src/main/resources/static/img/readMe/product/regist_question.PNG)
- 상품 문의
![question_list](src/main/resources/static/img/readMe/product/question_list.PNG)
- 공유하기

![share](src/main/resources/static/img/readMe/product/share.PNG)

</div>
</details>
<br>
<details>
<summary>커뮤니티</summary>
<div markdown="1">

- 커뮤니티 등록

![register](src/main/resources/static/img/readMe/community/register.PNG)

- 커뮤니티 리스트
![community_list](src/main/resources/static/img/readMe/community/community_list.PNG)

- 커뮤니티 상세 보기
![detail](src/main/resources/static/img/readMe/community/detail.PNG)

- 커뮤니티 답변 등록

![community_register](src/main/resources/static/img/readMe/community/community_register.PNG)

- 공유하기

![share](src/main/resources/static/img/readMe/community/share.PNG)
</div>
</details>
<br>
<details>
<summary>판매자 페이지</summary>
<div markdown="1">

- 판매자 권한 신청

![apply](src/main/resources/static/img/readMe/seller/apply.PNG)

- 판매자 권한 승인

![accept](src/main/resources/static/img/readMe/seller/accept.PNG)

- 내 판매 상품

![product](src/main/resources/static/img/readMe/seller/product.PNG)

- 내 상품 문의

![question](src/main/resources/static/img/readMe/seller/my_product_question.PNG)

- 주문 관리

![manage](src/main/resources/static/img/readMe/seller/manage_orders.PNG)

- 상품 문의 답변하기

![answer1](src/main/resources/static/img/readMe/seller/answer.PNG)

![answer2](src/main/resources/static/img/readMe/seller/answer2.PNG)

![answer3](src/main/resources/static/img/readMe/seller/answer3.PNG)

</div>
</details>
<br>
<details>
<summary>관리자 페이지</summary>
<div markdown="1">


- 회원 관리 페이지
  
![member](src/main/resources/static/img/readMe/admin/member_manage.PNG)
- 상품 관리 페이지

![product_manage](src/main/resources/static/img/readMe/admin/product_manage.PNG)

- 주문 관리 페이지

![order_manage](src/main/resources/static/img/readMe/admin/order_manage.PNG)

- 회원 통계

![mebmer_static](src/main/resources/static/img/readMe/admin/member_static.PNG)

- 상품 통계

![product_static](src/main/resources/static/img/readMe/admin/product_static.PNG)
   
   
- 주문 통계

![order_static](src/main/resources/static/img/readMe/admin/order_static.PNG)
</div>
</details>