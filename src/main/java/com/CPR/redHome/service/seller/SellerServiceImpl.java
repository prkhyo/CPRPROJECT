package com.CPR.redHome.service.seller;

import com.CPR.redHome.dto.seller.ImageDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.mapper.seller.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerMapper sellerMapper;

    @Override
    public int registProducts(ProductRegistDto productRegistDto) {

        return sellerMapper.registProducts(productRegistDto);
    }

    @Override
    public int registImage(List<ProductRegistDto> productRegistDto) {

        return sellerMapper.registImage(productRegistDto);
    }


}
