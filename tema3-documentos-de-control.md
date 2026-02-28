# Tema 3: Evita el “prompt spaghetti” con documentos de control

> Documento base relacionado: [good-practices.md](./good-practices.md)

El “prompt spaghetti” aparece cuando las instrucciones de trabajo se dispersan en múltiples mensajes, notas y correcciones parciales. El resultado suele ser:

- respuestas inconsistentes,
- retrabajo por ambigüedad,
- pérdida de contexto entre iteraciones,
- decisiones no trazables.

La forma práctica de evitarlo es trabajar con **documentos de control**, que funcionan como fuente de verdad compartida para personas y agentes.

---

## Qué es un documento de control

Es un documento operativo que concentra las reglas y el marco de ejecución del trabajo. No reemplaza la conversación, pero sí evita que la conversación sea el único lugar donde viven los requisitos.

Un buen documento de control debe responder, como mínimo:

- **Qué se quiere lograr** (objetivo).
- **Qué entra y qué no entra** (alcance).
- **Cómo se debe trabajar** (proceso y convenciones).
- **Cómo se valida el resultado** (criterios de calidad).
- **Dónde está la evidencia** (enlaces a artefactos).

---

## Desarrollo de todos los pasos del `good-practices.md`

## 1) Centraliza el contexto base en un documento único

### Objetivo
Evitar contexto fragmentado entre chat, tickets y notas personales.

### Qué incluir
- Objetivo del entregable.
- Restricciones (tiempo, stack, formato, tono, cumplimiento).
- Definiciones clave del dominio.
- Criterios de éxito.

### Ejemplo
En lugar de repetir en cada prompt “usa Java 21, no cambies endpoints existentes y documenta pruebas”, lo declaras una sola vez en el documento de control y lo referencias en cada tarea.

---

## 2) Separa reglas estables de decisiones temporales

### Objetivo
Distinguir lo permanente de lo circunstancial para reducir contradicciones.

### Reglas estables (ejemplos)
- Estilo de código.
- Convenciones de naming.
- Política de testing.
- Formato de PR.

### Decisiones temporales (ejemplos)
- “Esta semana priorizar performance sobre refactor”.
- “No tocar módulo X por freeze”.

### Ejemplo práctico
Si una instrucción temporal se mezcla con reglas estables, el agente puede aplicarla fuera de contexto en futuras tareas. Separarlas reduce ese riesgo.

---

## 3) Versiona cambios relevantes

### Objetivo
Tener trazabilidad de decisiones: **qué cambió, por qué y cuándo**.

### Mínimo recomendado
- Fecha.
- Autor.
- Cambio realizado.
- Motivo.
- Impacto esperado.

### Ejemplo de registro
- `2026-02-28`: se exige incluir checklist de seguridad en todas las entregas backend.
- Motivo: incidentes por validación insuficiente.
- Impacto: +5 minutos por entrega, menos defectos críticos.

---

## 4) Define plantilla de trabajo por tarea (entrada, proceso, validación, salida)

### Objetivo
Estandarizar cómo se ejecutan tareas para mejorar consistencia y velocidad.

### Plantilla sugerida
1. **Entrada**: requerimiento, contexto técnico, dependencias.
2. **Proceso**: pasos de implementación.
3. **Validación**: pruebas, lint, criterios de aceptación.
4. **Salida**: resumen, evidencias, próximos pasos.

### Ejemplo
Para una tarea de API:
- Entrada: endpoint + contrato DTO.
- Proceso: implementar servicio + controlador + mapper.
- Validación: pruebas unitarias y de integración.
- Salida: commit, changelog y comando de prueba ejecutado.

---

## 5) Establece checklist de calidad antes de cerrar

### Objetivo
Evitar “entregas aparentemente completas” con huecos críticos.

### Checklist tipo
- [ ] Cumple alcance pactado.
- [ ] No rompe compatibilidad existente.
- [ ] Pruebas pasan en local/CI.
- [ ] Documentación actualizada.
- [ ] Riesgos y limitaciones declarados.

### Ejemplo
Si una tarea funcional está “hecha” pero no actualiza documentación operativa, no se marca como cerrada hasta cubrir ese punto.

---

## 6) Conecta artefactos con enlaces explícitos

### Objetivo
Evitar que cada documento viva aislado.

### Qué enlazar
- Brief → plan de trabajo.
- Plan → implementación.
- Implementación → pruebas.
- Pruebas → decisión de cierre.

### Ejemplo
En `good-practices.md` se enlaza este documento de tema 3 para que el lector pase de resumen a guía detallada sin perder contexto.

---

## 7) Revisión de coherencia periódica

### Objetivo
Mantener el sistema de instrucciones limpio y actualizado.

### Cadencia sugerida
- Semanal en equipos con alta rotación de tareas.
- Quincenal o mensual en contextos más estables.

### Qué revisar
- Reglas duplicadas.
- Instrucciones obsoletas.
- Contradicciones entre documentos.
- Mejores prácticas emergentes para consolidar.

### Ejemplo
Si aparece la misma norma en tres documentos con redacciones distintas, se consolida en uno y los otros referencian esa fuente.

---

## Ejemplo completo (antes y después)

## Antes (prompt spaghetti)
- Prompt 1: “Haz endpoint de tareas”.
- Prompt 2: “Añade validaciones”.
- Prompt 3: “No cambies naming anterior”.
- Prompt 4: “Ahora sí, renombra campos”.

Resultado: conflicto de instrucciones, múltiples idas y vueltas, decisiones no trazables.

## Después (con documento de control)
- Documento base define naming, validaciones, compatibilidad y formato de entrega.
- Cada prompt referencia la misma fuente.
- Cambios nuevos se registran en historial de decisiones.

Resultado: menos ambigüedad, ejecución más predecible y revisiones más rápidas.

---

## Conclusión

El valor principal de un documento de control no es “documentar por documentar”, sino **reducir fricción operativa**. Centralizar contexto, versionar decisiones y usar plantillas/checklists convierte prompts sueltos en un sistema de trabajo robusto y mantenible.
