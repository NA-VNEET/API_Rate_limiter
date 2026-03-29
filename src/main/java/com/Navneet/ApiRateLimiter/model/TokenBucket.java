package com.Navneet.ApiRateLimiter.model;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {
    private final int capacity;
    private final int refillRate;
    private AtomicInteger tokens;
    private long lastRefillTime;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicInteger(capacity);
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refill();

        if(tokens.get() > 0){
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTime;
        int tokenToAdd = (int)(elapsedTime / 1_000_000_000L * refillRate);

        if(tokenToAdd > 0){
            tokens.set(Math.min(tokens.get() + tokenToAdd, capacity));
            lastRefillTime = now;
        }
    }
}
