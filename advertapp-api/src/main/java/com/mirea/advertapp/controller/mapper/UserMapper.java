package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {

        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getUsername())
                .phone(userCreateDto.getPhone())
                .role(Role.USER)
                .status(UserAccountStatus.ACTIVE)
                .build();
    }

    public abstract UserDto userToUserDto(User user);

    public abstract List<UserDto> userListToUserDtoList(List<User> users);
}
