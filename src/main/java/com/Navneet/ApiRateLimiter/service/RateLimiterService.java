package com.Navneet.ApiRateLimiter.service;

import com.Navneet.ApiRateLimiter.model.CombinedRateLimiter;
import com.Navneet.ApiRateLimiter.model.TokenBucket;
import org.apache.catalina.util.RateLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final ConcurrentHashMap<String, CombinedRateLimiter> userLimiters = new ConcurrentHashMap<>();

    public boolean allowRequest(String userId) {
        CombinedRateLimiter limiter = userLimiters.computeIfAbsent(
                userId,
                k -> new CombinedRateLimiter(
                        10,5,
                        5,1000
                )
        );
        return limiter.allowRequest();
    }
}
