"# patronage_fp" 

Esta aplicación complementa al juego de mesa Patronage (es un juego que estoy desarrollando).
Patronage es un emocionante juego de mesa familiar que combina estrategia, conocimientos y suerte. 
Los jugadores competirán por crear impresionantes colecciones de arte, respondiendo preguntas para ganar recursos, 
enfrentando eventos inesperados y tomando decisiones clave que podrían cambiarlo todo. 
¡Solo el mejor mecenas logrará completar dos colecciones y alzarse como el gran protector del arte!

Esta app permitirá a los jugadores ver los eventos aleatorios, las preguntas y sus recompensas, 
en lugar de usar tarjetas físicas. Esto me otorga mayor control, ya que un juego de mesa, una vez impreso, no se puede actualizar.

Al iniciar la app se conectará a un repositorio/API/servidor (esto tendré que pensar cómo lo hago más adelante)
donde leerá el archivo "version_preguntas.txt" y lo comparará con la versión actual que tenga guardada.
Si es distinta, entonces descargará el archivo csv con las preguntas e iniciará el proceso de volcado 
en la base de datos interna de la app.

#main
Rama estable para producción. No trabajar directamente sobre ella.

#developer
Rama principal para desarrollo.