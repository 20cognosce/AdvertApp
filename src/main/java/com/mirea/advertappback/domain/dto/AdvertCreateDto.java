package com.mirea.advertappback.domain.dto;

import com.mirea.advertappback.domain.entity.Address;
import com.mirea.advertappback.domain.entity.Photo;
import com.mirea.advertappback.domain.entity.User;
import jakarta.persistence.*;
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
