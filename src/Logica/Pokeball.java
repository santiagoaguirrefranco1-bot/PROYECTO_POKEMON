package Logica;

public class Pokeball extends Objeto {
    public Pokeball() {
        super("Poke ball");
    }

    @Override
        public boolean activar(Jugador jugador, Partida partida) {
        ClasePokemon encontrado = jugador.getMazo().sacarPokemonBasico();
        if (encontrado == null) {
        System.out.println("No quedan Pokémon básicos en el mazo.");
        return false;
        }
        jugador.agregarAMano(encontrado);
        jugador.getMazo().barajar();
        System.out.println("Encontraste a " + encontrado.getNombre() + " y lo pones en tu mano");
        return true;
    }
}