package org.inditex.price.infrastructure.adapters;

import java.util.List;
import java.util.Optional;

import org.inditex.price.application.ports.out.PricePort;
import org.inditex.price.domain.model.Price;
import org.inditex.price.infrastructure.persistence.PriceRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceAdapter implements PricePort
{
    private final PriceRepository priceRepository;

    public PriceAdapter(PriceRepository priceRepository)
    {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<List<Price>> findByBrandIdAndProductId(String productId,
                                                           String brandId)
    {
        return priceRepository.findByBrandIdAndProductId(brandId, productId);
    }
}
