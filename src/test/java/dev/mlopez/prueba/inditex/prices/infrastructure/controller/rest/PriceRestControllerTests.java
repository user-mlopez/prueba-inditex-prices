package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;


import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.HttpErrorInfo;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.PriceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceRestControllerTests {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Autowired
    private TestRestTemplate restTemplate;

    private String buildUrl(int brandId, int productId, String date) {
        return String.format("http://localhost:%d%s/prices?brandId=%d&productId=%d&date=%s", port, contextPath, brandId, productId, date);
    }

    @Test
    void shouldReturnPriceList1WhenBrandIs1ProductIs35455AndDateIs20200614T100000() {
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(
                this.buildUrl(1, 35455, "2020-06-14T10:00:00"), PriceDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(1, response.getBody().getPriceList());
        Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0).format(DateTimeFormatter.ISO_DATE_TIME)
                , response.getBody().getStartDate());
        Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59).format(DateTimeFormatter.ISO_DATE_TIME)
                , response.getBody().getEndDate());
        Assertions.assertEquals("35.50 EUR", response.getBody().getPvp());
    }

    @Test
    void shouldReturnUnprocessableEnityWhenBrandIsInvalid() {
        ResponseEntity<HttpErrorInfo> response = restTemplate.getForEntity(
                this.buildUrl(0, 35455, "2020-06-14T10:00:00"), HttpErrorInfo.class);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("El brandId indicado no es válido.", response.getBody().getMessage());
    }

    @Test
    void shouldReturnNotFoundWhenThereIsNotApplicablePrice() {
        ResponseEntity<HttpErrorInfo> response = restTemplate.getForEntity(
                this.buildUrl(1, 35455, "2000-06-16T21:00:00"), HttpErrorInfo.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("No hay tarifas disponibles para el producto indicado en la fecha especificada.", response.getBody().getMessage());
    }

    @Test
    void shouldReturnUnprocessableEnityWhenDateIsNull() {
        ResponseEntity<HttpErrorInfo> response = restTemplate.getForEntity(
                this.buildUrl(1, 35455, null), HttpErrorInfo.class);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("El valor 'null' no es válido para el parámetro 'date'.", response.getBody().getMessage());
    }

    @Test
    void shouldReturnUnprocessableEnityWhenParameterDateIsMissing() {
        String url = String.format("http://localhost:%d%s/prices?brandId=%d&productId=%d", port, contextPath, 1, 1);
        ResponseEntity<HttpErrorInfo> response = restTemplate.getForEntity(url, HttpErrorInfo.class);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("El parámetro 'date' de tipo 'LocalDateTime' es obligatorio.", response.getBody().getMessage());
    }

}
