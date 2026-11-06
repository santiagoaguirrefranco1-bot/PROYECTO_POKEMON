package Logica;

public class InvestigacionProfesor extends Soporte {
    public InvestigacionProfesor() {
        super("Investigación del Profesor");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        jugador.descartarMano();
        jugador.robar();
        jugador.robar();
        System.out.println(jugador.getNombre() + " descarta su mano y roba 2 cartas");
        return true;
    }
}