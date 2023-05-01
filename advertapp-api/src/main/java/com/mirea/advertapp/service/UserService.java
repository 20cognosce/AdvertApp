package com.mirea.advertapp.service;

import com.mirea.advertapp.controller.exception.EntityNotFoundException;
import com.mirea.advertapp.controller.mapper.UserMapper;
import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import com.mirea.advertapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        user.setHashPassword(encodePassword(userCreateDto.getPassword()));
        return userRepository.save(user);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public String encodePassword(char[] password) {
        return passwordEncoder.encode(new String(password));
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

    public List<UserDto> findAllByEmail(String email) {
        var foundUsers = userRepository.findAllByEmailContainsIgnoreCase(email);
        return userMapper.userListToUserDtoList(foundUsers);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " not found"));
    }

    public UserDto getByIdDto(Long id) {
        return userMapper.userToUserDto(getById(id));
    }

    public User getByEmail(String email) {
        return getByEmailOptional(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public Optional<User> getByEmailOptional(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto convertToDto(User user) {
        return userMapper.userToUserDto(user);
    }

    public Optional<User> validateAuthCandidate(String email, char[] password) {
        try {
            User user = getByEmail(email);
            if (passwordEncoder.matches(new String(password), user.getHashPassword())) {
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public UserDto setUserStatus(Long id, UserAccountStatus status) {
        var user = getById(id);
        user.setStatus(status);
        userRepository.save(user);
        return getByIdDto(id);
    }
}
