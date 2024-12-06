package org.inditex.price.rest.mapper;

import java.time.ZoneOffset;

import org.inditex.price.domain.model.Price;
import org.inditex.price.interfaces.rest.dtos.ProductPrice;

public class PriceMapper
{

    public static ProductPrice toProductPrice(Price price) {
        return new ProductPrice().productId(price.getProductId())
                                 .brandId(price.getBrandId())
                                 .dateFrom(price.getStartDate().atOffset(ZoneOffset.UTC))
                                 .dateTo(price.getEndDate().atOffset(ZoneOffset.UTC))
                                 .rate(price.getPriceList())
                                 .price(price.getPrice());
    }

}
