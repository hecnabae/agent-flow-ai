# Tema 1: Responsabilidad única (SRP)

[← Volver al índice de buenas prácticas](./good-practices.md)

El **Principio de Responsabilidad Única (Single Responsibility Principle)** indica que una clase, módulo o función debe tener **un único motivo de cambio**.

No significa “hacer clases pequeñas sin criterio”, sino **agrupar comportamiento coherente** y separar aquello que cambia por razones distintas.

## ¿Por qué importa?

Cuando una pieza de código mezcla responsabilidades:

- Es más difícil de entender.
- Aumenta el riesgo de romper algo al modificarla.
- Complica las pruebas unitarias.
- Genera acoplamiento innecesario.

En cambio, con SRP:

- El código es más legible.
- Los cambios son más localizados.
- Las pruebas se vuelven más simples.
- Se facilita reutilización y mantenimiento.

## Cómo detectar violaciones de SRP

Señales típicas:

1. Una clase “hace de todo” (validación, negocio, persistencia, formateo, logging, etc.).
2. Métodos muy largos con bloques que podrían vivir en componentes distintos.
3. Muchos `if` por tipos/escenarios heterogéneos.
4. Cambios frecuentes por razones no relacionadas (por ejemplo, reglas de negocio y formato de respuesta).

## Ejemplo 1 (malo): servicio con responsabilidades mezcladas

```java
public class TaskService {
    public TaskResponse createTask(CreateTaskRequest request) {
        // 1) Validación
        if (request.title() == null || request.title().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        // 2) Regla de negocio
        TaskDocument task = new TaskDocument();
        task.setTitle(request.title().trim());
        task.setStatus(TaskStatus.PENDING);

        // 3) Persistencia
        task = taskRepository.save(task);

        // 4) Transformación a DTO
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }
}
```

Problema: esta clase está mezclando responsabilidades de validación, negocio, persistencia y mapeo.

## Ejemplo 1 (mejor): separación de responsabilidades

```java
public class TaskService {
    private final TaskValidator validator;
    private final TaskFactory factory;
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskResponse createTask(CreateTaskRequest request) {
        validator.validateCreate(request);
        TaskDocument task = factory.newPendingTask(request.title());
        TaskDocument saved = repository.save(task);
        return mapper.toResponse(saved);
    }
}
```

En este enfoque:

- `TaskValidator` valida entradas.
- `TaskFactory` crea entidades con reglas de inicialización.
- `TaskRepository` persiste.
- `TaskMapper` transforma a DTO.

Cada pieza cambia por un motivo diferente y queda mejor aislada.

## Ejemplo 2: SRP en controladores

Un controlador debería enfocarse en **orquestar HTTP** (request/response, status codes), no en lógica de dominio compleja.

✅ Correcto:

- Recibe DTO de entrada.
- Llama a servicio de aplicación.
- Devuelve respuesta y código HTTP.

❌ Evitar:

- Reglas de negocio avanzadas dentro del controlador.
- Lógica de persistencia directa en endpoints.

## Consejos prácticos para aplicarlo

1. **Empieza por extraer validación** a un componente dedicado.
2. **Aísla el mapeo DTO ↔ dominio** en mappers.
3. **Mantén servicios de aplicación delgados**: deben orquestar, no hacerlo todo.
4. **Refactoriza en pasos pequeños** con pruebas automáticas.
5. **Usa nombres orientados a intención** (`TaskValidator`, `TaskMapper`, `TaskFactory`).

## Errores comunes al aplicar SRP

- Fragmentar en exceso (demasiadas clases triviales sin valor).
- Mover código sin cambiar dependencias (separa “archivos”, pero no responsabilidades reales).
- Ignorar el dominio: SRP debe alinearse con casos de uso y reglas de negocio.

## Resumen

Aplicar SRP no busca “microclases”, sino que cada componente tenga una responsabilidad clara y cohesionada.

Si dudas, hazte esta pregunta: **“¿Por qué motivos podría cambiar este código?”**
Si hay varios motivos no relacionados, probablemente conviene separar responsabilidades.

[Ver índice de buenas prácticas](./good-practices.md)
