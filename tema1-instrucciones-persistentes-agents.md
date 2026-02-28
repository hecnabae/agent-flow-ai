# Tema 1: Instrucciones persistentes con `AGENTS.md` (para no repetirlo todo)

> Basado en el documento original de buenas prácticas: [good-practices.md](./good-practices.md)

## ¿Qué es `AGENTS.md` y por qué usarlo?

`AGENTS.md` es un archivo de instrucciones persistentes para asistentes de desarrollo. Su propósito es capturar el contexto operativo del proyecto (stack, normas, flujos y criterios de calidad) para que no tengas que repetir lo mismo en cada sesión.

Cuando está bien definido, mejora tres cosas clave:

- **Consistencia**: el asistente sigue los mismos estándares en cada cambio.
- **Velocidad**: reduce aclaraciones manuales y retrabajo.
- **Escalabilidad**: distintos equipos o carpetas pueden tener reglas específicas.

---

## Desarrollo paso a paso (según `good-practices.md`)

## 1) Define el alcance

Antes de escribir reglas, decide el alcance del archivo:

- **Raíz del repositorio**: políticas globales.
- **Subdirectorios**: reglas específicas por módulo (`apps/api`, `apps/web`, etc.).

### Ejemplo

```text
/AGENTS.md                  -> normas globales
/apps/api/AGENTS.md         -> normas de backend Java
/apps/web/AGENTS.md         -> normas de frontend
```

**Recomendación**: comienza con una versión corta global y después especializa por carpetas si aparecen necesidades concretas.

## 2) Describe el stack y comandos base

Incluye la información mínima para que cualquier agente pueda ejecutar el proyecto sin adivinar:

- versión de runtime/lenguaje
- gestor de dependencias
- comando para tests
- comando para levantar el servicio

### Ejemplo de sección

```md
## Entorno
- Java 21
- Maven 3.9+

## Comandos
- Tests: `mvn test`
- Run API: `mvn -pl apps/api spring-boot:run`
```

Con esto evitas errores de contexto y respuestas genéricas.

## 3) Fija convenciones de código

Define reglas de implementación para reducir discrepancias en PRs:

- estilo y naming
- estructura por capas
- límites de responsabilidad
- patrones permitidos y antipatrones

### Ejemplo

```md
## Convenciones backend
- Controladores: solo orquestan request/response.
- Lógica de negocio: en servicios.
- Acceso a datos: en repositorios.
- Nombres de DTO: `*Request` y `*Response`.
```

Cuanto más concretas sean las reglas, más reutilizable será la salida del asistente.

## 4) Documenta criterios de calidad

No basta con "que compile". Define claramente la barra mínima:

- tests obligatorios
- checks estáticos
- cobertura mínima (si aplica)
- comportamiento esperado para casos borde

### Ejemplo

```md
## Calidad mínima
- Ejecutar `mvn test` antes de cerrar cambios.
- Si se modifica una validación, añadir test de error y de caso feliz.
- No aceptar warnings críticos de compilación.
```

Esto evita PRs incompletas y discusiones tardías.

## 5) Incluye reglas de entregables

Especifica cómo quieres recibir resultados:

- formato del resumen final
- plantilla de PR
- checklist de verificación
- idioma y nivel de detalle

### Ejemplo

```md
## Formato de entrega
- Resumen en viñetas.
- Lista de archivos modificados.
- Comandos ejecutados con estado (ok/fail/warn).
- Riesgos o deuda técnica pendiente.
```

Cuando esta parte está clara, el output se vuelve más útil para revisión y auditoría.

## 6) Añade ejemplos concretos

Los ejemplos son multiplicadores de calidad porque convierten normas abstractas en patrones replicables.

Incluye:

- ejemplos de prompts buenos
- snippets de código recomendados
- casos "esto sí / esto no"

### Ejemplo (sí / no)

```md
✅ Sí: "Añade tests unitarios para TaskService cubriendo validación de estado inválido"
❌ No: "Hazlo mejor"
```

Esto disminuye ambigüedad y mejora la precisión del asistente.

## 7) Versiona y revisa el archivo

`AGENTS.md` debe tratarse como código:

- commit con cada cambio relevante
- revisión en PR
- historial de decisiones

### Recomendación práctica

- revisarlo en cada cambio de arquitectura
- revisarlo cuando se repita un fallo operativo
- revisarlo al incorporar nuevas herramientas

## 8) Usa herencia por niveles

Puedes tener un esquema jerárquico:

1. reglas globales en raíz
2. reglas específicas por producto/módulo
3. reglas muy locales en carpetas internas

La regla local más específica debe complementar o sobrescribir lo necesario para ese contexto.

### Ejemplo realista

```text
/AGENTS.md
/apps/AGENTS.md
/apps/api/AGENTS.md
```

Así evitas un único archivo gigante y mantienes instrucciones accionables por área.

---

## Plantilla sugerida para tu `AGENTS.md`

```md
# AGENTS.md

## Propósito
Breve descripción del objetivo del repositorio.

## Stack
- Lenguajes y versiones
- Frameworks clave

## Comandos
- Instalar
- Test
- Run

## Convenciones de código
- Estructura
- Naming
- Reglas de diseño

## Calidad
- Pruebas obligatorias
- Criterios de aceptación

## Entregables
- Formato de respuesta
- Requisitos de PR

## Ejemplos
- Prompt recomendado
- Snippet de referencia
```

---

## Checklist rápido de adopción

- [ ] Existe `AGENTS.md` en la raíz del repo.
- [ ] Define comandos de ejecución y pruebas.
- [ ] Incluye convenciones concretas (no genéricas).
- [ ] Tiene criterios de calidad verificables.
- [ ] Define formato esperado de entrega/PR.
- [ ] Se revisa en PR como cualquier otro archivo.
- [ ] Existen archivos locales en subcarpetas cuando aplica.

---

## Conclusión

`AGENTS.md` funciona como una "memoria operativa" del proyecto. Si capturas alcance, comandos, convenciones, calidad y ejemplos, reduces fricción, aceleras iteraciones y mejoras la calidad media de los cambios sin repetir instrucciones en cada conversación.
