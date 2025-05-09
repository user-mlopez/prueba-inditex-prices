package dev.mlopez.prueba.inditex.prices.application;

import dev.mlopez.prueba.inditex.prices.domain.exceptions.PriceNotFoundException;
import dev.mlopez.prueba.inditex.prices.domain.exceptions.ValidationException;
import dev.mlopez.prueba.inditex.prices.domain.port.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
class GetPriceServiceTests {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetPriceService getPriceService;


    @Test
    void shouldThrowValidationExceptionWhenBrandIdIsInvalid() {
        int invalidBrandId = 0;
        LocalDateTime now = LocalDateTime.now();

        ValidationException ex = assertThrows(ValidationException.class, () ->
                getPriceService.execute(invalidBrandId, 1, now)
        );

        assertTrue(ex.getErrors().contains("error.brandid.invalid"));
    }


    @Test
    void shouldThrowValidationExceptionWhenBrandIdNotExists() {
        int notExistsBrandId = 999;
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(priceRepositoryPort.existsBrandId(notExistsBrandId)).thenReturn(false);

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            getPriceService.execute(notExistsBrandId, 1, now);
        });

        assertTrue(ex.getErrors().contains("error.brandid.notfound"));
    }

    @Test
    void shouldThrowValidationExceptionWhenProductIdIsInvalid() {
        int invalidProductId = 0;
        LocalDateTime now = LocalDateTime.now();

        ValidationException ex = assertThrows(ValidationException.class, () ->
                getPriceService.execute(1, invalidProductId, now)
        );

        assertTrue(ex.getErrors().contains("error.productid.invalid"));
    }


    @Test
    void shouldThrowValidationExceptionWhenProductIdNotExists() {
        int notExistsProductId = 999;
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(priceRepositoryPort.existsProductId(notExistsProductId)).thenReturn(false);

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            getPriceService.execute(1, notExistsProductId, now);
        });

        assertTrue(ex.getErrors().contains("error.productid.notfound"));
    }


    @Test
    void shouldThrowValidationExceptionWhenProductIdNotAssociatedWithBrandId() {
        int existsBrandId = 1;
        int productIdNotAssociated = 1;
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(priceRepositoryPort.existsBrandId(existsBrandId)).thenReturn(true);
        Mockito.when(priceRepositoryPort.existsProductId(productIdNotAssociated)).thenReturn(true);

        Mockito.when(priceRepositoryPort.isProductAssociatedWithBrand(existsBrandId, productIdNotAssociated)).thenReturn(false);

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            getPriceService.execute(existsBrandId, productIdNotAssociated, now);
        });

        assertTrue(ex.getErrors().contains("error.brandid.productid"));
    }


    @Test
    void shouldThrowValidationExceptionWhenDateIsNull() {

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            getPriceService.execute(1, 1, null);
        });

        assertTrue(ex.getErrors().contains("error.date.required"));
    }


    @Test
    void shouldThrowPriceNotFoundExceptionWhenNoApplicablePrice() {
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(priceRepositoryPort.existsBrandId(1)).thenReturn(true);
        Mockito.when(priceRepositoryPort.existsProductId(1)).thenReturn(true);
        Mockito.when(priceRepositoryPort.isProductAssociatedWithBrand(1, 1)).thenReturn(true);
        Mockito.when(priceRepositoryPort.findApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        PriceNotFoundException ex = assertThrows(PriceNotFoundException.class, () ->
                getPriceService.execute(1, 1, now)
        );


        assertTrue(ex.getMessage().contains("error.pricelist.notfound"));
    }


}
