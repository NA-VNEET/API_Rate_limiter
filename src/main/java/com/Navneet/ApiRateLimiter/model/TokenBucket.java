package com.Navneet.ApiRateLimiter.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private final int capacity;
    private final int refillRate;
    private AtomicLong tokens;
    private AtomicLong lastRefillTime;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicLong(capacity);
        this.lastRefillTime = new AtomicLong(System.currentTimeMillis());
    }

    public boolean allowRequest() {
        refill();

        while(true){
            long currentToken = tokens.get();
            if(currentToken < 0){
                return false;
            }
            if(tokens.compareAndSet(currentToken, currentToken -1)){
                return true;
            }
        }
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long lastTime = lastRefillTime.get();
        long elapsedTime = now - lastTime;
        int tokenToAdd = (int)(elapsedTime / 1_000_000_000L * refillRate);

        if(tokenToAdd > 0){
            long newToken = Math.min(tokens.get() + tokenToAdd, capacity);
            if(lastRefillTime.compareAndSet(lastTime, now)){
                tokens.set(newToken);
            }
        }
    }
}
