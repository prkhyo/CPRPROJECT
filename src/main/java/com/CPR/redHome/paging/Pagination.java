package com.CPR.redHome.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pagination {

    //페이징 계산에 필요한 파라미터들이 담긴 클래스
    private Criteria criteria;

    //전체 데이터 개수
    private int totalRecordCount;

    //전체 페이지 개수
    private int totalPageCount;

    //페이지 리스트의 첫 페이지 번호
    private int firstPage;

    //페이지 리스트의 마지막 페이지 번호
    private int lastPage;

    //SQL의 조건절에 사용되는 첫 RNUM(LIMIT 구문의 첫 번째 값에 사용되는 변수)
    private int firstRecordIndex;

    //이전 페이지 존재 여부
    private boolean hasPreviousPage;

    //다음 페이지 존재 여부
    private boolean hasNextPage;

    public Pagination(Criteria criteria, int totalRecordCount, int pageSize, int recordsPerPage) {

        this.criteria = criteria;
        this.totalRecordCount = totalRecordCount;
        this.criteria.setPageSize(pageSize);
        this.criteria.setRecordsPerPage(recordsPerPage);

        if(criteria.getCurrentPageNo() == 0)  this.criteria.setCurrentPageNo(1); //현재 페이지 초기값 1 로 설정

        if (totalRecordCount > 0) {
            calculation();
        }

    }


    private void calculation() {

        /* 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장) */
        totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
        if (criteria.getCurrentPageNo() > totalPageCount) {
            criteria.setCurrentPageNo(totalPageCount);
        }

        /* 페이지 리스트의 첫 페이지 번호 */
        firstPage = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

        /* 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장) */
        lastPage = firstPage + criteria.getPageSize() - 1;
        if (lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }

        /* SQL의 조건절에 사용되는 첫 RNUM */
        firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();


        /* 이전 페이지 존재 여부 */
        hasPreviousPage = firstPage != 1;

        /* 다음 페이지 존재 여부 */
        hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
    }

}