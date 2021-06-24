package com.CPR.redHome.paging;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {

    //현재 페이지 번호
    private int currentPageNo;

    //페이지당 출력할 데이터 개수
    private int recordsPerPage;

    //화면 하단에 출력할 페이지 사이즈
    private int pageSize;

    //검색 키워드
    private String searchKeyword;  //search_title,search_contents, search_id

    //검색 유형
    private String searchType;

    public Criteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 2;
        this.pageSize = 10;
    }


    //Criteria 클래스의 멤버 변수들을 쿼리 스트링(Query String) 형태로 반환하는 메서드
    public String makeQueryString(int pageNo) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .queryParam("searchType", searchType)
                .queryParam("searchKeyword", searchKeyword)
                .build()
                .encode();

        return uriComponents.toUriString();
    }



}

