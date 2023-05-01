package com.mirea.advertapp.controller;

import com.mirea.advertapp.domain.dto.AuthDto;
import com.mirea.advertapp.domain.dto.UserCreateDto;
import com.mirea.advertapp.domain.dto.UserDto;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import com.mirea.advertapp.security.UserDetailsImpl;
import com.mirea.advertapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllDto();
    }

    @GetMapping("/find")
    public List<UserDto> findAllByEmail(@RequestParam(value = "email", required = false) String email) {
        return userService.findAllByEmail(email);
    }

    @GetMapping("/count")
    public Integer getAllCount() {
        return userService.getAll().size();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getByIdDto(id);
    }

    @GetMapping(value = "/me")
    public UserDto getUserByAuth(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.convertToDto(userDetails.getUser());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthDto create(@RequestBody UserCreateDto userCreateDto) {
        User user = userService.create(userCreateDto);
        return new AuthDto(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                user.getStatus().name());
    }

    @PostMapping("/delete/{id}")
    public UserDto deleteUser(@PathVariable("id") Long id) {
        return userService.setUserStatus(id, UserAccountStatus.DELETED);
    }

    @PostMapping("/activate/{id}")
    public UserDto activateUser(@PathVariable("id") Long id) {
        return userService.setUserStatus(id, UserAccountStatus.ACTIVE);
    }
}
