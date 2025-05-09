package dev.mlopez.prueba.inditex.prices.infrastructure.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.*;

@Configuration
public class LogbookConfig {

    @Bean
    public Logbook logbook() {
        Logbook logbook = Logbook.builder()
                .condition(Conditions.exclude(
                        Conditions.requestTo("**/openapi/**"),
                        Conditions.requestTo("**/actuator/**"),
                        Conditions.contentType("application/octet-stream")))
                .sink(new DefaultSink(new DefaultHttpLogFormatter(), new DefaultHttpLogWriter()))
                .headerFilter(HeaderFilters.removeHeaders("accept-language"))
                .headerFilter(HeaderFilters.removeHeaders("Connection"))
                .headerFilter(HeaderFilters.removeHeaders("host"))
                .headerFilter(HeaderFilters.removeHeaders("priority"))
                .headerFilter(HeaderFilters.removeHeaders("sec-fetch-dest"))
                .headerFilter(HeaderFilters.removeHeaders("sec-fetch-mode"))
                .headerFilter(HeaderFilters.removeHeaders("sec-fetch-site"))
                .headerFilter(HeaderFilters.removeHeaders("sec-fetch-user"))
                .headerFilter(HeaderFilters.removeHeaders("upgrade-insecure-requests"))
                .headerFilter(HeaderFilters.removeHeaders("sec-ch-ua"))
                .headerFilter(HeaderFilters.removeHeaders("sec-ch-ua-mobile"))
                .headerFilter(HeaderFilters.removeHeaders("sec-ch-ua-platform"))
                .headerFilter(HeaderFilters.removeHeaders("sec-gpc"))
                .headerFilter(HeaderFilters.removeHeaders("Transfer-Encoding"))
                .headerFilter(HeaderFilters.removeHeaders("Keep-Alive"))
                .build();
        return logbook;
    }
}
