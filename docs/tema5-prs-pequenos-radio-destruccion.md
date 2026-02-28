# Tema 5: PRs pequeños — reduce el radio de destrucción

> Documento base: [good-practices.md](../good-practices.md)

Los PRs pequeños no son “PRs incompletos”. Son entregas intencionales, con valor, que reducen el impacto de errores y mejoran la capacidad de reacción del equipo.

## ¿Qué significa “radio de destrucción”?

El **radio de destrucción** (blast radius) es el alcance potencial del daño si algo sale mal:

- cuántos módulos toca un cambio,
- cuántos flujos de usuario puede romper,
- cuánto cuesta diagnosticar y revertir.

Cuanto más grande y mezclado es un PR, mayor es ese radio.

---

## Paso 1) Define una sola intención por PR

### Concepto
Cada PR debe tener un propósito único y verificable. Si tiene varios objetivos, se vuelve más difícil revisar, probar y revertir.

### Checklist
- ¿Puedo explicar el cambio en 1 frase?
- ¿El título del PR refleja una sola intención?
- ¿Si revierte este PR, revierte “una cosa” y no media funcionalidad?

### Ejemplo
**Malo:** “Refactor de tareas + nuevo endpoint + ajustes de seguridad + cambios de UI”.  
**Bueno:** “Agregar endpoint `PATCH /api/subtasks/{id}` para actualizar estado”.

---

## Paso 2) Separa refactor de funcionalidad

### Concepto
Mezclar limpieza técnica con nueva lógica funcional confunde al revisor: no queda claro qué parte cambia comportamiento real.

### Estrategia
1. PR A: renombres, extracción de métodos, simplificación (sin cambiar comportamiento).
2. PR B: lógica nueva sobre la base ya limpia.

### Ejemplo práctico
- **PR A:** mover validaciones repetidas de controladores a un helper compartido.
- **PR B:** introducir regla nueva: “no permitir subtareas en `DONE` si la tarea está `OPEN`”.

---

## Paso 3) Trocea por capas o por verticales pequeñas

### Opción A — Por capas (útil en cambios transversales)
1. Modelo/entidades.
2. Servicios de dominio.
3. API/controladores.
4. Cliente/UI.

### Opción B — Por verticales (útil en producto)
Entrega un flujo completo pero pequeño: “crear subtarea con título” y luego “editar subtarea”, etc.

### Regla útil
Prefiere cortes que sean:
- desplegables,
- compatibles hacia atrás,
- fáciles de probar de forma aislada.

---

## Paso 4) Usa feature flags cuando convenga

### Concepto
Una **feature flag** permite fusionar cambios sin exponerlos aún a todos los usuarios. Reduce riesgo en activación.

### Cuándo usarla
- Cambios de comportamiento difíciles de revertir.
- Funcionalidad que requiere varias iteraciones para completarse.
- Integraciones externas con incertidumbre.

### Mini flujo
1. Introducir código bajo flag en OFF.
2. Integrar y estabilizar.
3. Activar progresivamente (interno → porcentaje de usuarios → total).
4. Retirar la flag cuando ya no se necesite.

---

## Paso 5) Valida cada corte

### Concepto
Un PR pequeño solo es útil si está **terminado y validado**. “Pequeño” no significa “sin pruebas”.

### Validaciones mínimas por PR
- Compila.
- Pasa tests unitarios relevantes.
- Si aplica, pruebas de integración/smoke.
- No rompe compatibilidad de contratos existentes.

### Ejemplo
Si cambias solo mapeo DTO → dominio, el PR debe incluir pruebas de mapeo y asegurar que respuestas API previas no cambian accidentalmente.

---

## Paso 6) Describe impacto y plan de rollback

### Concepto
Incluso cambios pequeños pueden fallar. Debe quedar claro qué mirar post-merge y cómo volver atrás rápido.

### Plantilla breve para PR
- **Impacto esperado:** qué mejora y dónde.
- **Riesgo principal:** qué podría romperse.
- **Señal de éxito:** métrica/log/endpoint a verificar.
- **Rollback:** `revert commit` o desactivar flag X.

### Ejemplo
- Impacto: menor latencia al listar tareas.
- Riesgo: paginación inconsistente en filtros.
- Señal: tiempo p95 < 200ms en endpoint `GET /api/tasks`.
- Rollback: revertir commit `abc123`.

---

## Paso 7) Solicita revisión temprana

### Concepto
La calidad de revisión cae cuando el tamaño crece. PRs moderados aceleran feedback y reducen defectos en producción.

### Prácticas recomendadas
- Mantén PRs en un rango manejable (orientativo: 100–300 líneas útiles).
- Incluye contexto: problema, decisión técnica, límites del cambio.
- Pide revisión cuando el PR está listo para merge, no “a medio cocinar”.

---

## Ejemplo completo: dividir un cambio grande en PRs pequeños

Objetivo grande: “Mejorar gestión de subtareas y estado”.

### Plan malo (1 único PR gigante)
- Cambia entidades, servicios, controladores, validaciones, tests y docs de una vez.

### Plan bueno (4 PRs)
1. **PR 1 (refactor):** extraer validaciones comunes de subtareas.
2. **PR 2 (dominio):** introducir reglas de transición de estados.
3. **PR 3 (API):** exponer endpoint de actualización de estado con errores claros.
4. **PR 4 (observabilidad/docs):** métricas, logs y actualización de documentación.

Cada PR es revisable, testeable y reversible por separado.

---

## Errores frecuentes a evitar

- “Ya que estoy…” (scope creep).
- Mezclar cambios mecánicos con cambios semánticos.
- No documentar riesgos por ser “un PR pequeño”.
- Dividir en PRs artificiales que no compilan por sí solos.

---

## Resumen operativo

Si quieres reducir el radio de destrucción:

1. una intención por PR,
2. refactor separado de funcionalidad,
3. cortes desplegables,
4. activación controlada con flags cuando aplique,
5. validación completa por corte,
6. impacto + rollback explícitos,
7. revisión temprana y contextualizada.

Volver al documento base: [good-practices.md](../good-practices.md)
