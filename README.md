# agent-flow-ai

Plataforma de gestión de trabajo asistida por agentes para mantener el foco, automatizar subtareas y cuidar la salud arquitectónica.

## Estructura del repositorio

```
/apps
  /api        # Spring Boot 3 (Java 21) - servicios de tareas, subtareas y foco
```

## Desarrollo backend (Spring Boot)

### Requisitos

* Java 21
* Maven 3.9+
* MongoDB (local o remoto) – configurable vía `MONGODB_URI`

### Comandos útiles

```bash
# Ejecutar pruebas
mvn test

# Levantar la API
mvn -pl apps/api spring-boot:run
```

La API expone endpoints REST bajo `/api`, comenzando por la gestión de tareas y subtareas (`POST /api/tasks`, `GET /api/tasks`, `POST /api/tasks/{id}/subtasks`, `PATCH /api/subtasks/{id}`, etc.).
