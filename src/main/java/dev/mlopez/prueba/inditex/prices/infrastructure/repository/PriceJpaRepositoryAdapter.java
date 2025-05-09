package dev.mlopez.prueba.inditex.prices.infrastructure.repository;

import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.domain.port.PriceRepositoryPort;
import dev.mlopez.prueba.inditex.prices.infrastructure.repository.mapper.PriceEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceJpaRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper mapper;

    public PriceJpaRepositoryAdapter(PriceJpaRepository priceJpaRepository, PriceEntityMapper priceEntityMapper) {
        this.priceJpaRepository = priceJpaRepository;
        this.mapper = priceEntityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> findApplicablePrice(int brandId, int productId, LocalDateTime date) {
        return priceJpaRepository.findApplicablePrice(brandId, productId, date).map(mapper::toPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBrandId(int brandId) {
        return priceJpaRepository.existsBrandId(brandId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsProductId(int productId) {
        return priceJpaRepository.existsProductId(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isProductAssociatedWithBrand(int brandId, int productId) {
        return priceJpaRepository.isProductAssociatedWithBrand(brandId, productId);
    }

}
