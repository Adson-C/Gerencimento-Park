# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 3.3.5 REST API for parking management (Controle Estacionamento) using Java 17.

## Build & Run Commands

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Run tests
mvn test

# Run a specific test class
mvn test -Dtest=ClassName

# Package without running tests
mvn clean package -DskipTests
```

## Database

- **Development**: MySQL on localhost:3306
- **Database name**: park_api (auto-created if not exists)
- **Credentials**: park_api / parkapi123
- **Migrations**: Flyway (located in src/main/resources/db/migration)
- Server runs on port 8088

## Architecture

Standard Spring Boot layered architecture:

### Layer Structure
- **Entity** (`entity/`): JPA entities with Lombok annotations
- **Repository** (`repository/`): Spring Data JPA repositories
- **Service** (`service/`): Business logic with `@Transactional` annotations
- **Controller** (`web/controller/`): REST endpoints under `/api/v1/`
- **DTO** (`web/dto/`): Request/response DTOs
- **Mapper** (`web/dto/mapper/`): ModelMapper-based entity-DTO conversion

### Key Patterns
- Uses Lombok `@RequiredArgsConstructor` for constructor-based dependency injection
- Service layer methods annotated with `@Transactional` (readOnly=true for queries)
- DTOs for API input/output, not exposing entities directly in responses (except where legacy code remains)
- ModelMapper for entity-DTO conversion with custom PropertyMaps when needed
- Entity audit fields: dataCriacao, dataMotificacao, criadoPor, modificadoPor

### Configuration
- Timezone set to America/Sao_Paulo via SpringTimezoneConfig
- Locale set to pt_BR in application.properties
- SQL logging enabled with spring.jpa.show-sql=true

## Current Implementation

Usuario (User) CRUD operations with role-based access (ROLE_ADMIN, ROLE_CLIENTE).

Always use Context7 MCP when I need library/API documentation, code generation, setup or configuration steps without me having to explicitly ask.
