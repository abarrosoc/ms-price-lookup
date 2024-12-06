package org.inditex.price.rest;

import java.time.OffsetDateTime;

import org.inditex.price.application.ports.in.PriceGetUseCase;
import org.inditex.price.domain.model.Price;
import org.inditex.price.interfaces.rest.BrandApi;
import org.inditex.price.interfaces.rest.dtos.ProductPrice;
import org.inditex.price.rest.mapper.PriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class LookupPricesController implements BrandApi
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupPricesController.class);

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
        LOGGER.info("Hi getProductPrice! productId: {} , brandId {}.", productId, brandId);

        Price price = priceGetUseCase.findPriceToApply(productId,
                                                       brandId,
                                                       dateToApply.toInstant());

        LOGGER.info("Bye getProductPrice! Price {}", price);

        return ResponseEntity.ok(PriceMapper.toProductPrice(price));
    }

}
