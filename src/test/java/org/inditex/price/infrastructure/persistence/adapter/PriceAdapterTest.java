package org.inditex.price.infrastructure.persistence.adapter;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.inditex.price.application.ports.out.PricePort;
import org.inditex.price.domain.model.Price;
import org.inditex.price.infrastructure.adapters.PriceAdapter;
import org.inditex.price.infrastructure.persistence.PriceRepository;
import org.inditex.price.infrastructure.persistence.PriceRepositoryImplInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceAdapterTest
{

    private PricePort priceAdapter;

    private PriceRepository priceRepository;

    @BeforeEach
    void setup() {
        AtomicLong idGenerator = new AtomicLong(1);

        priceRepository = new PriceRepositoryImplInMemory();
        priceAdapter = new PriceAdapter(priceRepository);

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(1)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(35.50f)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2024-08-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2024-09-30T23:59:59.000Z"))
                                  .priceList(2)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(38.50f)
                                  .currency("EUR")
                                  .build());


        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(1)
                                  .productId("5005528383")
                                  .priority(1)
                                  .price(35.50f)
                                  .currency("EUR")
                                  .build());

    }

    @Test
    void shouldReturnPrice_WhenExistsProductAndBrand() {
        Optional<List<Price>> price = priceAdapter.findByBrandIdAndProductId("0003326989", "1");
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(2, price.get().size());
    }

    @Test
    void shouldNotReturnPrice_WhenNotExistsProductOrBrand() {
        Optional<List<Price>> price = priceAdapter.findByBrandIdAndProductId("0123456789", "1");
        Assertions.assertTrue(price.isPresent());
        Assertions.assertTrue(price.get().isEmpty());
    }

}
