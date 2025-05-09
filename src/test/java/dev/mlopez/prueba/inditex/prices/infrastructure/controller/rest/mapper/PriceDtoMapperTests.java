package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.mapper;

import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.PriceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PriceDtoMapperImpl.class})
public class PriceDtoMapperTests {

    @Autowired
    private PriceDtoMapper priceDtoMapper;

    @Test
    void shouldMapPriceToPriceDto() {
        Price price = new Price();
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0));
        price.setEndDate(LocalDateTime.of(2023, 1, 2, 18, 30));
        price.setPrice(new BigDecimal("25.45"));
        price.setCurr("EUR");

        PriceDto dto = priceDtoMapper.toPriceDto(price);

        assertEquals("25.45 EUR", dto.getPrice());
        assertEquals("2020-06-14T15:00:00", dto.getStartDate());
        assertEquals("2023-01-02T18:30:00", dto.getEndDate());
    }


    @Test
    void shouldReturnEmptyPriceWhenPriceIsNull() {
        Price price = new Price();
        price.setPrice(null);
        price.setCurr("EUR");

        PriceDto dto = priceDtoMapper.toPriceDto(price);

        assertEquals("", dto.getPrice());
    }


    @Test
    void shouldReturnEmptyPriceWhenCurrencyIsNull() {
        Price price = new Price();
        price.setPrice(new BigDecimal("45.50"));
        price.setCurr(null);

        PriceDto dto = priceDtoMapper.toPriceDto(price);

        assertEquals("", dto.getPrice());
    }


    @Test
    void shouldReturnEmptyPriceWhenValueIsNull() {
        Price price = new Price();
        price.setPrice(null);
        price.setCurr(null);

        PriceDto dto = priceDtoMapper.toPriceDto(price);

        assertEquals("", dto.getPrice());
    }

    @Test
    void shouldReturnNullWhenPriceIsNull() {
        Price price = null;

        PriceDto priceDto = priceDtoMapper.toPriceDto(price);

        assertNull(priceDto);
    }
}
