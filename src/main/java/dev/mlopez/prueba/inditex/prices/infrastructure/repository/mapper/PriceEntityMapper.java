package dev.mlopez.prueba.inditex.prices.infrastructure.repository.mapper;


import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.infrastructure.repository.model.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    @Mapping(source = "price", target = "pvp")
    Price toPrice(PriceEntity priceEntity);
}