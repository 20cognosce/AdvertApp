package com.mirea.advertapp.domain.dto;

import com.mirea.advertapp.domain.entity.Address;
import com.mirea.advertapp.domain.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertCreateDto {

    private String title;
    private String description;
    private Long userId;
    private Address address;
    private List<Photo> photos;
}
