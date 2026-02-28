# Good Practices

Este documento resume una guía de buenas prácticas para diseñar, desplegar y operar software con calidad y seguridad.

## Temario

1. Definir objetivos y riesgos
2. Diseñar interfaces y contratos claros
3. Automatizar pruebas y validaciones
4. Gestionar configuración y secretos
5. Observar el sistema (logs, métricas y trazas)
6. Desplegar de forma gradual y reversible
7. Operar con runbooks y aprendizaje continuo
8. Seguridad y control de daños ("blast radius")

## Flujo recomendado (paso a paso)

1. **Identificar activos críticos y amenazas** antes de implementar cambios.
2. **Diseñar controles preventivos** (autenticación, autorización, validaciones, segmentación).
3. **Aplicar controles detectivos** (auditoría, alertas, anomalías y telemetría).
4. **Reducir superficie de exposición** (mínimos permisos, componentes mínimos, secretos rotados).
5. **Aislar fallos por dominio** (entornos, redes, cuentas, colas, tenants o feature flags).
6. **Preparar mecanismos de contención** (kill switches, rate limits, degradación controlada).
7. **Planificar respuesta y recuperación** (runbooks, backups, restauración, rollback probado).
8. **Revisar incidentes y mejorar** (postmortems, hardening y pruebas de resiliencia).

## Desarrollo por temas

- [Tema 8: Seguridad y control de daños ("blast radius")](./tema8-seguridad-blast-radius.md)
