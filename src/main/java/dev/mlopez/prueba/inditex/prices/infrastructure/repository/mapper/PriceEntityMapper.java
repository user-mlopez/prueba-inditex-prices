package dev.mlopez.prueba.inditex.prices.infrastructure.repository.mapper;


import dev.mlopez.prueba.inditex.prices.domain.model.Price;
import dev.mlopez.prueba.inditex.prices.infrastructure.repository.model.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toPrice(PriceEntity priceEntity);
}