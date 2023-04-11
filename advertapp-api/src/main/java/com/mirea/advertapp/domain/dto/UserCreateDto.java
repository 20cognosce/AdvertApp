package com.mirea.advertapp.domain.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String firstName;
    private String lastName;
    private String phone;
    private char[] password;
    private String email;
}
