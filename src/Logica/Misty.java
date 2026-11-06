package Logica;

import java.util.ArrayList;
import java.util.List;

public class Misty extends Soporte {
    public Misty() {
        super("Misty");
    }
    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        for (ClasePokemon p : jugador.getPokemonEnJuego()) {
            if (p.getTipo() == ClaseTipo.AGUA) {
            for (int i = 0; i < 3; i++) {
                p.adjuntarEnergia(ClaseTipo.AGUA);
                }
                System.out.println("Misty adjunta 3 energias de Agua a " + p.getNombre());
                return true;
            }
    }
    System.out.println("No tienes Pokemon de Agua en juego");
    return false;
    }
}