package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceRestMessageSourceTests {

    @Autowired
    private PriceRestMessageSource priceRestMessageSource;

    @Test
    void returnKeyWhenMessageIsNotDefined() {
        String keyNotDefined = "error.key.notdefined";

        String message = priceRestMessageSource.getMessage(keyNotDefined);

        Assertions.assertEquals(keyNotDefined, message);
    }

    @Test
    void returnKeyWhenMessageIsNotDefinedWithArguments() {
        String keyNotDefined = "error.key.notdefined";

        String message = priceRestMessageSource.getMessage(keyNotDefined, new Object[]{ArgumentMatchers.any(String.class)});

        Assertions.assertEquals(keyNotDefined, message);
    }
}
