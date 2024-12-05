package org.inditex.price.application.ports.in;

import java.time.Instant;

import org.inditex.price.domain.model.Price;

public interface PriceGetUseCase
{

    Price findPriceToApply(String productId,
                           String brandId,
                           Instant dateToApply);
}
