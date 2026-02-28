# Good Practices para trabajar con agentes

Este documento resume prácticas recomendadas para mejorar la colaboración con asistentes de código y automatizaciones.

## Tema 1: Instrucciones persistentes con `AGENTS.md` (para no repetirlo todo)

### Objetivo
Evitar repetir contexto en cada sesión y mantener normas de trabajo consistentes por repositorio o por subcarpeta.

### Pasos recomendados
1. **Define el alcance**: decide si el `AGENTS.md` aplica a todo el repo o a un subdirectorio concreto.
2. **Describe el stack y comandos base**: añade cómo instalar, ejecutar y probar el proyecto.
3. **Fija convenciones de código**: naming, estructura, estilo y restricciones técnicas.
4. **Documenta criterios de calidad**: qué pruebas ejecutar y qué condiciones deben cumplirse antes de cerrar cambios.
5. **Incluye reglas de entregables**: formato de mensajes finales, PRs, checklist, etc.
6. **Añade ejemplos concretos**: prompts modelo, casos válidos/no válidos y snippets de referencia.
7. **Versiona y revisa el archivo**: mantenlo en Git y actualízalo con cambios de arquitectura o proceso.
8. **Usa herencia por niveles**: crea `AGENTS.md` más específicos en subcarpetas para sobrescribir reglas locales.

### Señales de que está funcionando
- Menos instrucciones repetidas en cada conversación.
- Respuestas más alineadas con el estilo del equipo.
- Menos revisiones por reglas incumplidas.
