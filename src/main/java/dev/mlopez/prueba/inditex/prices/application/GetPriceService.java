package dev.mlopez.prueba.inditex.prices.application;

import dev.mlopez.prueba.inditex.prices.domain.exceptions.PriceNotFoundException;
import dev.mlopez.prueba.inditex.prices.domain.exceptions.ValidationException;
import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.domain.port.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetPriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price execute(int brandId, int productId, LocalDateTime date) {

        this.validate(brandId, productId, date);

        return priceRepositoryPort.findApplicablePrice(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException("error.pricelist.notfound"));
    }

    private void validate(int brandId, int productId, LocalDateTime date) {
        List<String> errors = new ArrayList<>();

        this.validateBrandId(brandId, errors);
        this.validateProductId(productId, errors);
        this.validateAssociation(brandId, productId, errors);
        this.validateDate(date, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    private void validateBrandId(int brandId, List<String> errors) {
        if (brandId <= 0) {
            errors.add("error.brandid.invalid");
        } else if (!priceRepositoryPort.existsBrandId(brandId)) {
            errors.add("error.brandid.notfound");
        }
    }

    private void validateProductId(int productId, List<String> errors) {
        if (productId <= 0) {
            errors.add("error.productid.invalid");
        } else if (!priceRepositoryPort.existsProductId(productId)) {
            errors.add("error.productid.notfound");
        }
    }

    private void validateAssociation(int brandId, int productId, List<String> errors) {
        if (errors.isEmpty() && !priceRepositoryPort.isProductAssociatedWithBrand(brandId, productId)) {
            errors.add("error.brandid.productid");
        }
    }

    private void validateDate(LocalDateTime date, List<String> errors) {
        if (date == null) {
            errors.add("error.date.required");
        }
    }

}
