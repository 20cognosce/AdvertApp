package com.mirea.advertapp.controller;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.dto.AdvertDto;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/adverts")
@RestController
public class AdvertController {

    private final AdvertService advertService;

    @GetMapping
    public List<AdvertDto> getAll() {
        return advertService.getAllDto();
    }

    @GetMapping("/find")
    public List<AdvertDto> findAllByTitle(@RequestParam(value = "title") String title) {
        return advertService.findAllByTitle(title);
    }

    @GetMapping("/count")
    public Integer getAllCount() {
        return advertService.getAll().size();
    }

    @GetMapping("/{id}")
    public AdvertDto getById(@PathVariable("id") Long id) {
        return advertService.getByIdDto(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdvertDto create(@RequestBody AdvertCreateDto advertCreateDto) {
        Advert advert = advertService.create(advertCreateDto);
        return advertService.convertToDto(advert);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        advertService.deleteById(id);
        return ResponseEntity.ok("DELETED");
    }
}
