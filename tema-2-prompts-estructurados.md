# Tema 2: Prompts con estructura (Contexto + Objetivo + Verificación)

Una de las formas más efectivas de mejorar la calidad de salida de un modelo es estructurar el prompt en tres bloques explícitos:

1. **Contexto**: qué está pasando, qué información existe y qué restricciones aplican.
2. **Objetivo**: qué resultado quieres obtener exactamente.
3. **Verificación**: cómo comprobar que la respuesta cumple lo que pediste.

Esta técnica reduce ambigüedades, evita respuestas genéricas y hace más fácil iterar cuando algo no sale bien.

---

## 1) Contexto

El contexto define el marco de trabajo. Sin contexto, el modelo rellena vacíos con suposiciones.

### Qué incluir

- **Situación**: dominio o problema (soporte, marketing, código, educación, etc.).
- **Datos disponibles**: texto fuente, métricas, requisitos, audiencias.
- **Restricciones**: idioma, tono, extensión, formato, herramientas permitidas.
- **Rol esperado** (opcional): “actúa como analista”, “como revisor técnico”, etc.

### Ejemplo (débil vs fuerte)

**Débil**

> Haz un resumen de esto.

**Fuerte (con contexto)**

> Contexto: este es un informe trimestral para dirección, orientado a decisiones de presupuesto. El público no técnico tiene 5 minutos de lectura. Se priorizan riesgos, impacto financiero y próximos pasos.

---

## 2) Objetivo

El objetivo debe ser accionable y medible. Evita pedir cosas amplias como “explícalo bien”.

### Qué incluir

- **Entregable concreto**: resumen, tabla, plan, checklist, código, email, etc.
- **Criterios de calidad**: profundidad, nivel técnico, claridad, neutralidad, etc.
- **Formato de salida**: bullets, JSON, markdown, secciones fijas, número de puntos.

### Ejemplo

> Objetivo: genera un resumen ejecutivo en español de máximo 120 palabras, con 3 bullets finales de acciones recomendadas y un nivel de lenguaje no técnico.

---

## 3) Verificación

Este bloque obliga a validar la respuesta antes de entregarla y es clave para consistencia.

### Qué incluir

- **Checklist de cumplimiento**: longitud, formato, cobertura de puntos clave.
- **Condiciones de rechazo**: qué se considera incorrecto o incompleto.
- **Autoevaluación breve** (opcional): una línea final confirmando cumplimiento.

### Ejemplo

> Verificación:
> - Debe incluir al menos 1 riesgo y 1 oportunidad.
> - No superar 120 palabras en el resumen principal.
> - Incluir exactamente 3 acciones en bullets.
> - Si falta información para afirmar algo, indicarlo explícitamente.

---

## Plantilla reutilizable

Puedes copiar esta estructura como base:

```markdown
Contexto:
- [Situación]
- [Datos disponibles]
- [Restricciones]

Objetivo:
- [Entregable]
- [Formato]
- [Nivel de profundidad]

Verificación:
- [Regla 1]
- [Regla 2]
- [Regla 3]
```

---

## Ejemplo completo 1 (documentación técnica)

```markdown
Contexto:
- Tengo una API REST en Spring Boot con endpoints de tareas y subtareas.
- El equipo nuevo necesita onboarding rápido.
- El documento se publicará en README interno.

Objetivo:
- Redacta una guía de inicio rápido en español (200-300 palabras).
- Incluye: requisitos, cómo arrancar, cómo ejecutar tests y 3 endpoints clave.
- Formato en Markdown con títulos H2 y lista de comandos.

Verificación:
- Debe contener al menos 3 comandos de terminal.
- Debe mencionar `/api/tasks` y `/api/subtasks/{id}`.
- No inventar endpoints que no aparezcan en el contexto.
```

**Por qué funciona**: limita el alcance, define salida concreta y establece criterios verificables.

---

## Ejemplo completo 2 (análisis de feedback de usuarios)

```markdown
Contexto:
- Tengo 25 comentarios de usuarios de una app móvil.
- El objetivo del análisis es priorizar mejoras del próximo sprint.
- Audiencia: product manager y equipo de diseño.

Objetivo:
- Clasifica los comentarios por tema en una tabla.
- Devuelve: Tema | Frecuencia | Ejemplo textual | Prioridad (Alta/Media/Baja).
- Añade 5 recomendaciones accionables al final.

Verificación:
- Deben existir al menos 4 temas distintos.
- Cada tema debe incluir frecuencia numérica.
- Las recomendaciones deben empezar por un verbo de acción.
```

**Por qué funciona**: facilita la trazabilidad entre evidencia (comentarios) y decisiones (priorización).

---

## Errores comunes que evita esta estructura

- Pedir “algo bueno” sin definir métricas de calidad.
- Mezclar contexto y objetivo en un bloque confuso.
- No especificar formato de salida y luego perder tiempo reformateando.
- No incluir validación y detectar problemas demasiado tarde.

---

## Recomendaciones de uso en equipos

- Estandariza una plantilla C+O+V en el repositorio.
- Guarda ejemplos buenos por caso de uso (soporte, ingeniería, contenido, etc.).
- Versiona prompts críticos igual que el código.
- Cuando falle una respuesta, ajusta primero **Verificación** antes de rehacer todo.

Con esta estructura, los prompts son más consistentes, auditables y fáciles de mejorar en ciclos cortos.
