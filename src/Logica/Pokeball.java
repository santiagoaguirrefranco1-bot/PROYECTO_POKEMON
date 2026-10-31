package Logica;

public class Pokeball extends Objeto {
    public Pokeball() {
        super("Pokeball");
    }

    @Override //necesito alguna variable para ver si el pokemon es basico y ver si esta en el mazo
    public boolean activar() {
        return true;
    }
}