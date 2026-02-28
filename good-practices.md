# Good Practices

Este documento resume prácticas recomendadas para desarrollar software mantenible, especialmente en APIs backend.

## Temas

1. **Responsabilidad única (SRP)**: cada clase o módulo debe tener un único motivo de cambio.  
   ➜ Desarrollo completo: [Tema 1 - Responsabilidad única](./tema-1-responsabilidad-unica.md)
2. **Nombres expresivos**: usa nombres que expliquen intención y eviten ambigüedades.
3. **Separación por capas**: divide dominio, aplicación y entrega (controllers) para reducir acoplamiento.
4. **Validación de entradas**: valida datos lo antes posible y devuelve errores claros.
5. **Pruebas automatizadas**: cubre casos de éxito, borde y error para facilitar refactors seguros.

---

> Recomendación: aplica estas prácticas de forma incremental. Empezar por SRP suele mejorar rápidamente la legibilidad y la testabilidad.
