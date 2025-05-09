package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;

import dev.mlopez.prueba.inditex.prices.application.GetPriceService;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.mapper.PriceDtoMapper;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.PriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@Tag(name = "${api.prices.name}", description = "${api.prices.description}")
public class PriceRestController {

    private final GetPriceService getPriceService;
    private final PriceDtoMapper mapper;

    public PriceRestController(GetPriceService getPriceService, PriceDtoMapper priceDtoMapper) {
        this.getPriceService = getPriceService;
        this.mapper = priceDtoMapper;
    }

    @Operation(summary = "${api.prices.get-prices.summary}", description = "${api.prices.get-prices.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrice(@Parameter(required = true, description = "Identificador de la marca", example = "1")
                                             @RequestParam int brandId,
                                             @Parameter(required = true, description = "Identificador del producto", example = "35455")
                                             @RequestParam int productId,
                                             @Parameter(required = true, description = "Fecha de consulta (en formato ISO 8601)", example = "2020-06-14T10:00:00")
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        return ResponseEntity.ok(mapper.toPriceDto(getPriceService.execute(brandId, productId, date)));
    }
}