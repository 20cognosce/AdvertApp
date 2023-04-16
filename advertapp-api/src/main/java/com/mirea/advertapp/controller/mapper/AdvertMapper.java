package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AdvertMapper {

    public abstract Advert advertCreateDtoToAdvert(AdvertCreateDto advertCreateDto);

    public abstract AdvertDto advertToAdvertDto(Advert advert);

    public abstract List<AdvertDto> advertListToAdvertDtoList(List<Advert> adverts);
}