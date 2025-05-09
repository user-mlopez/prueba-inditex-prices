package dev.mlopez.prueba.inditex.prices.infrastructure.repository;

import dev.mlopez.prueba.inditex.prices.infrastructure.repository.model.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceJpaRepositoryTests {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    @DisplayName("Prueba 1: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA).")
    void shouldFindPriceList1WhenDate20200614T100000() {
        PriceEntity expectedPriceEntity = new PriceEntity();
        expectedPriceEntity.setBrandId(1);
        expectedPriceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        expectedPriceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        expectedPriceEntity.setPriceList(1);
        expectedPriceEntity.setProductId(35455);
        expectedPriceEntity.setPriority(0);
        expectedPriceEntity.setPrice(new BigDecimal("35.50"));
        expectedPriceEntity.setCurr("EUR");

        Optional<PriceEntity> priceEntity = priceJpaRepository.findApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-14T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
        assertTrue(priceEntity.isPresent());
        this.assertEqualsPriceEntity(expectedPriceEntity, priceEntity.get());
    }

    @Test
    @DisplayName("Prueba 2: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).")
    void shouldFindPriceList2WhenDate20200614T160000() {
        PriceEntity expectedPriceEntity = new PriceEntity();
        expectedPriceEntity.setBrandId(1);
        expectedPriceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        expectedPriceEntity.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        expectedPriceEntity.setPriceList(2);
        expectedPriceEntity.setProductId(35455);
        expectedPriceEntity.setPriority(1);
        expectedPriceEntity.setPrice(new BigDecimal("25.45"));
        expectedPriceEntity.setCurr("EUR");

        Optional<PriceEntity> priceEntity = priceJpaRepository.findApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-14T16:00:00", DateTimeFormatter.ISO_DATE_TIME));
        assertTrue(priceEntity.isPresent());
        this.assertEqualsPriceEntity(expectedPriceEntity, priceEntity.get());
    }

    @Test
    @DisplayName("Prueba 3: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).")
    void shouldFindPriceList1WhenDate20200614T210000() {
        PriceEntity expectedPriceEntity = new PriceEntity();
        expectedPriceEntity.setBrandId(1);
        expectedPriceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        expectedPriceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        expectedPriceEntity.setPriceList(1);
        expectedPriceEntity.setProductId(35455);
        expectedPriceEntity.setPriority(0);
        expectedPriceEntity.setPrice(new BigDecimal("35.50"));
        expectedPriceEntity.setCurr("EUR");

        Optional<PriceEntity> priceEntity = priceJpaRepository.findApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-14T21:00:00", DateTimeFormatter.ISO_DATE_TIME));
        assertTrue(priceEntity.isPresent());
        this.assertEqualsPriceEntity(expectedPriceEntity, priceEntity.get());
    }

    @Test
    @DisplayName("Prueba 4: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).")
    void shouldFindPriceList3WhenDate20200615T100000() {
        PriceEntity expectedPriceEntity = new PriceEntity();
        expectedPriceEntity.setBrandId(1);
        expectedPriceEntity.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        expectedPriceEntity.setEndDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0));
        expectedPriceEntity.setPriceList(3);
        expectedPriceEntity.setProductId(35455);
        expectedPriceEntity.setPriority(1);
        expectedPriceEntity.setPrice(new BigDecimal("30.50"));
        expectedPriceEntity.setCurr("EUR");

        Optional<PriceEntity> priceEntity = priceJpaRepository.findApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-15T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
        assertTrue(priceEntity.isPresent());
        this.assertEqualsPriceEntity(expectedPriceEntity, priceEntity.get());
    }

    @Test
    @DisplayName("Prueba 5: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).")
    void shouldFindPriceList4WhenDate20200616T210000() {
        PriceEntity expectedPriceEntity = new PriceEntity();
        expectedPriceEntity.setBrandId(1);
        expectedPriceEntity.setStartDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        expectedPriceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        expectedPriceEntity.setPriceList(4);
        expectedPriceEntity.setProductId(35455);
        expectedPriceEntity.setPriority(1);
        expectedPriceEntity.setPrice(new BigDecimal("38.95"));
        expectedPriceEntity.setCurr("EUR");

        Optional<PriceEntity> priceEntity = priceJpaRepository.findApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_DATE_TIME));
        assertTrue(priceEntity.isPresent());
        this.assertEqualsPriceEntity(expectedPriceEntity, priceEntity.get());
    }

    private void assertEqualsPriceEntity(PriceEntity expectedPriceEntity, PriceEntity actualPriceEntity) {
        assertEquals(expectedPriceEntity.getBrandId(), actualPriceEntity.getBrandId());
        assertEquals(expectedPriceEntity.getStartDate(), actualPriceEntity.getStartDate());
        assertEquals(expectedPriceEntity.getEndDate(), actualPriceEntity.getEndDate());
        assertEquals(expectedPriceEntity.getPriceList(), actualPriceEntity.getPriceList());
        assertEquals(expectedPriceEntity.getProductId(), actualPriceEntity.getProductId());
        assertEquals(expectedPriceEntity.getPriority(), actualPriceEntity.getPriority());
        assertEquals(expectedPriceEntity.getPrice().toPlainString(), actualPriceEntity.getPrice().toPlainString());
        assertEquals(expectedPriceEntity.getCurr(), actualPriceEntity.getCurr());
    }
}
