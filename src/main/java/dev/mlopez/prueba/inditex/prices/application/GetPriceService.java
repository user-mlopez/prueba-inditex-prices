package dev.mlopez.prueba.inditex.prices.application;

import dev.mlopez.prueba.inditex.prices.domain.exceptions.PriceNotFoundException;
import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.domain.port.PriceRepositoryPort;

import java.time.LocalDateTime;


public class GetPriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price execute(int brandId, int productId, LocalDateTime date) {

        return priceRepositoryPort.findApplicablePrice(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException("There are no prices available for the specified product in the specified date."));
    }
}