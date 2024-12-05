package org.inditex.price.rest;

import java.time.OffsetDateTime;

import org.inditex.price.application.ports.in.PriceGetUseCase;
import org.inditex.price.domain.model.Price;
import org.inditex.price.interfaces.rest.BrandApi;
import org.inditex.price.interfaces.rest.dtos.ProductPrice;
import org.inditex.price.rest.mapper.PriceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class LookupPricesController implements BrandApi
{

    private final PriceGetUseCase priceGetUseCase;

    public LookupPricesController(PriceGetUseCase priceGetUseCase)
    {
        this.priceGetUseCase = priceGetUseCase;
    }

    @Override
    public ResponseEntity<ProductPrice> getProductPrice(String productId,
                                                            String brandId,
                                                            OffsetDateTime dateToApply)
    {
        Price price = priceGetUseCase.findPriceToApply(productId,
                                                       brandId,
                                                       dateToApply.toInstant());

        return ResponseEntity.ok(PriceMapper.toProductPrice(price));
    }

}
