package Logica;

import java.util.ArrayList;
import java.util.List;

public class Misty extends Soporte {
    public Misty() {
        super("Misty");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        List<ClasePokemon> aguas = new ArrayList<>();
        List<String> nombres = new ArrayList<>();

        for (ClasePokemon p : jugador.getPokemonEnJuego()) {
            if (p.getTipo() == ClaseTipo.AGUA) {
                aguas.add(p);
                nombres.add(p.getNombre());
            }
        }

        if (aguas.isEmpty()) {
            partida.getVista().mostrarMensaje("No tienes Pokemon de Agua en juego");
            return false;
        }

        int i = partida.getVista().elegirOpcion("A cual Pokemon de Agua adjuntas 3 energias?", nombres, false);
        ClasePokemon destino = aguas.get(i);

        for (int k = 0; k < 3; k++) {
            destino.adjuntarEnergia(ClaseTipo.AGUA);
        }
        partida.getVista().mostrarMensaje("Misty adjunta 3 energias de Agua a " + destino.getNombre());
        return true;
    }
}