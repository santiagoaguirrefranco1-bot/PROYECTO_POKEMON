package Logica;

public class Pocion extends Objeto {
    public Pocion() {
        super("Pocion");
    }

    @Override //necesito las variables de vida de los pokemones
    public boolean activar () {
    return true;
    }
}