package org.inditex.price.rest;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.inditex.price.domain.model.Price;
import org.inditex.price.infrastructure.persistence.PriceRepository;
import org.inditex.price.infrastructure.persistence.PriceRepositoryImplInMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LookupPriceControllerTest
{
    public static final String BASE_URL = "/brand/1/product/0003326989?date_to_apply=%s";

    @Autowired
    private MockMvc mockMvc;

    private PriceRepository priceRepository;

    @BeforeEach
    void setup() {
        AtomicLong idGenerator = new AtomicLong(1);

        priceRepository = new PriceRepositoryImplInMemory();

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-14T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(1)
                                  .productId("0003326989")
                                  .priority(0)
                                  .price(35.50f)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-14T15:00:00.000Z"))
                                  .endDate(Instant.parse("2020-06-14T18:30:00.000Z"))
                                  .priceList(2)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(25.45f)
                                  .currency("EUR")
                                  .build());


        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-15T00:00:00.000Z"))
                                  .endDate(Instant.parse("2020-06-15T11:00:00.000Z"))
                                  .priceList(3)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(30.50f)
                                  .currency("EUR")
                                  .build());

        priceRepository.save(Price.builder()
                                  .id(idGenerator.getAndIncrement())
                                  .brandId("1")
                                  .startDate(Instant.parse("2020-06-15T16:00:00.000Z"))
                                  .endDate(Instant.parse("2020-12-31T23:59:59.000Z"))
                                  .priceList(4)
                                  .productId("0003326989")
                                  .priority(1)
                                  .price(38.95f)
                                  .currency("EUR")
                                  .build());

    }

    @Test
    void shouldReturnPrice3550_WhenDateIsDay14And10Am() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(BASE_URL, "2020-06-14T10:00:00.000Z")))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value("0003326989"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50f));
    }

    @Test
    void shouldReturnPrice2545_WhenDateIsDay14And16Pm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(BASE_URL, "2020-06-14T16:00:00.000Z")))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value("0003326989"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45f));
    }

    @Test
    void shouldReturnPrice3550_WhenDateIsDay14And21Pm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(BASE_URL, "2020-06-14T21:00:00.000Z")))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value("0003326989"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50f));
    }

    @Test
    void shouldReturnPrice3050_WhenDateIsDay15And10Am() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(BASE_URL, "2020-06-15T10:00:00.000Z")))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value("0003326989"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50f));
    }

    @Test
    void shouldReturnPrice3895_WhenDateIsDay16And21Pm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(BASE_URL, "2020-06-16T21:00:00.000Z")))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value("0003326989"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95f));
    }

    @AfterEach
    void tearDown()
    {
        priceRepository.deleteAll();
    }

}
