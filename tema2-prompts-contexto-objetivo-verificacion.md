# Tema 2: Prompts con estructura (Contexto + Objetivo + Verificación)

> Documento vinculado al original: [good-practices.md](./good-practices.md)

## ¿Por qué esta estructura funciona?

Un prompt estructurado reduce ambigüedad y mejora la calidad de salida. El modelo responde mejor cuando sabe:

- **de dónde parte** (Contexto),
- **qué tiene que conseguir** (Objetivo),
- **cómo comprobar que lo hizo bien** (Verificación).

Esta combinación convierte peticiones genéricas en instrucciones accionables y evaluables.

---

## 1) Contexto

El contexto define el marco de trabajo. Sin contexto, el modelo rellena huecos con supuestos que pueden no coincidir con tu necesidad.

### Qué incluir

- Dominio o problema (ej.: marketing B2B, backend Java, formación interna).
- Audiencia (ej.: equipo técnico, clientes finales, dirección).
- Restricciones (tiempo, formato, normativa, estilo).
- Información disponible y no disponible.

### Buenas prácticas

- Incluye solo lo relevante para la tarea.
- Evita contradicciones entre requisitos.
- Señala límites explícitos ("no inventes datos", "si falta información, indícalo").

### Ejemplo (débil vs fuerte)

**Débil:**

```text
Explícame este tema.
```

**Fuerte:**

```text
Contexto: estoy preparando una sesión para perfiles junior de producto.
Necesito explicar en español qué es una API REST con ejemplos sencillos.
No uses jerga excesiva y limita la respuesta a 400 palabras.
```

---

## 2) Objetivo

El objetivo expresa la meta exacta de la respuesta. Debe ser observable y verificable.

### Qué incluir

- Entregable esperado (resumen, tabla, plan, código, checklist).
- Nivel de profundidad (introductorio, intermedio, experto).
- Criterios de forma (longitud, idioma, estructura).

### Buenas prácticas

- Usa verbos concretos: "redacta", "compara", "propón", "prioriza".
- Define formato de salida (Markdown, JSON, bullets, tabla).
- Evita objetivos múltiples sin prioridad.

### Ejemplo

```text
Objetivo: redacta una guía de onboarding en formato Markdown con:
1) resumen ejecutivo,
2) 5 pasos accionables,
3) errores comunes,
4) checklist final.
```

---

## 3) Verificación

La verificación añade un mecanismo de control de calidad en la propia respuesta.

### Qué incluir

- Checklist de validación.
- Criterios de aceptación (completitud, claridad, exactitud, formato).
- Manejo de incertidumbre (marcar supuestos y vacíos de información).

### Buenas prácticas

- Pide auto-chequeo breve al final.
- Añade criterios cuantificables cuando sea posible.
- Solicita que se indiquen riesgos o dudas explícitamente.

### Ejemplo

```text
Verificación:
- Comprueba que cada recomendación tenga justificación.
- Incluye una sección "Supuestos".
- Si falta información crítica, listarla antes de concluir.
- Cierra con un checklist de cumplimiento (Sí/No).
```

---

## Aplicación del flujo completo de `good-practices.md`

Este tema aterriza los **5 pasos del documento original** en una plantilla reutilizable:

1. **Entender el problema real**
   - Define decisión y resultado esperado antes de escribir el prompt.
2. **Aportar contexto relevante**
   - Introduce dominio, audiencia y restricciones útiles.
3. **Definir un objetivo claro y medible**
   - Especifica entregable + formato + nivel de detalle.
4. **Solicitar verificación explícita**
   - Añade checklist y condiciones de aceptación.
5. **Iterar por versiones**
   - Ajusta contexto, objetivo o verificación según los fallos detectados.

---

## Plantilla lista para usar

```text
Contexto:
[Describe el escenario, audiencia y límites]

Objetivo:
[Indica exactamente qué salida necesitas y en qué formato]

Verificación:
[Define checklist de calidad, cobertura y manejo de incertidumbre]
```

---

## Ejemplo completo

```text
Contexto:
Somos un equipo de operaciones en una startup SaaS.
Tenemos incidencias repetidas en soporte por falta de documentación interna.
La audiencia es el equipo de soporte (nivel técnico medio).

Objetivo:
Crea una guía en Markdown para resolver incidencias de login.
Debe incluir: diagnóstico inicial, árbol de decisión, acciones por causa y escalado.
Extensión máxima: 700 palabras.

Verificación:
- Debe haber al menos 5 causas posibles con acción concreta.
- Incluir una sección de "Errores a evitar".
- Añadir un checklist final de resolución.
- Si falta información operativa, listarla en "Datos pendientes".
```

### Resultado esperado

Con esta estructura, la respuesta suele ser:

- más precisa,
- más reutilizable,
- más fácil de revisar,
- y más simple de iterar.
