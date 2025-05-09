package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PriceRestMessageSource {

    private static final Logger LOG = LoggerFactory.getLogger(PriceRestMessageSource.class);

    private final MessageSource messageSource;

    public PriceRestMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, Locale.getDefault());
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            return key;
        }
    }

    public String getMessage(String key, Object[] args) {
        try {
            return messageSource.getMessage(key, args, Locale.getDefault());
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            return key;
        }
    }
}
