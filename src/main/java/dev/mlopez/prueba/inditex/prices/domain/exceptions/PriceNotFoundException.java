package dev.mlopez.prueba.inditex.prices.domain.exceptions;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }
}

