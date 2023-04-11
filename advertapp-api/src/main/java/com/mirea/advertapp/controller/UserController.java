package com.mirea.advertapp.controller;

import com.mirea.advertapp.controller.mapper.UserMapper;
import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllDto();
    }

    @PostMapping
    public ResponseEntity<UserDto> create(UserCreateDto userCreateDto) {
        User user = userService.create(userCreateDto);
        return ResponseEntity
                .status(201)
                .body(userMapper.userToUserDto(user));
    }
}
