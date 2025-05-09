package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;

import dev.mlopez.prueba.inditex.prices.application.GetPriceService;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.mapper.PriceDtoMapper;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.PriceDto;
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
public class PriceRestController {

    private final GetPriceService getPriceService;
    private final PriceDtoMapper mapper;

    public PriceRestController(GetPriceService getPriceService, PriceDtoMapper priceDtoMapper) {
        this.getPriceService = getPriceService;
        this.mapper = priceDtoMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrice(@RequestParam int brandId,
                                             @RequestParam int productId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        return ResponseEntity.ok(mapper.toPriceDto(getPriceService.execute(brandId, productId, date)));
    }
}