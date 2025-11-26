package com.jiten.ratelimiter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ratelimiter")
public class RateLimiterProperties {
    private int limit = 5;
    private int windowSeconds = 60;
    private String keyHeader = "X-API-KEY";
    private boolean enabled = true;

    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }
    public int getWindowSeconds() { return windowSeconds; }
    public void setWindowSeconds(int windowSeconds) { this.windowSeconds = windowSeconds; }
    public String getKeyHeader() { return keyHeader; }
    public void setKeyHeader(String keyHeader) { this.keyHeader = keyHeader; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
