# Tema 8: Seguridad y control de daños ("blast radius")

> Documento base relacionado: [good-practices.md](./good-practices.md)

## 1) ¿Qué es "blast radius" en software?

El **blast radius** (radio de explosión) es la magnitud del daño potencial cuando algo falla: una vulnerabilidad, una mala configuración, un bug en producción o una credencial comprometida.

En arquitectura moderna, no basta con "evitar fallos"; hay que **asumir que algunos fallos ocurrirán** y diseñar para que:

- afecten al menor número posible de usuarios,
- duren el menor tiempo posible,
- sean fáciles de detectar, contener y revertir.

---

## 2) Conceptos clave de seguridad y contención

- **Defensa en profundidad**: varias capas de control (no depender de un único mecanismo).
- **Mínimo privilegio**: cada servicio/usuario sólo accede a lo imprescindible.
- **Aislamiento**: separar componentes para que una caída o intrusión no se propague.
- **Fail-safe defaults**: ante duda o fallo, negar acceso o degradar de forma segura.
- **Observabilidad de seguridad**: registrar eventos útiles para detectar y responder.
- **Recuperabilidad**: capacidad real de volver a un estado sano rápidamente.

---

## 3) Desarrollo completo del flujo de `good-practices` aplicado al Tema 8

A continuación se desarrollan **todos los pasos** del documento `good-practices.md`, con foco en seguridad y control de daños.

### Paso 1. Identificar activos críticos y amenazas

Primero, enumera qué debes proteger:

- datos personales,
- credenciales y tokens,
- integridad de transacciones,
- disponibilidad del servicio.

Después modela amenazas (ej. STRIDE) y prioriza por impacto/probabilidad.

**Ejemplo**: si una API de tareas guarda notas privadas, una amenaza clave es acceso indebido por un token robado. El activo crítico es la confidencialidad de las notas.

### Paso 2. Diseñar controles preventivos

Controles para evitar que el incidente ocurra:

- autenticación robusta (OIDC/JWT con expiración corta),
- autorización por recurso (no sólo por rol global),
- validación estricta de entrada,
- políticas CORS y cabeceras de seguridad,
- segmentación de red y WAF cuando aplique.

**Ejemplo**: en `PATCH /tasks/{id}` validar que el `taskId` pertenece al usuario autenticado antes de permitir cambios.

### Paso 3. Aplicar controles detectivos

No todo se puede prevenir; debes detectar rápido:

- logs de auditoría (quién, qué, cuándo, desde dónde),
- métricas (errores 401/403, tasa de requests, latencia),
- alertas (picos de intentos fallidos, escalada de privilegios),
- trazas distribuidas para seguir un incidente de extremo a extremo.

**Ejemplo**: alerta si una misma IP intenta acceder a >200 recursos de distintos usuarios en 5 minutos.

### Paso 4. Reducir superficie de exposición

Menos superficie = menos riesgo:

- eliminar endpoints no usados,
- desactivar puertos/servicios innecesarios,
- separar secretos por entorno,
- rotar claves y usar secret manager,
- actualizar dependencias con CVEs conocidos.

**Ejemplo**: si un microservicio no necesita acceso de escritura a MongoDB, su usuario sólo debe tener permisos de lectura.

### Paso 5. Aislar fallos por dominio

Este paso es central para el **blast radius**:

- separación por entornos (dev/stage/prod),
- cuentas/proyectos cloud separados por producto o criticidad,
- colas y consumidores por dominio,
- límites de recursos por servicio,
- feature flags para activar cambios por segmentos.

**Ejemplo**: desplegar una nueva lógica sólo para 5% de usuarios internos antes de abrir al 100%.

### Paso 6. Preparar mecanismos de contención

Cuando detectas daño, necesitas frenar propagación:

- **kill switch** para desactivar funcionalidades críticas,
- rate limiting y circuit breakers,
- revocación masiva de tokens,
- aislamiento temporal de tenants afectados,
- modo degradado (read-only, cola diferida, fallback).

**Ejemplo**: si una integración externa empieza a devolver datos corruptos, activar kill switch y usar caché validada hasta resolver.

### Paso 7. Planificar respuesta y recuperación

La seguridad no termina en detectar; hay que restaurar:

- runbook de incidente (pasos claros, responsables, tiempos objetivo),
- backups probados (no sólo configurados),
- rollback de despliegues automatizado,
- ejercicios de simulación (game days, tabletop).

**Ejemplo**: runbook para fuga de credenciales: rotación de secretos, invalidación de sesiones, análisis forense y comunicación.

### Paso 8. Revisar incidentes y mejorar

Sin aprendizaje, el incidente se repite:

- postmortem sin culpables,
- acciones correctivas con fecha y dueño,
- nuevos tests de seguridad y resiliencia,
- hardening de arquitectura según hallazgos.

**Ejemplo**: tras un incidente de sobrecarga, añadir test de carga con umbrales y alerta proactiva en CPU/memoria.

---

## 4) Ejemplos prácticos de reducción de blast radius

### Ejemplo A: Credencial de servicio comprometida

**Sin controles**: acceso amplio a toda la base de datos y todos los entornos.

**Con controles**:

1. credencial sólo de lectura,
2. alcance exclusivo a `prod-read-replica`,
3. rotación automática cada 24h,
4. detección de uso anómalo,
5. revocación inmediata.

Resultado: el atacante obtiene datos limitados y por poco tiempo.

### Ejemplo B: Bug en una nueva release

**Sin despliegue gradual**: caída total para todos los clientes.

**Con canary + feature flag**:

1. despliegue al 2%,
2. alerta por aumento de errores 5xx,
3. rollback automático,
4. desactivación de feature.

Resultado: impacto acotado a una pequeña fracción de tráfico.

### Ejemplo C: Dependencia externa inestable

**Sin contención**: timeouts encadenados y degradación global.

**Con patrones de resiliencia**:

1. circuit breaker,
2. timeout corto,
3. fallback local,
4. cola de reintentos.

Resultado: el sistema principal sigue operativo con degradación controlada.

---

## 5) Checklist operativo (rápido)

- [ ] ¿Cada servicio opera con mínimo privilegio?
- [ ] ¿Hay segmentación por entorno/cuenta/red?
- [ ] ¿Existen límites de tasa, cuota y recursos?
- [ ] ¿Tenemos logs de auditoría accionables?
- [ ] ¿El rollback está probado y automatizado?
- [ ] ¿Hay kill switch para funciones críticas?
- [ ] ¿Los secretos rotan y se monitoriza su uso?
- [ ] ¿Se realizan postmortems con acciones verificables?

---

## 6) Cierre

El objetivo del Tema 8 no es prometer "riesgo cero", sino diseñar sistemas donde los errores y ataques sean **locales, visibles y recuperables**. Ese enfoque convierte la seguridad en una capacidad continua de ingeniería, no en una revisión puntual.
