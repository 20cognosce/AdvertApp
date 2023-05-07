package com.mirea.advertapp.domain.dto;

public record AuthDto(Long id, String email, String firstName, String lastName, String role, String status) {
}
