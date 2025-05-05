<body>

<h1>API Jokes con Spring Boot</h1>

<p>Este proyecto es una API REST y una interfaz web desarrolladas con <strong>Spring Boot</strong> para gestionar chistes organizados por categorías, flags, tipos e idiomas.</p>

<h2>📁 Estructura del Proyecto</h2>
<ul>
    <li><strong>Controladores REST y web</strong> claramente separados.</li>
    <li><strong>DTOs</strong> para la transferencia de datos limpia entre capas.</li>
    <li><strong>Servicios</strong> implementados con interfaces para facilitar la inyección de dependencias.</li>
    <li><strong>Manejo de excepciones</strong> personalizado y claro.</li>
</ul>

<h2>⚙️ Áreas de Mejora Futuras</h2>

<h3>1. Redundancia en DAO y Servicios</h3>
<p>Actualmente, tengo interfaces DAO y servicios específicos para cada entidad. Planeo implementar interfaces genéricas para reducir esta redundancia y mejorar la reutilización de código.</p>

<h3>2. Configuración de Jackson</h3>
<p>Mi configuración personalizada en Jackson podría simplificarse mediante anotaciones específicas en mis DTOs o entidades, haciendo mi configuración más clara y manejable.</p>

<h3>3. Optimización en Estructura de Recursos</h3>
<p>Aunque organizo bien mis plantillas, noto cierta duplicación de fragmentos como encabezados y pies de página. Trabajaré en la creación de fragmentos comunes reutilizables.</p>

<h3>4. Mejor organización en scripts SQL</h3>
<p>Actualmente, los scripts SQL podrían estar mejor organizados en subcarpetas según el tipo de operación o entidad, facilitando así su mantenimiento y claridad.</p>

<h2>🚧 Próximos Pasos</h2>
<ul>
    <li>Implementar documentación automática con <strong>Swagger</strong> o <strong>SpringDoc OpenAPI</strong>.</li>
    <li>Añadir pruebas unitarias y de integración.</li>
    <li>Mejorar la validación de entradas mediante anotaciones en DTOs.</li>
    <li>Crear un manejo global uniforme de excepciones utilizando <code>@ControllerAdvice</code>.</li>
</ul>

<p>¡Gracias por revisar mi proyecto! Toda sugerencia o contribución es bienvenida.</p>

</body>
</html>
