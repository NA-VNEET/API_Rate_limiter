package com.Navneet.ApiRateLimiter.model;


import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowLimiter {

    private final int maxRequests;
    private final long windowSize;

    private final ConcurrentLinkedQueue<Long> timestamps = new ConcurrentLinkedQueue<>();

    public SlidingWindowLimiter(int maxRequests, long windowSize) {
        this.maxRequests = maxRequests;
        this.windowSize = windowSize;
    }

    public boolean allowRequest() {
        long now = System.currentTimeMillis();
        //remove old request
        while (!timestamps.isEmpty() && now - timestamps.peek() > windowSize) {
            timestamps.poll();
        }
        if (timestamps.size() < maxRequests) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}