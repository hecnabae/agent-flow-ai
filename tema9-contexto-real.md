# Tema 9 — Contexto real: referencia archivos, no lo expliques todo con texto

> Este documento amplía el tema 9 y está **vinculado al documento original de prácticas**: [`good-practices.md`](./good-practices.md).

## Objetivo del tema

Cuando trabajas con IA en un repositorio, explicar ideas solo con texto suele producir respuestas ambiguas. La buena práctica es aportar **contexto real y verificable**: archivos concretos, rutas, fragmentos y cambios exactos.

En otras palabras:

- ❌ “Hay que mejorar la API.”
- ✅ “En `apps/api/src/main/resources/application.yml` falta documentar la variable `MONGODB_URI` y su valor por defecto.”

---

## Conceptos clave

### 1. Contexto real > contexto narrado

El modelo rinde mejor cuando recibe evidencia concreta:

- rutas de archivos,
- funciones o clases específicas,
- comandos ejecutados,
- resultados observables.

Esto reduce suposiciones y evita soluciones “genéricas”.

### 2. Trazabilidad

Cada recomendación debería poder responder: **“¿de dónde sale?”**.

Para eso, referencia:

- archivo,
- sección o bloque,
- impacto esperado.

### 3. Acción mínima verificable

No pidas “arreglar todo”. Divide en pasos pequeños que puedan validarse (por ejemplo, una mejora por commit o por PR).

### 4. Evidencia antes que opinión

Sustituye frases abstractas por hechos reproducibles:

- “el test X falla con este error”,
- “esta ruta devuelve 404”,
- “esta clase no se usa”.

---

## Pasos (aplicación práctica del tema)

> Esta secuencia te permite aplicar la práctica de “contexto real” de forma consistente.

### Paso 1 — Delimita el objetivo

Define en una frase qué quieres conseguir.

**Ejemplo**: “Documentar correctamente la configuración de base de datos para desarrollo local”.

### Paso 2 — Localiza las fuentes reales

Identifica los archivos que contienen la verdad del problema.

**Ejemplo de fuentes**:

- `README.md`
- `apps/api/src/main/resources/application.yml`

### Paso 3 — Extrae evidencia concreta

Recoge fragmentos, claves o rutas (sin copiar de más).

**Ejemplo**:

- En `README.md` se menciona `MONGODB_URI` como variable configurable.
- En `application.yml` está el valor de conexión efectivo.

### Paso 4 — Formula la petición con referencias

Pide cambios nombrando archivos concretos y resultado esperado.

**Mal**: “Mejora la documentación”.

**Bien**: “Actualiza `README.md` para explicar cómo se usa `MONGODB_URI` y añade ejemplo de `.env` para local”.

### Paso 5 — Propón cambios atómicos

Divide el trabajo en unidades pequeñas.

**Ejemplo**:

1. Añadir sección de variables de entorno.
2. Añadir ejemplo de ejecución local.
3. Validar que los comandos funcionan.

### Paso 6 — Verifica con comandos

Valida el cambio con comandos reproducibles.

**Ejemplo**:

```bash
mvn test
mvn -pl apps/api spring-boot:run
```

### Paso 7 — Documenta resultados

Registra qué cambió y dónde.

Formato recomendado:

- Qué archivo se modificó.
- Qué problema resolvió.
- Cómo comprobarlo.

### Paso 8 — Deja enlaces cruzados

Relaciona el documento nuevo con el original para mantener contexto navegable.

**Ejemplo**:

- En este archivo: enlace a `good-practices.md`.
- En `good-practices.md`: enlace de vuelta a este tema.

### Paso 9 — Evita sobreexplicar, muestra referencias

Si un lector puede abrir el archivo y verificarlo, la explicación ya es suficiente.

Regla práctica:

- 30% explicación,
- 70% referencias, ejemplos y pasos ejecutables.

---

## Ejemplos comparativos

### Ejemplo A — Solicitud débil (sin contexto real)

“Haz una guía de buenas prácticas para backend.”

**Problema**: demasiado abierta, no se puede verificar fácilmente.

### Ejemplo B — Solicitud fuerte (con contexto real)

“Crea una guía para `apps/api` usando como base `good-practices.md`, incluyendo ejemplos sobre `application.yml`, comandos `mvn test` y arranque local con Maven.”

**Ventaja**: concreta, verificable y accionable.

---

## Checklist rápido para usar IA con contexto real

- [ ] ¿Incluí rutas de archivo exactas?
- [ ] ¿Definí objetivo y resultado esperado?
- [ ] ¿Pedí pasos pequeños y verificables?
- [ ] ¿Añadí comandos de validación?
- [ ] ¿Dejé enlaces entre documento base y ampliación?

---

## Referencia

Documento original: [`good-practices.md`](./good-practices.md)
