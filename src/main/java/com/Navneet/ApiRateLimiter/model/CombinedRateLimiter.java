package com.Navneet.ApiRateLimiter.model;

public class CombinedRateLimiter {
    private final TokenBucket tokenBucket;
    private final SlidingWindowLimiter slidingWindow;

    public CombinedRateLimiter(int capacity, int refillRate,
                               int maxRequests, long windowSize) {

        this.tokenBucket = new TokenBucket(capacity, refillRate);
        this.slidingWindow = new SlidingWindowLimiter(maxRequests, windowSize);
    }

    public boolean allowRequest() {

        // Step 1: Token Bucket (burst control)
        if (!tokenBucket.allowRequest()) {
            return false;
        }
        // Step 2: Sliding Window (strict limit)
        return slidingWindow.allowRequest();
    }
}
