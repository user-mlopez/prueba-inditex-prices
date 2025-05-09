package dev.mlopez.prueba.inditex.prices.infrastructure.repository.mapper;

import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.infrastructure.repository.model.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PriceEntityMapperImpl.class})
class PriceEntityMapperTests {

    @Autowired
    private PriceEntityMapper priceEntityMapper;

    @Test
    void shouldReturnNullWhenPriceEntityIsNull() {
        PriceEntity priceEntity = null;

        Price price = priceEntityMapper.toPrice(priceEntity);

        assertNull(price);
    }
}
