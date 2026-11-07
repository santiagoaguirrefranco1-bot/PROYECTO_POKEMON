package Logica;

public class Pokeball extends Objeto {
    public Pokeball() {
        super("Poke ball");
    }

    @Override
    public boolean activar(Jugador jugador, Partida partida) {
        ClasePokemon encontrado = jugador.getMazo().sacarPokemonBasico();
        if (encontrado == null) {
            partida.getVista().mostrarMensaje("No quedan Pokemon basicos en el mazo");
            return false;
        }
        jugador.agregarAMano(encontrado);
        jugador.getMazo().barajar();
        partida.getVista().mostrarMensaje("Encontraste a " + encontrado.getNombre() + " y lo pones en tu mano");
        return true;
    }
}