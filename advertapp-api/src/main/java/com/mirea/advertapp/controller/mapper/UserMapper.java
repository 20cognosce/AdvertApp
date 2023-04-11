package com.mirea.advertapp.controller.mapper;

import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring", uses=PasswordEncoder.class)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {

        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .phone(userCreateDto.getPhone())
                .email(userCreateDto.getEmail())
                .hashPassword(passwordEncoder.encode(new String(userCreateDto.getPassword())))
                .role(Role.USER)
                .status(UserAccountStatus.ACTIVE)
                .build();
    }

    public abstract UserDto userToUserDto(User user);

    public abstract List<UserDto> userListToUserDtoList(List<User> users);
}
