package dev.mlopez.prueba.inditex.prices.infrastructure.config;

import dev.mlopez.prueba.inditex.prices.application.GetPriceService;
import dev.mlopez.prueba.inditex.prices.domain.port.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public GetPriceService getPriceService(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceService(priceRepositoryPort);
    }

}
