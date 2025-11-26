# SentinelRateLimiter

[![](https://jitpack.io/v/JitenkumarTayade/SentinelRateLimiter.svg)](https://jitpack.io/#JitenkumarTayade/SentinelRateLimiter)

A lightweight, plug-and-play **Spring Boot Rate Limiter Starter** built using a **fixed-window algorithm**, designed for simplicity, quick integration, and clean auto-configuration.  
Includes a working **demo application** to help you test and understand usage instantly.

---

## 🚀 Features

- 🔌 Auto-configured rate-limiting filter (zero manual wiring)
- 🪪 Per-API-key or per-IP limiting
- ⏱ Configurable request limit & time window
- ⚡ In-memory implementation (fast + simple)
- 🔄 Easily extendable to Redis / Token-Bucket / Sliding Window
- 🧩 Packaged as a reusable **Spring Boot Starter**

---

## 📦 Project Structure

SentinelRateLimiter/
├── ratelimiter/ → Library module (Spring Boot starter)
├── demo/ → Demo Spring Boot app
├── pom.xml → Parent Maven module
└── README.md

yaml
Copy code

---

## 🛠 Quick Start (Run Locally)

### **1️⃣ Build the entire project**
```bash
mvn clean install -DskipTests
2️⃣ Run the demo application
bash
Copy code
mvn -pl demo spring-boot:run
3️⃣ Test the rate limiter
bash
Copy code
curl -H "X-API-KEY: user1" http://localhost:8080/hello
Expected:
First 5 requests → 200 OK

After limit exceeded → 429 Too Many Requests

📥 Add to Your Maven Project (via JitPack)
Step 1 — Add JitPack repository
xml
Copy code
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
Step 2 — Add the dependency
xml
Copy code
<dependency>
    <groupId>com.github.JitenkumarTayade</groupId>
    <artifactId>SentinelRateLimiter</artifactId>
    <version>v0.0.1</version>
</dependency>
Step 3 — Optional configuration
yaml
Copy code
ratelimiter:
  enabled: true
  limit: 5
  window-seconds: 30
  key-header: X-API-KEY


📷 Snapshots : 
1️⃣ IDE — Application Successfully Started (Tomcat running)
<img width="1920" height="1030" alt="intelliJ server started , project running status" src="https://github.com/user-attachments/assets/bb6acdb5-2fec-4916-bbbf-de51f693e425" />

2️⃣ Terminal — Build Success
<img width="1920" height="771" alt="4" src="https://github.com/user-attachments/assets/5825477c-bc7e-48f4-8cbb-d547abc75734" />

3️⃣ Result — Rate Limit Response (Browser/Terminal)
<img width="1920" height="1031" alt="2  Result - limit exceed snapshot" src="https://github.com/user-attachments/assets/ca8b8ec6-d0e0-463c-b59a-85a38e3c96da" />

🔬 How It Works (Deep Dive)
This starter registers an auto-configured servlet filter:

Extracts API key from header (default: X-API-KEY)

Falls back to client IP if no header provided

Uses a thread-safe fixed-window counter

Resets counts after configured window

Returns 429 when limit is crossed

The implementation uses:

ConcurrentHashMap

AtomicInteger

Fixed Window algorithm

Auto-configuration via spring.factories / AutoConfiguration.imports

🧱 Architecture Overview
sql
Copy code
Client → Filter → RateLimiterService → Controller
                     |
                     +--> Window store (in-memory)
You can easily swap in a Redis-backed store to support distributed systems.

📈 Roadmap
Redis backend support

Token bucket algorithm

Custom exception handler

Actuator metrics

Annotations support (@RateLimit)

GitHub Actions release pipeline

🤝 Contributing
Pull requests are welcome!
If you want to discuss a feature or enhancement, open an issue first.

📜 License
MIT License — feel free to use commercially.

👤 Author
Jitenkumar Tayade
Creator of SentinelRateLimiter — a clean, minimal, production-ready rate limiter starter for Spring Boot.
