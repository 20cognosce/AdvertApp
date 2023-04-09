package com.mirea.advertappback.domain.dto;

import com.mirea.advertappback.domain.entity.Advert;
import com.mirea.advertappback.domain.entityenum.Role;
import com.mirea.advertappback.domain.entityenum.UserAccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Role role;
    private UserAccountStatus status;
    private List<AdvertDto> adverts;
}
