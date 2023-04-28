package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.ImageDto;
import com.mirea.advertapp.domain.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ImageMapper {

    @Mapping(source = "advert.id", target = "advertId")
    public abstract ImageDto imageToImageDto(Image image);
}
