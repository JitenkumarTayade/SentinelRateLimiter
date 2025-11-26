package com.jiten.ratelimiter.filter;

import com.jiten.ratelimiter.config.RateLimiterProperties;
import com.jiten.ratelimiter.service.FixedWindowRateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RateLimiterFilter extends HttpFilter {
    private final RateLimiterProperties properties;
    private final FixedWindowRateLimiterService limiter;

    public RateLimiterFilter(RateLimiterProperties properties) {
        this.properties = properties;
        this.limiter = new FixedWindowRateLimiterService(properties.getLimit(), properties.getWindowSeconds());
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String key = req.getHeader(properties.getKeyHeader());
        if (key == null || key.isBlank()) {
            key = req.getRemoteAddr();
        }

        boolean allowed = limiter.tryConsume(key);
        if (!allowed) {
            res.setStatus(429);
            res.getWriter().write("Rate limit exceeded. Try later.");
            return;
        }
        chain.doFilter(req, res);
    }
}
