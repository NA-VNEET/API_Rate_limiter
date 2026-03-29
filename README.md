# API Rate Limiter 

##  Overview

This project implements a **high-performance API Rate Limiter** using a hybrid approach combining:

* **Token Bucket Algorithm** (for burst handling)
* **Sliding Window Algorithm** (for strict rate enforcement)

The system is designed to handle **concurrent requests safely and efficiently** using **lock-free atomic operations**.

---

## ⚙️ Features

* ✅ Hybrid Rate Limiting (Token Bucket + Sliding Window)
* ✅ Thread-safe design using `AtomicLong` and `ConcurrentHashMap`
* ✅ Lock-free concurrency (CAS operations)
* ✅ Per-user rate limiting
* ✅ High scalability and performance
* ✅ Proper HTTP response handling (429 Too Many Requests)

---

## 🧠 Architecture

Client → Controller → Service → Rate Limiter (Token Bucket + Sliding Window)

---

## 🧩 Tech Stack

* Java
* Spring Boot
* Multithreading & Concurrency
* Atomic Operations (CAS)
* Concurrent Collections

---

## 🔄 How It Works

1. **Token Bucket**

   * Allows burst requests
   * Tokens are refilled over time

2. **Sliding Window**

   * Maintains request timestamps
   * Ensures strict request limits per time window

3. **Hybrid Flow**

```
Request → Token Bucket → Sliding Window → Allow / Reject
```

---

## 📁 Project Structure

```
rate-limiter/
 ├── controller/
 ├── service/
 ├── model/
 ├── RateLimiterApplication.java
```

---

## 🚀 Getting Started

### 1️⃣ Clone Repository

```
git clone https://github.com/your-username/api-rate-limiter.git
cd api-rate-limiter
```

### 2️⃣ Run Application

```
mvn spring-boot:run
```

### 3️⃣ Test API

```
http://localhost:8080/api/test?user=nano
```

---

## 🧪 Example Behavior

* First few requests → ✅ Allowed
* Exceed limit → ❌ Rate Limited (HTTP 429)

---



---
