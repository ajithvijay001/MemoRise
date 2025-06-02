# 🧠 MemoRise – A Flashcard-Based Learning App

MemoRise is a flashcard-based learning application built with Java (Spring Boot) and MongoDB. It enables users to create decks, add flashcards, mark them as learned, like cards, and track progress. A clean REST API with JWT-based authentication is implemented, and a Vue.js frontend is in progress.

This is my first full-stack project and an ongoing hands-on learning journey. I’m actively adding new features like full-text search with Elasticsearch and a responsive UI.

---

## 🎯 Project Overview

MemoRise helps users to:
- Create decks for any subject or topic
- Add and manage flashcards
- Track learning progress (read/unread)
- Like or favorite cards
- Perform search (Elasticsearch integration in progress)

This project is designed with clean, modular code and follows RESTful best practices. It also demonstrates MongoDB aggregations and secure JWT-based user access.

---

## 🔒 Features

- ✅ JWT-based authentication
- ✅ Role-based access control (admin/user)
- ✅ Flashcard CRUD operations
- ✅ Toggle card read/unread status
- ✅ Like/unlike flashcards
- ✅ Deck progress tracking (MongoDB aggregation)
- ✅ RESTful API endpoints (Postman-ready)
- 🔄 Frontend in Vue.js (in progress)
- 🔍 ElasticSearch integration (planned)

---

## 💻 Tech Stack

- **Backend:** Java 21, Spring Boot 3
- **Security:** Spring Security, JWT
- **Database:** MongoDB (Atlas)
- **Frontend:** Vue.js (planned), HTML, CSS
- **Search:** Elasticsearch (to be added)
- **Tools:** Postman, Git, GitHub

---

## 🧩 Skills Demonstrated

- Backend API development with Spring Boot
- MongoDB document modeling and aggregation
- Secure login and role-based access using Spring Security & JWT
- RESTful API design and versioning
- Postman collection testing
- Planning and structuring a full-stack architecture
- Hands-on with Vue.js setup and basic frontend routing (in progress)
- Exploring Elasticsearch for full-text search integration

---

## 📦 Project Status

- 🟢 Core APIs are completed (deck & flashcard management, user auth)
- 🟡 Frontend is in progress using Vue.js
- 🟡 Elasticsearch integration is planned next
- 🔄 README and documentation are being updated as the project evolves

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MongoDB (local or Atlas)

### Steps to Run

```bash
git clone https://github.com/ajithvijay001/MemoRise.git
cd memorise
mvn spring-boot:run
