package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class AdvertMapper {

    @Autowired
    private UserService userService;

    public Advert advertCreateDtoToAdvert(AdvertCreateDto advertCreateDto) {
        if ( advertCreateDto == null ) {
            return null;
        }

        return Advert.builder()
                .title(advertCreateDto.getTitle())
                .description(advertCreateDto.getDescription())
                .published(LocalDateTime.now())
                .address(advertCreateDto.getAddress())
                .user(userService.getById(advertCreateDto.getUserId()))
                .build();
    }

    @Mapping(target = "user", qualifiedByName = "noAdverts")
    public abstract AdvertDto advertToAdvertDto(Advert advert);

    @AdvertWithoutUser
    @Mapping(target = "user", ignore = true)
    public abstract AdvertDto advertToAdvertDtoWithoutUser(Advert advert);

    public List<AdvertDto> advertListToAdvertDtoList(List<Advert> adverts) {
        return adverts.stream()
                .map(this::advertToAdvertDto)
                .collect(Collectors.toList());
    }
}