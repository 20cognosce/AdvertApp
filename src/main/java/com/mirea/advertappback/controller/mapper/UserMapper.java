package com.mirea.advertappback.controller.mapper;

import com.mirea.advertappback.domain.dto.UserCreateDto;
import com.mirea.advertappback.domain.dto.UserDto;
import com.mirea.advertappback.domain.entity.User;
import com.mirea.advertappback.domain.entityenum.Role;
import com.mirea.advertappback.domain.entityenum.UserAccountStatus;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {

        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .phone(userCreateDto.getPhone())
                .email(userCreateDto.getEmail())
                .hashPassword(new BCryptPasswordEncoder(16)
                        .encode(new String(userCreateDto.getPassword())))
                .role(Role.USER)
                .status(UserAccountStatus.ACTIVE)
                .build();
    }

    public abstract UserDto userToUserDto(User user);

    public abstract List<UserDto> userListToUserDtoList(List<User> users);
}
