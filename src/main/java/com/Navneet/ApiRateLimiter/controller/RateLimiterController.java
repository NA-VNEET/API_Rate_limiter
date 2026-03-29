package com.Navneet.ApiRateLimiter.controller;

import com.Navneet.ApiRateLimiter.service.RateLimiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimiterController {
    private final RateLimiterService rateLimiterService;
    public RateLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam String user) {
        if (rateLimiterService.allowRequest(user)) {
            return ResponseEntity.ok("User " + user + " is allowed");
        }
        return ResponseEntity.status(429).body("Rate limit exceeded");
    }
}
