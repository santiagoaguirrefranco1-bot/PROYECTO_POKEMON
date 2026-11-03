package Logica;

public class TurboVelocidad extends Objeto {
    public TurboVelocidad() {
        super("Turbo Velocidad");
    }

    @Override
    public boolean activar(Jugador jugador) {
        if (jugador.getActivo() == null) return false;
        jugador.sumarReduccionRetirada(1);
        System.out.println("El costo de retirada baja en 1 este turno");
        return true;
    }
}
