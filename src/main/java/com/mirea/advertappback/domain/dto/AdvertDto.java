package com.mirea.advertappback.domain.dto;

import com.mirea.advertappback.domain.entity.Address;
import com.mirea.advertappback.domain.entity.Photo;
import com.mirea.advertappback.domain.entity.User;
import jakarta.persistence.*;
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
    private User user;
    private Address address;
    private List<Photo> photos; //TODO: fix to PhotoDto
}
