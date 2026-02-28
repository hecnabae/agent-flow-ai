# Tema 6 — Usa “Skills” para tareas repetibles

> Documento original de referencia: [good-practices.md](./good-practices.md)

Las **Skills** son recetas operativas reutilizables para ejecutar tareas repetidas con calidad consistente. En vez de “reinventar” cada flujo, una Skill concentra instrucciones, orden de ejecución, validaciones y ejemplos.

---

## ¿Qué problema resuelven las Skills?

Sin Skills, los equipos suelen sufrir:

- Variabilidad en resultados (cada persona lo hace distinto).
- Omisiones de pasos críticos (checks, validación, documentación).
- Mayor tiempo de ejecución por falta de estandarización.
- Dificultad para transferir conocimiento entre integrantes.

Con Skills, el trabajo repetible se convierte en un proceso:

- **Predecible** (misma calidad de salida).
- **Trazable** (pasos y decisiones explícitas).
- **Escalable** (nuevos miembros se incorporan más rápido).
- **Mejorable** (versionado e iteración continua).

---

## Desarrollo completo de los pasos de `good-practices.md`

A continuación se desarrollan **todos los pasos** listados en el tema 6 del documento original.

### 1) Detecta la repetición

Identifica tareas candidatas observando:

- Frecuencia (¿ocurre semanal o diariamente?).
- Coste de error (¿si se omite algo, duele en producción?).
- Variabilidad actual (¿cada ejecución sale distinta?).

**Ejemplo:**
“Crear endpoints CRUD + DTO + tests” se repite en cada módulo nuevo.

---

### 2) Define alcance y resultado

Delimita qué hace y qué **no** hace la Skill:

- Entradas requeridas (nombre de módulo, puerto, base package).
- Resultado esperado (archivos generados, tests verdes, endpoints activos).
- Límites (no incluye despliegue, no incluye migración de datos, etc.).

**Ejemplo:**
Skill “Alta de recurso REST”:
- Entrada: `resourceName`, `basePath`, `fields`.
- Salida: controller + service + tests básicos.
- Fuera de alcance: dashboards, permisos avanzados.

---

### 3) Estandariza el procedimiento

Convierte conocimiento tácito en secuencia explícita:

1. Preparar estructura de carpetas.
2. Generar contratos (DTO/request/response).
3. Implementar servicio y capa web.
4. Añadir manejo de errores.
5. Ejecutar pruebas.
6. Verificar estilo/cobertura.

**Regla práctica:** cada paso debe poder ejecutarse sin ambigüedad.

---

### 4) Parametriza

Evita plantillas rígidas. Una Skill sólida admite variables:

- Nombre de dominio (`Task`, `Project`, `Reminder`).
- Prefijos/rutas (`/api/tasks`, `/api/projects`).
- Dependencias opcionales (cache, colas, auditoría).

**Ejemplo de parámetros mínimos:**

```text
resource_name: Task
endpoint_base: /api/tasks
persistence: mongodb
include_tests: true
```

Esto permite reutilizar el mismo flujo en múltiples contextos.

---

### 5) Añade validaciones

Toda Skill debe tener controles automáticos o checklist de salida:

- Compila sin errores.
- Pruebas relevantes pasan.
- API responde con códigos esperados.
- Documentación mínima actualizada.

**Definition of Done sugerida:**
- ✅ Build correcto
- ✅ Tests unitarios del módulo
- ✅ Endpoint de salud o smoke test
- ✅ Notas de cambio registradas

---

### 6) Incluye ejemplos reales

Incluye al menos:

- Caso estándar (happy path).
- Caso con datos mínimos.
- Caso borde (nombres largos, campos opcionales, errores esperables).

**Ejemplo práctico:**
- Crear recurso `Task` con campos obligatorios.
- Crear recurso `TaskLite` con set mínimo.
- Intentar crear `Task` sin campo obligatorio y validar error 400.

---

### 7) Versiona y mejora

Una Skill no es estática. Debe evolucionar con:

- Feedback del equipo.
- Postmortems de errores.
- Cambios de stack (frameworks, librerías, políticas).

**Cadencia recomendada:**
- Revisión ligera mensual.
- Revisión profunda por release relevante.

---

## Ejemplos de Skills que aportan valor

1. **Skill de scaffolding backend**
   - Crea estructura base de feature.
   - Añade pruebas mínimas.
   - Deja checklist de validación.

2. **Skill de preparación de PR**
   - Verifica formato de commit.
   - Ejecuta tests obligatorios.
   - Genera plantilla de descripción de cambios.

3. **Skill de incidente recurrente**
   - Recoge logs y métricas.
   - Ejecuta diagnóstico estándar.
   - Produce resumen de causa raíz preliminar.

---

## Señales de que una Skill está bien diseñada

- Un miembro nuevo puede ejecutarla sin ayuda extra.
- Dos personas distintas obtienen resultados equivalentes.
- Los errores recurrentes disminuyen con el tiempo.
- La documentación y los ejemplos siguen vigentes.

---

## Relación con el documento original

Este documento amplía el **Tema 6** del índice de buenas prácticas.

- Volver al índice general: [good-practices.md](./good-practices.md)
