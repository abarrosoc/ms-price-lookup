package org.inditex.price.application.services;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.inditex.price.application.ports.in.PriceGetUseCase;
import org.inditex.price.domain.exception.ResourceNotFoundException;
import org.inditex.price.domain.model.Price;
import org.inditex.price.infrastructure.adapters.PriceAdapter;
import org.springframework.stereotype.Service;

@Service
public class PriceService implements PriceGetUseCase
{

    private final PriceAdapter priceAdapter;

    public PriceService(PriceAdapter priceAdapter)
    {
        this.priceAdapter = priceAdapter;
    }

    @Override
    public Price findPriceToApply(String productId,
                                  String brandId,
                                  Instant dateToApply)
    {
        Optional<List<Price>> prices = priceAdapter.findByBrandIdAndProductId(productId, brandId);

        return prices.stream()
                     .flatMap(List::stream)
                     .filter(price -> price.getStartDate().isBefore(dateToApply)
                                        && price.getEndDate().isAfter(dateToApply))
                     .max(Comparator.comparing(Price::getPriority))
                     .orElseThrow(() -> new ResourceNotFoundException("No price found matching criteria"));
    }
}
