import Logica.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<ClaseAtaque> ataques = new ArrayList<>();
        ataques.add(new ClaseAtaque("Mordisco", 1, ClaseTipo.ELECTRICO, 20));
        ClasePokemon pikachu = new ClasePokemon("Pikachu", ClaseTipo.ELECTRICO, 60, 1, ataques);

        pikachu.recibirDanio(30);
        System.out.println("PS despues de 30 de daño: " + pikachu.getPsActual());
        pikachu.curar(100);
        System.out.println("PS despues de curar de mas: " + pikachu.getPsActual());

        System.out.println("Puede atacar sin energia: " + pikachu.puedeAtacar(ataques.get(0)));
        pikachu.adjuntarEnergia(ClaseTipo.ELECTRICO);
        System.out.println("Puede atacar con energia: " + pikachu.puedeAtacar(ataques.get(0)));

        ClasePokemon mewtwo = new ClasePokemonEx("Mewtwo ex", ClaseTipo.PSIQUICO, 150, 2, ataques);
        System.out.println("Premio Pikachu: " + pikachu.getPuntosPremio());
        System.out.println("Premio Mewtwo ex: " + mewtwo.getPuntosPremio());

        System.out.println("Agua debil a Fuego: " + ClaseTipo.AGUA.esDebilA(ClaseTipo.FUEGO));

        List<ClaseCarta> cartas = new ArrayList<>();
        cartas.add(pikachu);
        cartas.add(new Pocion());
        Jugador yugi = new Jugador("yugi", new Mazo(cartas));
        yugi.agregarAMano(pikachu);
        yugi.colocarActivo(pikachu);
        pikachu.recibirDanio(40);
        Jugador rival = new Jugador("rival", new Mazo(new ArrayList<>(cartas)));
        Partida partida = new Partida(yugi, rival, new Consola());
        new Pocion().activar(yugi, partida);
        System.out.println("PS tras Pocion: " + pikachu.getPsActual());
    }
}