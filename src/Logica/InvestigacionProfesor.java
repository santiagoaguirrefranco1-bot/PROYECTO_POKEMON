package Logica;

public class InvestigacionProfesor extends Soporte {
    public InvestigacionProfesor() {
        super("Investigacion del Profesor");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        jugador.descartarMano();
        jugador.robar();
        jugador.robar();
        partida.getVista().mostrarMensaje(jugador.getNombre() + " descarta tu mano y roba 2 cartas");
        return true;
    }
}