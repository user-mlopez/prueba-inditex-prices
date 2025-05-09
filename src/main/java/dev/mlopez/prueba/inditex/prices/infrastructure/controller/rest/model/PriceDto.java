package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model;

import java.io.Serializable;

public class PriceDto implements Serializable {

    private int productId;
    private int brandId;
    private long priceList;
    private String startDate;
    private String endDate;
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

