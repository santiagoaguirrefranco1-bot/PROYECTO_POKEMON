package Logica;

public class Pocion extends Objeto {
    public Pocion() {
        super("Poción");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
    ClasePokemon activo = jugador.getActivo();
    if (activo == null) return false;
    activo.curar(20);
    System.out.println(activo.getNombre() + " recupera 20 PS");
    return true;
    }
}