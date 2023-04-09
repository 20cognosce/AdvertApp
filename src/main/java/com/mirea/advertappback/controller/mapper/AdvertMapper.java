package com.mirea.advertappback.controller.mapper;

import com.mirea.advertappback.domain.dto.AdvertCreateDto;
import com.mirea.advertappback.domain.dto.AdvertDto;
import com.mirea.advertappback.domain.entity.Advert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdvertMapper {

    public Advert advertCreateDtoToAdvert(AdvertCreateDto advertCreateDto){
        return new Advert();
    }

    public abstract AdvertDto advertToAdvertDto(Advert advert);
}