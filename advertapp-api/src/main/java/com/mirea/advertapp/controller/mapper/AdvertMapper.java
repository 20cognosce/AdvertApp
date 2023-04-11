package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdvertMapper {

    public Advert advertCreateDtoToAdvert(AdvertCreateDto advertCreateDto){
        return new Advert();
    }

    public abstract AdvertDto advertToAdvertDto(Advert advert);
}