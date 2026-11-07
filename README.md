# PROYECTO_POKEMON

Pokemon TCG Pocket — Simulador por consola

Simulacion por consola, escrita en Java 21, del juego de cartas Pokemon TCG Pocket con las mecanicas esenciales de la primera expansion, Genetic Apex (Mewtwo, Pikachu y Charizard). Dos entrenadores se enfrentan por turnos en la misma consola, cada uno con su mazo de 20 cartas.

El proyecto esta hecho con los cuatro pilares de la Programacion: encapsulamiento, herencia, polimorfismo e interfaces. No usa ninguna libreria externa, solo Java SE estandar.

Integrantes:

Nombre	           parte principal
Diego Garzon	   CartaEntrenador/Jugador/Mazo
Johan Benitez	   Consola/Partida/FabricaCartas
Santiago Aguirre   ClaseCarta/CartaPokemon/ClaseAtaque

Como funciona una partida

1. Preparacion

Cada entrenador escribe su nombre y elige uno de tres mazos predefinidos: Fuego y Rayo, Agua o Psiquico.
Cada mazo se valida: exactamente 20 cartas, maximo 2 copias de la misma carta y al menos 6 Pokemon.
Se baraja el mazo y se reparten 5 cartas. Si ninguna es un Pokemon, se hace mulligan: la mano vuelve al mazo, se baraja y se reparte de nuevo.
Cada entrenador elige su Pokemon activo y puede poner hasta 2 en el banco.
El jugador que empieza se elige al azar.

2. Un turno

Al empezar su turno, el jugador roba 1 carta y recibe 1 energia de la zona de energia. Despues elige acciones en un menu numerado:

Adjuntar energia de la zona a cualquier Pokemon propio (una vez por turno).
Jugar una carta de entrenador. Los Objetos no tienen limite, pero solo se puede jugar 1 Soporte por turno.
Colocar un Pokemon de la mano en el banco, si hay espacio.
Retirar el Pokemon activo, descartando tantas energias como su costo de retirada.
Atacar (solo una vez, y termina el turno).
Pasar turno.

El primer jugador no puede atacar en su primer turno.

3. Combate

Un ataque solo se puede usar si el Pokemon activo tiene las energias requeridas. El costo incoloro se paga con cualquier energia.
El daño se aplica al Pokemon activo rival, con +20 de daño por debilidad:
Atacante	Defensor débil
Fuego	    Agua
Agua	    Fuego
Electrico	Agua
Psiquico	Incoloro
Cuando los PS llegan a 0, el Pokemon sale del juego. El rival gana 1 punto de premio o 2 si era un Pokemon-ex y el dueño elige un nuevo activo desde su banco.

4. Como se gana

Acumular 3 puntos de premio.
que el rival se quede sin Pokemon en juego.
que el rival no pueda robar carta al inicio de su turno (mazo vacio).

Cartas implementadas

Pokemon	    Tipo	 PS	Pokemon	    Tipo	     PS,
Pikachu	  Electrico	 60,	Mewtwo ex	Psiquico	150,
Charmander	Fuego	 60,	Zapdos ex	Electrico	130,
Squirtle	Agua	 60,	Moltres ex	Fuego	    130,
Jigglypuff	Incoloro 70,	Articuno ex	Agua	    130,
Magikarp	Agua	 30,	Snorlax	    Incoloro	150.
Entrenador	                Tipo	         Efecto,
Investigacion del Profesor	Soporte	Descarta tu mano y roba 2 cartas,
Misty	                    Soporte	Adjunta 3 energias de Agua a uno de tus Pokemon de Agua,
Pocion	                    Objeto	Cura 20 PS al Pokemon activo,
Poke Ball	                Objeto	Busca un Pokemon basico en el mazo y lo pone en tu mano,
Turbo Velocidad	            Objeto	Reduce en 1 el costo de retirada del activo este turno.

Como se ejecuta el codigo:

App pide los nombres y los mazos a traves de Consola, y crea las cartas con FabricaCartas.
Con eso arma dos Jugador (cada uno con su Mazo) y los entrega a Partida.
Partida lleva los turnos y las reglas. Cuando necesita hablar con el usuario, no imprime nada: le pide la accion a la interfaz Vista, que Consola implementa.
Las cartas de entrenador se juegan llamando a activar(jugador, partida), y cada una hace algo distinto.

Decisiones de diseño:

Costos de retirada: el enunciado no los define. Usamos 1 para Pikachu, Charmander, Squirtle, Jigglypuff, Magikarp y Zapdos ex; 2 para Mewtwo ex, Moltres ex y Articuno ex; y 3 para Snorlax.
Zona de energia: cada turno entrega energia de un tipo elegido al azar entre los tipos de Pokemon (sin Incoloro) con los que se armo el mazo.
Misty: adjunta siempre 3 energias al Pokemon de Agua que el jugador elija.
Atacar termina el turno.
Energias en la consola: se muestran con letras (F fuego, A agua, E electrico, P psiquico, I incoloro) para que se vean bien en cualquier terminal.

Requisito: tener instalado el JDK 21 o superior.
