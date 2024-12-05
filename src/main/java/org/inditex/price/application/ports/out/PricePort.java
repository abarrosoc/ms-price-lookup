package org.inditex.price.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.inditex.price.domain.model.Price;

public interface PricePort
{

    Optional<List<Price>> findByBrandIdAndProductId(String productId,
                                                    String brandId);

}
