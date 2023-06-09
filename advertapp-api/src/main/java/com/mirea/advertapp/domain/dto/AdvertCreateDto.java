package com.mirea.advertapp.domain.dto;

import com.mirea.advertapp.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertCreateDto {

    private String title;
    private String description;
    private Long userId;
    private Address address;
}
