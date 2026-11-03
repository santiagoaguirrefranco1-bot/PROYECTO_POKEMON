public class Partida {
    Partida(Jugador jugador1, Jugador jugador2);

    void iniciar();
    Jugador getJugadorActual();
    Jugador getRival(Jugador jugador);
    boolean hayGanador();
    Jugador getGanador();
}