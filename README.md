# simple-ratelimiter

A small Spring Boot rate limiter starter (fixed window) + demo.

## Quick run (from repo root)

1. Build:
   mvn clean install -DskipTests

2. Run demo:
   mvn -pl demo spring-boot:run

3. Test:
   curl -H "X-API-KEY: user1" http://localhost:8080/hello

The demo uses a fixed-window in-memory limiter. For production use replace the service with a Redis backed implementation.
