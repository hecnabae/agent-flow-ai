# Tema 4 — Bucle obligatorio: plan → cambio mínimo → test → fix

> Documento relacionado: [Good Practices de desarrollo](./good-practices.md)

El objetivo de este bucle es **reducir riesgo**, **acelerar el feedback** y **evitar cambios grandes difíciles de depurar**.

## 1) Qué significa cada fase

## Plan

Antes de tocar código, define:

- Qué problema exacto vas a resolver.
- Qué parte del sistema afecta.
- Qué validación va a demostrar que el cambio funciona.

Resultado esperado: una lista breve de pasos pequeños y verificables.

## Cambio mínimo

Implementa el ajuste más pequeño posible para mover el estado actual hacia el objetivo.

Principios:

- Una intención por cambio.
- Evitar refactors grandes mezclados con cambios funcionales.
- Mantener el diff pequeño para revisar y revertir fácilmente.

## Test

Ejecuta pruebas directamente relacionadas con el cambio.

Puede incluir:

- Unit tests.
- Tests de integración.
- Checks de compilación/lint.

La regla clave: **no asumir, verificar**.

## Fix

Si algo falla:

- Corregir la causa raíz.
- Re-ejecutar test.
- Repetir el ciclo hasta verde.

Si todo pasa:

- Revisar si hay simplificaciones seguras.
- Preparar commit atómico.

---

## 2) Relación con todos los pasos de `good-practices.md`

El fichero principal define 4 pasos:

1. Entender objetivo y contexto.
2. Planificar en pasos pequeños.
3. Aplicar cambio mínimo viable.
4. Ejecutar el bucle obligatorio.

Este tema 4 conecta y operacionaliza los cuatro:

- El **Plan** del bucle aterriza los pasos 1 y 2.
- El **Cambio mínimo** corresponde al paso 3.
- **Test** y **Fix** cierran el paso 4 con aprendizaje iterativo.

Así, no es una fase aislada: es una forma de ejecutar todo el flujo con disciplina.

---

## 3) Procedimiento recomendado (checklist)

1. Escribe una mini hipótesis de cambio (1–3 líneas).
2. Define la prueba/check que debe pasar.
3. Aplica el cambio mínimo.
4. Ejecuta pruebas relevantes.
5. Si falla, arregla causa raíz (no parche superficial).
6. Repite desde el paso 4.
7. Cuando pase, revisa claridad del código y cierra con commit pequeño.

---

## 4) Ejemplos prácticos

## Ejemplo A: Validación en API

**Situación:** un endpoint acepta payload inválido sin error.

- **Plan:** añadir validación de campo obligatorio `title`.
- **Cambio mínimo:** incorporar validación en DTO/controlador sin refactor adicional.
- **Test:** test unitario para payload vacío + test de caso válido.
- **Fix:** ajustar mensaje de error y status HTTP si el test no coincide.

Resultado: mejora puntual, validada y con bajo riesgo.

## Ejemplo B: Bug de estado

**Situación:** una subtarea pasa de `DONE` a `TODO` cuando no debería.

- **Plan:** restringir transición inválida.
- **Cambio mínimo:** añadir guard clause en servicio de dominio.
- **Test:** test de transición inválida esperando excepción.
- **Fix:** corregir condición lógica y volver a ejecutar test suite relacionada.

Resultado: corrección focalizada y comportamiento protegido por prueba.

## Ejemplo C: Ajuste de rendimiento local

**Situación:** una consulta se ejecuta dos veces por error.

- **Plan:** identificar duplicidad y medir efecto.
- **Cambio mínimo:** reutilizar resultado ya obtenido en el mismo flujo.
- **Test:** test de integración + comprobación funcional de respuesta.
- **Fix:** si cambia salida, corregir mapping y repetir validación.

Resultado: optimización sin alterar contrato funcional.

---

## 5) Anti-patrones a evitar

- Cambiar demasiadas cosas en un solo commit.
- Arreglar tests “para que pasen” sin resolver causa raíz.
- Ejecutar tests solo al final de una cadena larga de cambios.
- Mezclar refactor estructural y bugfix sin separación.

---

## 6) Señales de que el bucle está funcionando

- Commits pequeños, legibles y revertibles.
- Menos regresiones inesperadas.
- Tiempo menor entre “cambio” y “feedback”.
- Revisiones de código más rápidas y con menos dudas.

---

## 7) Plantilla rápida para uso diario

- **Plan:** “Voy a cambiar ___ para conseguir ___; validaré con ___.”
- **Cambio mínimo:** “Solo modifico ___.”
- **Test:** “Ejecuto ___.”
- **Fix:** “Si falla ___, corrijo ___ y repito.”

Esta plantilla ayuda a mantener foco y consistencia en equipos.
