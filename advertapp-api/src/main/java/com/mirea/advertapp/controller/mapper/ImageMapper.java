package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.ImageDto;
import com.mirea.advertapp.domain.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ImageMapper {

    public abstract Image imageDtoToImage(ImageDto imageDto);

    public abstract ImageDto imageToImageDto(Image image);
}
