package dev.mlopez.prueba.inditex.prices.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PriceTests {

    @Test
    void shouldGetPriority() {
        int testPriority = 1;

        Price price = new Price();
        price.setPriority(testPriority);

        assertEquals(testPriority, price.getPriority());
    }
}
