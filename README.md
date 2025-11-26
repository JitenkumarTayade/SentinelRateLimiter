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

## 📥 Add to Your Maven Project (via JitPack)

Follow these 3 quick steps to integrate **SentinelRateLimiter** into your Spring Boot project.

---
## 📥 Add to Your Maven Project (via JitPack)
Follow these 3 quick steps to integrate **SentinelRateLimiter** into your Spring Boot project.
🟩 Step 1 — Add JitPack repository

```diff
 <repositories>
     <repository>
         <id>jitpack.io</id>
         <url>https://jitpack.io</url>
     </repository>
 </repositories>

🟩 Step 2 — Add the dependency
 <dependency>
     <groupId>com.github.JitenkumarTayade</groupId>
     <artifactId>SentinelRateLimiter</artifactId>
     <version>v0.0.1</version>
 </dependency>

🟩 Step 3 — Optional configuration (YAML)
 ratelimiter:
   enabled: true
   limit: 5
   window-seconds: 30
   key-header: X-API-KEY

---

## 📷 Snapshots

Below are visual snapshots showing the project in action.  
These help developers quickly understand how the rate limiter behaves during runtime.

### 1️⃣ IDE — Application Startup (Spring Boot + Tomcat Running)
![IDE Startup](https://raw.githubusercontent.com/JitenkumarTayade/SentinelRateLimiter/main/assest/intelliJ server started , project running status.png)

---

### 2️⃣ Terminal — Maven Build Success
![Build Success](https://raw.githubusercontent.com/JitenkumarTayade/SentinelRateLimiter/main/assest/2.png)

---

### 3️⃣ Pre-Result — Requests Within Allowed Limit
![Allowed Requests](https://raw.githubusercontent.com/JitenkumarTayade/SentinelRateLimiter/main/assest/1. Pre-result - without crossing set limit.png)

---

### 4️⃣ Result — Rate Limit Exceeded (HTTP 429 Snapshot)
![Rate Limit Exceeded](https://raw.githubusercontent.com/JitenkumarTayade/SentinelRateLimiter/main/assest/2. Result - limit exceed snapshot.png)

---

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
