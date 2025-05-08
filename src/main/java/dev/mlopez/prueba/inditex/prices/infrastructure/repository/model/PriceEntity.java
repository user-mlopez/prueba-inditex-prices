package dev.mlopez.prueba.inditex.prices.infrastructure.repository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public PriceEntity() {
    }

    public PriceEntity(int brandId, LocalDateTime startDate, LocalDateTime endDate, long priceList, int productId, int priority, BigDecimal price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public int getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long getPriceList() {
        return priceList;
    }

    public int getProductId() {
        return productId;
    }

    public int getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }

}
