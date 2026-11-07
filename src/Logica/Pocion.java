package Logica;

public class Pocion extends Objeto {
    public Pocion() {
        super("Pocion");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        ClasePokemon activo = jugador.getActivo();
        if (activo == null) return false;
        activo.curar(20);
        partida.getVista().mostrarMensaje(activo.getNombre() + " recupera 20 PS (" + activo.getPsActual() + "/" + activo.getPsMax() + ")");
        return true;
    }
}