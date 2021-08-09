package com.CPR.redHome.service.seller;

import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.seller.ImageDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.mapper.seller.SellerMapper;
import com.CPR.redHome.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerMapper sellerMapper;

    @Override
    public int countAllSellerQuestions(Long memberId, String reply, Criteria criteria) {
        int questionCnt = sellerMapper.selectSellerQuestionTotalCnt(memberId ,reply, criteria);
        return questionCnt;
    }

    @Override
    public List<QuestionViewDto> getQuestionList(Long memberId, String reply, String orderType, int firstRecordIndex, Criteria criteria) {

        List<QuestionViewDto> questionList = sellerMapper.selectSellerQuestion(memberId, reply, orderType, firstRecordIndex, criteria);
        return questionList;
    }

    @Override
    public int registProducts(ProductRegistDto productRegistDto) {

        return sellerMapper.registProducts(productRegistDto);
    }

    @Override
    public int registImage(List<ProductRegistDto> productRegistDto) {

        return sellerMapper.registImage(productRegistDto);
    }


}
