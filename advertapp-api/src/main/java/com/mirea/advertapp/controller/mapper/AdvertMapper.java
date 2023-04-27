package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public abstract class AdvertMapper {

    public Advert advertCreateDtoToAdvert(AdvertCreateDto advertCreateDto, User user) {
        if ( advertCreateDto == null ) {
            return null;
        }

        return Advert.builder()
                .title(advertCreateDto.getTitle())
                .description(advertCreateDto.getDescription())
                .published(LocalDateTime.now())
                .address(advertCreateDto.getAddress())
                .user(user)
                .build();
    }

    @Mapping(source = "user.id", target = "userId")
    public abstract AdvertDto advertToAdvertDto(Advert advert);

    public abstract List<AdvertDto> advertListToAdvertDtoList(List<Advert> adverts);
}