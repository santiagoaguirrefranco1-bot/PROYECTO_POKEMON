package Logica;

import java.util.ArrayList;
import java.util.List;

public final class FabricaCartas {
    private FabricaCartas() {}

    private static final ClaseTipo F = ClaseTipo.FUEGO;
    private static final ClaseTipo A = ClaseTipo.AGUA;
    private static final ClaseTipo E = ClaseTipo.ELECTRICO;
    private static final ClaseTipo P = ClaseTipo.PSIQUICO;
    private static final ClaseTipo I = ClaseTipo.INCOLORO;

    public static ClaseCarta crear(String nombre) {
        return switch (nombre) {
            case "Pikachu" -> new ClasePokemon("Pikachu", E, 60, 1,
                    List.of(new ClaseAtaque("Mordisco", 1, E, 20)));
            case "Charmander" -> new ClasePokemon("Charmander", F, 60, 1,
                    List.of(new ClaseAtaque("Ascuas", 1, F, 30)));
            case "Squirtle" -> new ClasePokemon("Squirtle", A, 60, 1,
                    List.of(new ClaseAtaque("Placaje", 1, A, 20)));
            case "Jigglypuff" -> new ClasePokemon("Jigglypuff", I, 70, 1,
                    List.of(new ClaseAtaque("Golpe", 1, I, 20)));
            case "Magikarp" -> new ClasePokemon("Magikarp", A, 30, 1,
                    List.of(new ClaseAtaque("Chapoteo", 1, A, 10)));
            case "Snorlax" -> new ClasePokemon("Snorlax", I, 150, 3,
                    List.of(new ClaseAtaque("Golpe Cuerpo", 3, I, 100)));
            case "Mewtwo ex" -> new ClasePokemonEx("Mewtwo ex", P, 150, 2,
                    List.of(new ClaseAtaque("Ataque Psíquico", 2, P, 50),
                            new ClaseAtaque("Psicorrayo", 3, P, 150)));
            case "Zapdos ex" -> new ClasePokemonEx("Zapdos ex", E, 130, 1,
                    List.of(new ClaseAtaque("Picotazo", 1, I, 30),
                            new ClaseAtaque("Tormenta Eléctrica", 3, E, 100)));
            case "Moltres ex" -> new ClasePokemonEx("Moltres ex", F, 130, 2,
                    List.of(new ClaseAtaque("Aleteo", 1, I, 30),
                            new ClaseAtaque("Danza Infernal", 3, F, 70)));
            case "Articuno ex" -> new ClasePokemonEx("Articuno ex", A, 130, 2,
                    List.of(new ClaseAtaque("Viento Helado", 1, A, 30),
                            new ClaseAtaque("Ventisca", 3, A, 100)));
            case "Investigación del Profesor" -> new InvestigacionProfesor();
            case "Poción" -> new Pocion();
            case "Poké Ball" -> new Pokeball();
            case "Turbo Velocidad" -> new TurboVelocidad();
            case "Misty" -> new Misty();
            default -> throw new IllegalArgumentException("Carta desconocida: " + nombre);
        };
    }

    private static void agregar(List<ClaseCarta> lista, String nombre, int copias) {
        for (int i = 0; i < copias; i++) {
            lista.add(crear(nombre));
        }
    }

    public static String[] nombresMazos() {
        return new String[] { "Mazo Fuego y Rayo", "Mazo Agua", "Mazo Psíquico" };
    }

    public static Mazo crearMazo(int opcion) {
        List<ClaseCarta> c = new ArrayList<>();
        switch (opcion) {
            case 1 -> {
                agregar(c, "Charmander", 2); agregar(c, "Moltres ex", 2);
                agregar(c, "Pikachu", 2);    agregar(c, "Zapdos ex", 2);
                agregar(c, "Jigglypuff", 2); agregar(c, "Snorlax", 2);
                agregar(c, "Poción", 2);     agregar(c, "Poké Ball", 2);
                agregar(c, "Turbo Velocidad", 2); agregar(c, "Investigación del Profesor", 2);
            }
            case 2 -> {
                agregar(c, "Squirtle", 2);   agregar(c, "Magikarp", 2);
                agregar(c, "Articuno ex", 2); agregar(c, "Snorlax", 2);
                agregar(c, "Jigglypuff", 2); agregar(c, "Misty", 2);
                agregar(c, "Poción", 2);     agregar(c, "Poké Ball", 2);
                agregar(c, "Turbo Velocidad", 2); agregar(c, "Investigación del Profesor", 2);
            }
            default -> {
                agregar(c, "Mewtwo ex", 2);  agregar(c, "Pikachu", 2);
                agregar(c, "Jigglypuff", 2); agregar(c, "Snorlax", 2);
                agregar(c, "Zapdos ex", 2);  agregar(c, "Magikarp", 2);
                agregar(c, "Poción", 2);     agregar(c, "Poké Ball", 2);
                agregar(c, "Turbo Velocidad", 2); agregar(c, "Investigación del Profesor", 2);
            }
        }
        return new Mazo(c);
    }
}