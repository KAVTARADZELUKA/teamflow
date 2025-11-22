# 🧩 TeamFlow

**TeamFlow** is a modern **Task & Project Management** platform built with **Spring Boot (JPA)** and **Vue 3**.  
It is designed as a **senior-level backend showcase project** to demonstrate architecture, scalability, and modern development practices.

---

## 🚀 Objectives
This project aims to demonstrate:
- Scalable API design using Spring Boot (JPA/Hibernate)
- Domain-driven modular architecture
- Secure JWT-based authentication and authorization
- Event-driven communication (Kafka, async processing)
- CI/CD automation and observability (Prometheus, Grafana)

---

## 🧱 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend** | Java 21, Spring Boot 3.3, Spring Web (JPA/Hibernate), Spring Security, PostgreSQL |
| **Frontend** | Vue 3, TypeScript, Vite, TailwindCSS |
| **Infrastructure** | Docker Compose, GitHub Actions, Prometheus, Grafana |

---

## ⚙️ Local Development

### 1. Run PostgreSQL
Make sure Docker is running, then start the local PostgreSQL container:

```bash
docker-compose up -d postgres
