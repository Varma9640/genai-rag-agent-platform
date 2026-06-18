# GenAI RAG Agent Platform

## Overview

GenAI RAG Agent Platform is an enterprise-grade Retrieval Augmented Generation (RAG) system built using Java 17, Spring Boot, ChromaDB, Ollama, Prometheus, and Grafana.

The platform enables users to upload PDF documents, generate embeddings, store vectors in ChromaDB, retrieve relevant context using hybrid search and re-ranking, and generate AI-powered responses through Large Language Models (LLMs).

---

## Features

* PDF Upload and Parsing
* Text Chunking
* Embedding Generation
* ChromaDB Vector Storage
* Hybrid Search (Vector + Keyword)
* Re-Ranking
* Session Memory
* Conversation History
* Agent Routing
* Multi-LLM Support
* LLM Fallback Mechanism
* Streaming Responses (SSE)
* Source Citation
* Prometheus Metrics
* Grafana Dashboards

---

## Architecture

User
→ REST API
→ Agent Router
→ RAG Engine
→ Hybrid Search
→ ChromaDB
→ LLM
→ Response
→ Prometheus
→ Grafana

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring AI
* Ollama
* ChromaDB
* H2 Database
* Maven
* Docker
* Prometheus
* Grafana

---

## APIs

### Document APIs

POST /api/v1/documents/upload

GET /api/v1/documents

GET /api/v1/documents/{id}

DELETE /api/v1/documents/delete/{id}

### RAG APIs

POST /api/v1/rag/ask

POST /api/v1/rag/debug

POST /api/v1/stream/stream

---

## Monitoring

Prometheus:

http://localhost:9090

Grafana:

http://localhost:3000

---

## Future Enhancements

* Redis Cache
* Kafka Integration
* Multi-Agent Orchestration
* Role-Based Security
* Cloud Deployment
