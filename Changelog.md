Tarea 1:
*(NEW) Esqueleto de la pantalla principal
*(NEW) Sistema de navegación.
*(NEW) Pantalla de "Preguntas".
*(NEW) Pantalla de "Eventos".
*(ENH) Reestructuración de la app.
*(FIX) Padding values.

Tarea 2:
*(NEW) Clase Application.
*(ENH) Nombre Application en el Manifest.
*(FIX) Arreglo Previews.
*(FIX) Arreglo "dynamicColor" para que funcione el tema personalizado.
*(NEW) Logs Ciclo de vida de la MainActivity.
*(NEW) Implementado Room
*(NEW) Entidades, interfaces DAO y creación de la BDD.
*(ENH) Subir versión Kotlin a la 2.1.0
*(FIX) Añadir EventoBean a la BDD.
*(NEW): Permisos de acceso a Internet y almacenamiento.
*(ENH) Fijada la orientación del MainActivity en vertical.

*(ENH) Nuevas funciones en Interfaces DAO.
*(NEW) Prueba API de internet (lo dejo como referencia para más adelante).

Tarea 3:
*(NEW) Nuevas funciones Interfaces DAO.
*(NEW) Implementación SQLite y testeo en MainActivity.
*(ENH) Nuevos campos "leida" y "leido" en las entidades "Preguntas" y "Eventos" respectivamente.

*(ENH) Movida creación de la base de datos a la clase Application.

Tarea 4:
*(ENH) Llamar a la API sólo si hay conexión a internet.
*(NEW) Implementar Hilt.
*(NEW) Implementar ViewModel.
*(ENH) Preguntas y respuestas se muestran en pantalla sacándolas de la BDD (falta usar StateFlow)
*(ENH) Lógica de marcar preguntas como leídas y reiniciar lista de preguntas si ya se han leído todas.
*(ENH) Control de errores en queries a Preguntas.
*(ENH) Acceso a una API de Internet utilizando Retrofit.
*(ENH) Añadir ViewModels a MainScreen y a EventosScreen.
*(ENH) Limpiada la clase MainActivity.
*(ENH) Implementación de StateFlow en los ViewModels.
*(FIX) Quitar fijar pantalla a modo Retrato.

Tarea 5:
*(NEW) Repositorios para manejar la lógica de BDD, shared preferences y APIs.
*(FIX) Pasar función lambda para onBackClick en lugar de navController.
*(NEW) Use Case para Preguntas, shared preferences y API de Chuck Norris.
*(NEW) AlertDialog al responder preguntas.
*(ENH) Manejo de tablas vacías (temporal).
*(FIX) loadPregunta y loadEvento ya no se llaman dos veces al abrir las pantallas.