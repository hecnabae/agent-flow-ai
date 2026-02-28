# Tema 10: Estandariza en equipo — checklist y definición de “Done”

> Documento derivado de las pautas de [`good-practices.md`](./good-practices.md).

## 1) ¿Qué significa “estandarizar en equipo”?

Estandarizar en equipo consiste en acordar **cómo trabajamos**, **qué verificamos** y **cuándo consideramos una tarea finalizada** para que los resultados sean consistentes, mantenibles y predecibles.

No se trata de burocracia, sino de:

- Reducir errores repetitivos.
- Evitar malentendidos entre desarrollo, QA y producto.
- Acelerar revisiones y entregas.
- Mantener una calidad homogénea aunque cambien las personas del equipo.

---

## 2) Conceptos clave

### Checklist de equipo

Una checklist es una lista breve y verificable de pasos obligatorios antes de mover una tarea a la siguiente fase (code review, QA, release, etc.).

**Características de una buena checklist:**

- Es concreta y accionable.
- Se puede verificar objetivamente (sí/no).
- Está adaptada al flujo real del equipo.
- Se revisa y mejora periódicamente.

### Definición de Done (DoD)

La Definición de Done es el acuerdo explícito de condiciones mínimas para considerar una historia/tarea como terminada.

La DoD responde: **“¿Qué debe cumplirse para que todos podamos afirmar que esto está realmente hecho?”**

---

## 3) Pasos del proceso (alineado con good-practices)

A continuación se presenta el flujo completo para aplicar estándar de equipo con checklist + DoD de forma práctica.

### Paso 1 — Alinear alcance y criterios antes de implementar

Antes de escribir código:

1. Confirmar objetivo funcional de la tarea.
2. Definir criterios de aceptación con negocio/producto.
3. Identificar riesgos técnicos y dependencias.
4. Acordar qué evidencia de validación será necesaria.

**Ejemplo:**
Si la tarea es “crear endpoint de subtareas”, el criterio no es solo “compila”, sino también “valida datos, persiste correctamente, y devuelve errores claros”.

### Paso 2 — Definir checklist operativa por etapa

Separar la checklist por bloques evita olvidos:

- **Implementación:** convenciones, validaciones, manejo de errores.
- **Calidad técnica:** tests, cobertura mínima, casos borde.
- **Seguridad:** validación de input, permisos, datos sensibles.
- **Documentación:** cambios de contrato, ejemplos de uso.
- **Entrega:** revisión aprobada y despliegue verificado.

**Consejo:** cada ítem debe poder marcarse con evidencia.

### Paso 3 — Definir la DoD común del equipo

La DoD debe ser corta, compartida y visible para todo el equipo.

Un ejemplo base:

1. Funcionalidad implementada según criterios de aceptación.
2. Tests automáticos relevantes en verde.
3. Code review aprobada.
4. Sin defectos críticos abiertos.
5. Documentación actualizada.
6. Lista de verificación completada.

### Paso 4 — Integrar checklist + DoD al flujo diario

No basta con tener un documento:

- Incluir checklist en la plantilla de PR o en el ticket.
- Revisar la DoD en refinamiento/planning.
- Bloquear avance cuando falten ítems críticos.
- Usar la misma estructura en todo el equipo.

### Paso 5 — Medir cumplimiento y calidad

Para saber si el estándar funciona, medir:

- % de tareas que cumplen DoD al primer intento.
- Defectos encontrados tras “cerrar” tareas.
- Tiempo medio de revisión.
- Ítems de checklist más incumplidos.

### Paso 6 — Mejorar continuamente (retrospectiva)

Cada ciclo:

1. Identificar fallos repetitivos.
2. Ajustar checklist (añadir/quitar simplificando).
3. Revisar DoD si está desactualizada.
4. Comunicar cambios y entrenar al equipo.

---

## 4) Ejemplo práctico completo

### Contexto

Historia: “Como usuario, quiero poder marcar una subtarea como completada para seguir el progreso de una tarea principal”.

### Checklist aplicada

- [ ] Endpoint `PATCH /api/subtasks/{id}` implementado.
- [ ] Validación de estado permitida.
- [ ] Respuesta de error clara para IDs inexistentes.
- [ ] Tests unitarios de servicio añadidos/actualizados.
- [ ] Prueba manual de flujo feliz y caso inválido.
- [ ] Contrato/documentación API actualizado.

### DoD de la historia

La historia está **Done** cuando:

- Cumple criterios funcionales.
- Todas las pruebas relevantes pasan.
- PR aprobada sin observaciones bloqueantes.
- No hay regresiones en endpoints relacionados.
- Se deja evidencia (captura de tests o resultados de CI).

---

## 5) Anti-patrones frecuentes (y cómo evitarlos)

1. **Checklist enorme y genérica** → simplificar a ítems críticos.
2. **DoD ambigua** (“bien probado”) → reemplazar por evidencia medible.
3. **Actualizar checklist sin comunicar** → siempre anunciar cambios al equipo.
4. **Marcar Done por presión de fechas** → mantener mínimos de calidad no negociables.

---

## 6) Plantilla reutilizable para el equipo

Puedes copiar esta plantilla en tickets/PR:

```md
## Checklist
- [ ] Criterios de aceptación cubiertos
- [ ] Validaciones y errores controlados
- [ ] Tests automáticos en verde
- [ ] Code review aprobada
- [ ] Documentación/contrato actualizado

## Definición de Done
- [ ] Funcionalidad validada por negocio/producto
- [ ] Sin incidencias críticas abiertas
- [ ] Evidencia de verificación adjunta
```

---

## 7) Relación con el documento base

Este tema 10 amplía y operativiza las recomendaciones de buenas prácticas del documento original: [`good-practices.md`](./good-practices.md).
