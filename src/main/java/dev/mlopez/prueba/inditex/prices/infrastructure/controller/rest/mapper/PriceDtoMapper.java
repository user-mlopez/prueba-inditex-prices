package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.mapper;

import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

    static final Logger LOG = LoggerFactory.getLogger(PriceDtoMapper.class);

    @Mapping(source = "price.startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "price.endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = ".", target = "pvp", qualifiedByName = "formatearPrecio")
    PriceDto toPriceDto(Price price);

    @Named("formatearPrecio")
    static String formatearPrecio(Price price) {
        if (price.getPvp() == null || price.getCurr() == null) {
            LOG.warn("Review the currency and price fields of " + price);
            return "";
        }
        return price.getPvp().toPlainString() + " " + price.getCurr();
    }
}
