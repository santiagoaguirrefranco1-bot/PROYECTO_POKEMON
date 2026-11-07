package Logica;

public class TurboVelocidad extends Objeto {
    public TurboVelocidad() {
        super("Turbo Velocidad");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        if (jugador.getActivo() == null) return false;
        jugador.sumarReduccionRetirada(1);
        partida.getVista().mostrarMensaje("El costo de retirada baja en 1 este turno");
        return true;
    }
}