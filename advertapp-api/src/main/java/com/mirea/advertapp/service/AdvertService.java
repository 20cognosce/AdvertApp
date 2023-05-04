package com.mirea.advertapp.service;

import com.mirea.advertapp.controller.exception.EntityNotFoundException;
import com.mirea.advertapp.controller.mapper.AdvertMapper;
import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.repo.AdvertRepository;
import com.mirea.advertapp.repo.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final ImageRepository imageRepository;
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

    public List<Advert> getAll() {
        return StreamSupport
                .stream(advertRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<AdvertDto> getAllDto() {
        List<Advert> adverts = getAll();
        return advertMapper.advertListToAdvertDtoList(adverts);
    }

    public List<AdvertDto> findAllByTitle(String title) {
        var foundAdverts = advertRepository.findAllByTitleContainsIgnoreCase(title);
        return advertMapper.advertListToAdvertDtoList(foundAdverts);
    }

    @Transactional
    public void deleteById(Long id) {
        var advert = getById(id);
        imageRepository.deleteAll(advert.getImages());
        advertRepository.deleteById(id);

        if (advertRepository.countById(id) != 0) {
            throw new IllegalArgumentException("Advert with id = " + id + " was not deleted");
        }
    }
}
