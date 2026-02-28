# Good Practices para desarrollo asistido por agentes

Este documento define una guía de referencia rápida con temas y pasos reutilizables para diseñar, implementar y validar cambios de software de forma consistente.

## Temas

1. Comprender el problema y el contexto.
2. Definir límites, supuestos y riesgos.
3. Diseñar cambios pequeños y reversibles.
4. Validar con pruebas automáticas y checks locales.
5. Documentar decisiones y trade-offs.
6. Revisar impacto operativo (seguridad, rendimiento, observabilidad).
7. **Especificación primero cuando importa (SDD).**

## Pasos base del método

1. **Contexto y objetivo:** identificar qué necesidad de negocio o técnica se quiere resolver.
2. **Criterios de aceptación:** concretar qué debe cumplirse para dar el trabajo por válido.
3. **Especificación:** redactar el comportamiento esperado antes de implementar (contratos, casos límite, reglas).
4. **Plan de implementación:** descomponer el trabajo en cambios pequeños, ordenados y comprobables.
5. **Verificación:** ejecutar pruebas/checks y revisar resultados frente a los criterios de aceptación.
6. **Evidencia y documentación:** dejar trazabilidad de lo construido, decisiones y ejemplos.

## Desarrollo ampliado del tema 7

- 📄 Ver detalle completo en [`tema7-especificacion-primero-sdd.md`](./tema7-especificacion-primero-sdd.md).
