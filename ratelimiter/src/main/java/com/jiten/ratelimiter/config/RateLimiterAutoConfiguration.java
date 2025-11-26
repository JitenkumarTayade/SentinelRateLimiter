package com.jiten.ratelimiter.config;

import com.jiten.ratelimiter.filter.RateLimiterFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
@EnableConfigurationProperties(RateLimiterProperties.class)
@ConditionalOnProperty(prefix = "ratelimiter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RateLimiterAutoConfiguration {

    @Bean
    public RateLimiterFilter rateLimiterFilter(RateLimiterProperties properties) {
        return new RateLimiterFilter(properties);
    }

}
