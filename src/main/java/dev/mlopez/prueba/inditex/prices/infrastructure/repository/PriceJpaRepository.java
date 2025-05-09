package dev.mlopez.prueba.inditex.prices.infrastructure.repository;

import dev.mlopez.prueba.inditex.prices.infrastructure.repository.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.brandId = :brandId AND p.productId = :productId " +
            " AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findApplicablePrice(int brandId, int productId, LocalDateTime date);

    @Query("SELECT EXISTS (SELECT 1 FROM PriceEntity p WHERE p.brandId = :brandId)")
    boolean existsBrandId(int brandId);

    @Query("SELECT EXISTS (SELECT 1 FROM PriceEntity p WHERE p.productId = :productId)")
    boolean existsProductId(int productId);

    @Query("SELECT EXISTS (SELECT 1 FROM PriceEntity p WHERE p.brandId = :brandId AND p.productId = :productId)")
    boolean isProductAssociatedWithBrand(int brandId, int productId);

}