package com.mirea.advertapp.security;

import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getByEmail(email);
        List<SimpleGrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return mapUserToCustomUserDetails(user, authorities);
    }

    private UserDetailsImpl mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.setId(user.getId());
        userDetailsImpl.setEmail(user.getEmail());
        userDetailsImpl.setHashPassword(user.getHashPassword());
        userDetailsImpl.setAuthorities(authorities);
        userDetailsImpl.setUser(user);
        return userDetailsImpl;
    }
}