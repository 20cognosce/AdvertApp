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
    private String email;
    private char[] password;
}
