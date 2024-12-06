package org.inditex.price.application.services;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.inditex.price.application.ports.in.PriceGetUseCase;
import org.inditex.price.domain.exception.ResourceNotFoundException;
import org.inditex.price.domain.model.Price;
import org.inditex.price.infrastructure.adapters.PriceAdapter;
import org.inditex.price.infrastructure.persistence.PriceRepository;
import org.inditex.price.infrastructure.persistence.PriceRepositoryImplInMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceServiceTest
{
    private PriceGetUseCase priceService;

    private PriceAdapter priceAdapter;

    private PriceRepository priceRepository;


    @BeforeEach
    void setup(){
        AtomicLong idGenerator = new AtomicLong(1);

        priceRepository = new PriceRepositoryImplInMemory();
        priceAdapter = new PriceAdapter(priceRepository);
        priceService = new PriceService(priceAdapter);

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(1)
                                  .productId("0003326989")
                                  .price(35.50f)
                                  .priority(0)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-07-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(2)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(50.30f)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2021-06-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2022-12-31T23:59:59.000Z"))
                                  .priceList(3)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(48.10f)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-09-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(4)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(16.50f)
                                  .currency("EUR")
                                  .build());
    }

    @Test
    void shouldReturnPrice_WhenExistsRateToApply() {
        Price price = priceService.findPriceToApply("0003326989", "1", Instant.parse("2020-11-14T00:00:00.000Z"));
        Assertions.assertEquals(50.30f, price.getPrice());
    }


    @Test
    void shouldReturn404_WhenNotFoundRateToApply() {

        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            priceService.findPriceToApply("0123456789","1", Instant.now());
        });

        Assertions.assertEquals("No price found matching criteria", resourceNotFoundException.getMessage());
    }


    @AfterEach
    void tearDown()
    {
        priceRepository.deleteAll();
    }

}
