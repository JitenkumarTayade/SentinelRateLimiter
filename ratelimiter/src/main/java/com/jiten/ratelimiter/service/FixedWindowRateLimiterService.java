package com.jiten.ratelimiter.service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiterService {

    private final int limit;
    private final int windowSeconds;

    private static class Window {
        volatile long windowStartEpoch;
        AtomicInteger count = new AtomicInteger(0);
    }

    private final Map<String, Window> map = new ConcurrentHashMap<>();

    public FixedWindowRateLimiterService(int limit, int windowSeconds) {
        this.limit = limit;
        this.windowSeconds = windowSeconds;
    }

    public boolean tryConsume(String key) {
        long now = Instant.now().getEpochSecond();
        Window w = map.computeIfAbsent(key, k -> {
            Window nw = new Window();
            nw.windowStartEpoch = now;
            nw.count.set(0);
            return nw;
        });

        synchronized (w) {
            if (now - w.windowStartEpoch >= windowSeconds) {
                w.windowStartEpoch = now;
                w.count.set(0);
            }
            int current = w.count.incrementAndGet();
            return current <= limit;
        }
    }

    public int getLimit() { return limit; }
    public int getWindowSeconds() { return windowSeconds; }
}
