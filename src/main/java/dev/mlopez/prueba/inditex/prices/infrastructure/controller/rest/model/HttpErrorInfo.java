package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class HttpErrorInfo {

    @Schema(description = "Fecha y hora del error", example = "2025-05-09T14:48:48.8555267+02:00")
    private final ZonedDateTime timestamp;

    @Schema(description = "Ruta de la API", example = "http://localhost:8080/prueba-inditex-prices/prices")
    private final String path;

    @Schema(description = "Estado HTTP de respuesta", example = "NOT_FOUND")
    private final HttpStatus httpStatus;

    @Schema(description = "Detalle de la causa del error", example = "No hay tarifas disponibles para el producto indicado en la fecha especificada.")
    private final String message;

    public HttpErrorInfo() {
        timestamp = null;
        this.httpStatus = null;
        this.path = null;
        this.message = null;
    }

    public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }

}
