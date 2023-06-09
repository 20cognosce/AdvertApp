package com.mirea.advertapp.controller;

import com.mirea.advertapp.domain.dto.AuthDto;
import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserLoginDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import com.mirea.advertapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody UserLoginDto loginDto) {
        Optional<User> userOptional = userService.validateAuthCandidate(loginDto.getEmail(), loginDto.getPassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getStatus() == UserAccountStatus.ACTIVE) {
                return ResponseEntity.ok(new AuthDto(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole().name(),
                        user.getStatus().name()));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthDto create(@RequestBody UserCreateDto userCreateDto) {
        User user = userService.create(userCreateDto);
        return new AuthDto(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                user.getStatus().name());
    }
}

