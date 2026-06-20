# GenAI RAG Agent Platform

## Overview

GenAI RAG Agent Platform is an enterprise-grade Retrieval Augmented Generation (RAG) system built using Java 17, Spring Boot, LangChain4j, Ollama, OpenAI, H2 Database, Prometheus, and Swagger.

The platform enables users to upload PDF documents, extract content, generate embeddings, perform similarity search, retrieve contextual information, and generate AI-powered responses using Large Language Models (LLMs).

The platform also supports conversation memory, query history, prompt auditing, agent orchestration, streaming responses, and fallback LLM mechanisms.

---

## Features

### Document Processing

* PDF Upload
* PDF Parsing
* Text Extraction
* Text Chunking
* Document Management

### RAG Capabilities

* Similarity Search
* Context Retrieval
* Context Builder
* Retrieval Augmented Generation
* Debug Retrieval API

### AI Capabilities

* Multi-LLM Support
* Ollama Integration
* OpenAI Integration
* LLM Fallback Mechanism
* Agent Routing
* Agent Orchestration

### Conversation Management

* Session Memory
* Conversation History
* Query History
* Prompt Audit

### Streaming

* Server Sent Events (SSE)
* Real-Time AI Response Streaming

### Monitoring

* Spring Boot Actuator
* Prometheus Metrics

### API Documentation

* Swagger OpenAPI

---

## Architecture

User

↓

REST API Layer

↓

Agent Router

↓

RAG Engine

↓

Similarity Search

↓

Context Builder

↓

LLM Provider

↓

Response Generation

↓

Query History

↓

Prompt Audit

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Data JPA
* Spring Validation

### AI & LLM

* LangChain4j
* Ollama
* OpenAI

### Database

* H2 Database

### Build Tool

* Maven

### Testing

* JUnit 5
* Mockito
* MockMvc

### Monitoring

* Spring Boot Actuator
* Prometheus

### Documentation

* Swagger OpenAPI

---

## APIs

### Chat APIs

POST /api/v1/chat/generate

---

### RAG APIs

POST /api/v1/rag/ask

POST /api/v1/rag/debug

---

### Agent APIs

POST /api/v1/agent/execute

---

### Streaming APIs

POST /api/v1/stream/stream

---

### Document APIs

POST /api/v1/documents/upload

DELETE /api/v1/documents/delete/{id}

GET /api/v1/documents

GET /api/v1/documents/{id}

DELETE /api/v1/documents/{id}

---

### Conversation APIs

GET /api/v1/conversations

GET /api/v1/conversations/{id}

DELETE /api/v1/conversations/{id}

---

### Metrics APIs

GET /api/v1/metrics

---

## Monitoring

### Actuator

http://localhost:8080/actuator

### Prometheus

http://localhost:9090

---

## Testing

* JUnit 5
* Mockito
* MockMvc

### Test Coverage

* 87% Class Coverage
* 82% Line Coverage

---

## Future Enhancements

* JWT Authentication
* Spring Security
* Role-Based Access Control
* Redis Cache
* Kafka Integration
* PostgreSQL Support
* Docker Compose
* Cloud Deployment
* Multi-Agent Orchestration

---

## Author

Developed using Java, Spring Boot, LangChain4j, Ollama, OpenAI, JPA, and modern RAG architecture principles.