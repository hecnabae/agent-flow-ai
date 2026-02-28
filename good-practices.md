# Good Practices de colaboración

Esta guía reúne prácticas de trabajo para mantener la calidad, acelerar revisiones y reducir riesgos al integrar cambios.

## Temas

1. Definir alcance y criterio de terminado.
2. Diseñar cambios reversibles.
3. Escribir pruebas enfocadas al riesgo.
4. Comunicar decisiones técnicas en PR.
5. **PRs pequeños: reduce el radio de destrucción.**

---

## Tema 5 — PRs pequeños: reduce el radio de destrucción

### Objetivo
Dividir cambios grandes en unidades revisables y desplegables para minimizar regresiones, facilitar rollback y mantener la velocidad del equipo.

### Pasos recomendados

1. **Define una sola intención por PR**  
   El PR debe responder a una pregunta concreta: “¿qué problema único resuelve?”.
2. **Separa refactor de funcionalidad**  
   Si necesitas limpiar código, hazlo en un PR previo sin cambios de comportamiento.
3. **Trocea por capas o verticales**  
   Divide por migración de datos, dominio, API y UI (o por historias verticales pequeñas).
4. **Usa feature flags cuando convenga**  
   Integra código inactivo de forma segura antes de activar comportamiento nuevo.
5. **Valida cada corte**  
   Asegura compilación, pruebas y compatibilidad en cada PR intermedio.
6. **Describe impacto y plan de rollback**  
   Incluye riesgos, métricas de verificación y cómo revertir en minutos.
7. **Solicita revisión temprana**  
   PRs de 100–300 líneas útiles suelen recibir feedback más rápido y con mayor calidad.

### Desarrollo ampliado
Consulta la guía extendida del tema en:

➡️ **[docs/tema5-prs-pequenos-radio-destruccion.md](docs/tema5-prs-pequenos-radio-destruccion.md)**
