package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
