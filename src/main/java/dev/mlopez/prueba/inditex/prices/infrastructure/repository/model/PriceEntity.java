package dev.mlopez.prueba.inditex.prices.infrastructure.repository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
public class PriceEntity {

    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Id
    private long priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String curr;

}
