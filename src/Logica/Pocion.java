package Logica;

public class Pocion extends Objeto {
    public Pocion() {
        super("Pocion");
    }

    @Override //necesito las variables de jugador y partida
    public boolean activar () {
    return true;
    }
}