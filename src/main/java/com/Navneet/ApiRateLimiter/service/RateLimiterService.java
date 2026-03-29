package com.Navneet.ApiRateLimiter.service;

import com.Navneet.ApiRateLimiter.model.TokenBucket;
import org.apache.catalina.util.RateLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final ConcurrentHashMap<String, TokenBucket> userBuckets = new ConcurrentHashMap<>();

    private static final int CAPACITY = 5;
    private static final int  REFILL_RATE = 5;

    public boolean allowRequest(String userId) {
        userBuckets.putIfAbsent(userId, new TokenBucket(CAPACITY, REFILL_RATE));

        TokenBucket tokenBucket = userBuckets.get(userId);
        return tokenBucket.allowRequest();
    }
}
