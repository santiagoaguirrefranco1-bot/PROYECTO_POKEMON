import Logica.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // - - - - - - - P R U E B A S - - - - - - -
        //Crear un Pokemon a mano
        List<ClaseAtaque> ataques = new ArrayList<>();
        ataques.add(new ClaseAtaque("Mordisco", 1, ClaseTipo.ELECTRICO, 20));
        ClasePokemon pikachu = new ClasePokemon("Pikachu", ClaseTipo.ELECTRICO, 60, 1, ataques);

        //Daño y curacion
        pikachu.recibirDanio(30);
        System.out.println("PS despues de 30 de daño: " + pikachu.getPsActual());   // DEBE DE SER 30
        pikachu.curar(100);
        System.out.println("PS despues de curar de mas: " + pikachu.getPsActual());  // DEBE DE SER 60

        //Energia y ataque
        System.out.println("Puede atacar sin energia: " + pikachu.puedeAtacar(ataques.get(0)));  // false
        pikachu.adjuntarEnergia(ClaseTipo.ELECTRICO);
        System.out.println("Puede atacar con energia: " + pikachu.puedeAtacar(ataques.get(0)));  // true

        //Pokemon ex y basico
        ClasePokemon mewtwo = new ClasePokemonEx("Mewtwo ex", ClaseTipo.PSIQUICO, 150, 2, ataques);
        System.out.println("Premio Pikachu: " + pikachu.getPuntosPremio());   // DEBE DE SER 1
        System.out.println("Premio Mewtwo ex: " + mewtwo.getPuntosPremio());   // DEBE DE SER 2

        //Debilidades
        System.out.println("Agua debil a Fuego: " + ClaseTipo.AGUA.esDebilA(ClaseTipo.FUEGO));  // true

        //Probar una carta de entrenador con un jugador
        List<ClaseCarta> cartas = new ArrayList<>();
        cartas.add(pikachu);
        cartas.add(new Pocion());
        Jugador yugi = new Jugador("yugi", new Mazo(cartas));
        yugi.agregarAMano(pikachu);
        yugi.colocarActivo(pikachu);
        pikachu.recibirDanio(40);
        new Pocion().activar(yugi);
        System.out.println("PS tras Pocion: " + pikachu.getPsActual());        // DEBE DE SER 40
    }
}