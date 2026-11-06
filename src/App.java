import Logica.Consola;
import Logica.FabricaCartas;
import Logica.Jugador;
import Logica.Mazo;
import Logica.Partida;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Consola consola = new Consola();
        consola.mostrarBienvenida();

        String nombre1 = consola.pedirNombre("Entrenador 1");
        Mazo mazo1 = FabricaCartas.crearMazo(consola.elegirMazo(nombre1, FabricaCartas.nombresMazos()));
        String nombre2 = consola.pedirNombre("Entrenador 2");
        Mazo mazo2 = FabricaCartas.crearMazo(consola.elegirMazo(nombre2, FabricaCartas.nombresMazos()));

        if (!mazo1.esValido() || !mazo2.esValido()) {
            System.out.println("Alguno de los mazos no es valido.");
            return;
        }

        Partida partida = new Partida(new Jugador(nombre1, mazo1), new Jugador(nombre2, mazo2), consola);
        partida.jugar();
    }
}