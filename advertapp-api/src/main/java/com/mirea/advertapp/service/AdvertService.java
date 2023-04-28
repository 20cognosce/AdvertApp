package com.mirea.advertapp.service;

import com.mirea.advertapp.controller.exception.EntityNotFoundException;
import com.mirea.advertapp.controller.mapper.AdvertMapper;
import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.repo.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final UserService userService;
    private final AdvertMapper advertMapper;

    public Advert create(AdvertCreateDto advertCreateDto) {
        User user = userService.getById(advertCreateDto.getUserId());
        Advert advert = advertMapper.advertCreateDtoToAdvert(advertCreateDto, user);
        return advertRepository.save(advert);
    }

    public AdvertDto convertToDto(Advert advert) {
        return advertMapper.advertToAdvertDto(advert);
    }

    public Advert getById(Long id) {
        return advertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advert with id = " + id + " not found"));
    }

    public AdvertDto getByIdDto(Long id) {
        return advertMapper.advertToAdvertDto(getById(id));
    }

    public List<AdvertDto> searchByTitle(String title) {
        var foundAdverts = advertRepository.findByTitleContainsIgnoreCase(title);
        return advertMapper.advertListToAdvertDtoList(foundAdverts);
    }

    public List<Advert> getAll() {
        return StreamSupport
                .stream(advertRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<AdvertDto> getAllDto() {
        List<Advert> adverts = getAll();
        return advertMapper.advertListToAdvertDtoList(adverts);
    }
}
