package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = AdvertMapper.class)
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

    @Mapping(target = "adverts", qualifiedBy = AdvertWithoutUser.class)
    public abstract UserDto userToUserDto(User user);

    @Named("noAdverts")
    @Mapping(target = "adverts", ignore = true)
    public abstract UserDto userToUserDtoWithoutAdverts(User user);

    public List<UserDto> userListToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }
}
