package Logica;

public class TurboVelocidad extends Objeto {
    public TurboVelocidad() {
        super("Turbo Velocidad");
    }

    @Override //me falta alguna variable dentro de pokemon con el valor de costo retirada para restar -1
    public boolean activar() {
        return true;
    }
}
