package com.mirea.advertappback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class HealthCheckController {

    @GetMapping("/check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
