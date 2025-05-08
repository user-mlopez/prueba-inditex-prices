package dev.mlopez.prueba.inditex.prices.domain.port;

import dev.mlopez.prueba.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> findApplicablePrice(int brandId, int productId, LocalDateTime date);


}
