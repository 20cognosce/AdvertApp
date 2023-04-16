package com.mirea.advertapp.domain.dto;

import com.mirea.advertapp.domain.entity.Address;
import com.mirea.advertapp.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime published;
    private LocalDateTime updated;
    private UserDto user;
    private Address address;
    private List<ImageDto> images;
}
