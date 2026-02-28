# Good Practices para trabajar con agentes

Este documento reúne prácticas recomendadas para colaborar con agentes de IA en flujos de desarrollo.

## Índice de temas

1. Define resultados observables y criterios de aceptación.
2. Proporciona contexto mínimo pero suficiente.
3. Divide el trabajo en incrementos verificables.
4. Valida siempre con pruebas o checks reproducibles.
5. Documenta decisiones y trazabilidad.
6. Usa **Skills** para tareas repetibles.

---

## Tema 6: Usa **Skills** para tareas repetibles

Cuando un flujo se repite (por ejemplo: scaffolding, checklist de release, migraciones similares, setup de entornos), conviene encapsularlo como una **Skill** reutilizable.

### Pasos recomendados

1. **Detecta la repetición**: identifica tareas que aparecen de forma recurrente.
2. **Define alcance y resultado**: especifica claramente entrada, salida y límites.
3. **Estandariza el procedimiento**: escribe pasos secuenciales, claros y ejecutables.
4. **Parametriza**: reemplaza valores fijos por parámetros (servicio, rama, entorno, etc.).
5. **Añade validaciones**: incorpora checks y criterios de “hecho”.
6. **Incluye ejemplos reales**: casos felices y edge-cases frecuentes.
7. **Versiona y mejora**: revisa la Skill con feedback del equipo.

📘 Desarrollo completo del tema: [tema6-skills.md](./tema6-skills.md)
