package com.mirea.advertappback.service;

import com.mirea.advertappback.controller.mapper.UserMapper;
import com.mirea.advertappback.domain.dto.UserCreateDto;
import com.mirea.advertappback.domain.dto.UserDto;
import com.mirea.advertappback.domain.entity.User;
import com.mirea.advertappback.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User create(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllDto() {
        List<User> users = getAll();
        return userMapper.userListToUserDtoList(users);
    }
}
