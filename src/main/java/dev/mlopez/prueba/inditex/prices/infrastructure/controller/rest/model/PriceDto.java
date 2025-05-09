package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PriceDto implements Serializable {

    @Schema(description = "ID del producto", example = "35455")
    private int productId;

    @Schema(description = "ID de la marca", example = "1")
    private int brandId;

    @Schema(description = "ID de la tarifa", example = "1")
    private long priceList;

    @Schema(description = "Fecha de inicio", example = "2025-05-01T00:00:00")
    private String startDate;

    @Schema(description = "Fecha de fin", example = "2025-12-31T23:59:59")
    private String endDate;

    @Schema(description = "Precio final", example = "35,50 EUR")
    private String price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public long getPriceList() {
        return priceList;
    }

    public void setPriceList(long priceList) {
        this.priceList = priceList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
