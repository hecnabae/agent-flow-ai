# Tema 7: Especificación primero cuando importa (SDD)

> Documento base relacionado: [`good-practices.md`](./good-practices.md)

## ¿Qué significa SDD?

**SDD (Specification-Driven Development)** significa definir primero el comportamiento esperado del sistema (qué debe hacer y bajo qué condiciones) y después implementar el código.

No reemplaza a las pruebas; las complementa. La especificación actúa como contrato compartido entre producto, negocio y desarrollo.

## ¿Cuándo “importa” aplicar SDD?

SDD aporta más valor cuando hay:

- Reglas de negocio críticas (pagos, estados legales, facturación).
- Integraciones entre equipos o servicios (APIs públicas o internas).
- Riesgo alto de regresión por ambigüedad funcional.
- Necesidad de auditoría y trazabilidad de decisiones.

## Beneficios prácticos

- Reduce malentendidos antes de escribir código.
- Evita retrabajo por cambios tardíos de criterios.
- Mejora la calidad de diseño (interfaces más claras).
- Acelera revisiones de código al tener criterios explícitos.

---

## Aplicación de **todos los pasos** de `good-practices.md` al tema 7

A continuación se aplica, uno por uno, el flujo completo definido en el documento original.

### Paso 1) Contexto y objetivo

**Qué hacer:** describir el problema y el resultado esperado en términos de negocio y usuario.

**Plantilla breve:**

- Contexto actual:
- Problema observado:
- Objetivo medible:

**Ejemplo (API de tareas):**

- Contexto actual: la API permite crear subtareas.
- Problema observado: se crean subtareas duplicadas por nombre en la misma tarea.
- Objetivo medible: impedir duplicados por `taskId + nombreNormalizado` con respuesta de error clara.

### Paso 2) Criterios de aceptación

**Qué hacer:** convertir expectativas en condiciones verificables.

**Buenas prácticas:**

- Escribir criterios en formato “Dado / Cuando / Entonces”.
- Incluir casos felices y casos límite.
- Evitar lenguaje ambiguo (“rápido”, “correcto”).

**Ejemplo:**

1. **Dado** una tarea existente, **cuando** intento crear una subtarea con nombre único, **entonces** la API responde `201`.
2. **Dado** una tarea existente, **cuando** intento crear una subtarea con nombre ya existente (ignorando mayúsculas), **entonces** la API responde `409`.
3. **Dado** una tarea inexistente, **cuando** intento crear subtarea, **entonces** la API responde `404`.

### Paso 3) Especificación

**Qué hacer:** definir el contrato antes de implementar.

**Qué debe incluir la especificación:**

- Entradas válidas e inválidas.
- Reglas de negocio.
- Estructura de respuesta y códigos de error.
- Casos límite y comportamiento esperado.

**Ejemplo de especificación resumida:**

- Endpoint: `POST /api/tasks/{id}/subtasks`
- Regla principal: `name` debe ser único por tarea tras normalizar (`trim`, minúsculas).
- Errores:
  - `404 TASK_NOT_FOUND`
  - `409 SUBTASK_NAME_ALREADY_EXISTS`
- Invariante: no se modifica una subtarea existente en caso de conflicto.

### Paso 4) Plan de implementación

**Qué hacer:** dividir la ejecución en cambios pequeños y reversibles.

**Secuencia recomendada:**

1. Añadir validación de unicidad en capa de dominio/repositorio.
2. Ajustar servicio para mapear conflicto a excepción de negocio.
3. Exponer error REST (`409`) en controlador/handler.
4. Añadir pruebas unitarias e integración.
5. Documentar en README/contrato API.

### Paso 5) Verificación

**Qué hacer:** comprobar que la implementación cumple los criterios de aceptación.

**Checklist mínimo:**

- Pruebas unitarias para reglas de negocio.
- Pruebas de integración de endpoint.
- Validación manual rápida de casos críticos (si aplica).

**Matriz ejemplo (criterio → evidencia):**

- Criterio 1 → test `createSubtask_returns201_whenUniqueName`.
- Criterio 2 → test `createSubtask_returns409_whenDuplicatedName`.
- Criterio 3 → test `createSubtask_returns404_whenTaskMissing`.

### Paso 6) Evidencia y documentación

**Qué hacer:** dejar trazabilidad para mantenimiento futuro.

**Qué registrar:**

- Qué cambió y por qué.
- Qué alternativas se descartaron.
- Qué riesgos quedan abiertos.
- Ejemplos de uso para consumidores.

**Ejemplo de evidencia:**

- Decisión: unicidad case-insensitive por tarea.
- Alternativa descartada: permitir duplicados y deduplicar en UI (rechazada por inconsistencia entre clientes).
- Riesgo abierto: impacto en rendimiento para tareas con miles de subtareas (mitigar con índice compuesto).

---

## Ejemplo completo de mini-especificación (lista para ejecutar)

```md
Título: Evitar duplicados de subtareas por nombre en una tarea

Objetivo:
Reducir errores operativos y ambigüedad en tableros de trabajo.

Criterios de aceptación:
- Dado task existente, cuando nombre único, entonces 201.
- Dado task existente, cuando nombre duplicado case-insensitive, entonces 409.
- Dado task inexistente, cuando alta de subtarea, entonces 404.

Contrato:
POST /api/tasks/{taskId}/subtasks
Body: { "title": "string" }

Errores:
404 TASK_NOT_FOUND
409 SUBTASK_NAME_ALREADY_EXISTS

No funcionales:
- Tiempo p95 < 200ms
- Log estructurado con taskId y código de error
```

## Antipatrones a evitar

- Implementar primero y “documentar después”.
- Criterios de aceptación sin casos de error.
- Especificaciones demasiado abstractas que no permiten testear.
- Cambios grandes sin plan incremental.

## Resumen

Con SDD, la especificación no es burocracia: es una herramienta para reducir incertidumbre. Aplicar los 6 pasos de `good-practices.md` garantiza que el equipo comparta contexto, acuerde criterios verificables y deje evidencia útil para evolucionar el sistema.
