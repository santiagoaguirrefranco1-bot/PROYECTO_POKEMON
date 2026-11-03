package Logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    private static final int PREMIOS_PARA_GANAR = 3;

    private final Jugador[] jugadores;
    private final Vista vista;
    private int turno = 0;
    private int indiceActual;
    private Jugador ganador;
    private String motivo;

    public Partida(Jugador j1, Jugador j2, Vista vista) {
        this.jugadores = new Jugador[] { j1, j2 };
        this.vista = vista;
        this.indiceActual = new Random().nextInt(2);
    }

    public Vista getVista() {
        return vista;
    }

    public void jugar() {
        for (Jugador j : jugadores) {
            prepararJugador(j);
        }
        while (ganador == null) {
            turno++;
            Jugador actual = jugadores[indiceActual];
            Jugador rival = jugadores[1 - indiceActual];

            actual.iniciarTurno();
            if (!actual.robar()) {
                terminar(rival, actual.getNombre() + " no tiene cartas para robar");
                break;
            }
            jugarTurno(actual, rival);
            indiceActual = 1 - indiceActual;
        }
        vista.anunciarGanador(ganador, motivo);
    }

    private void prepararJugador(Jugador j) {
        int mulligans = j.repartirManoInicial();
        if (mulligans > 0) {
            vista.mostrarMensaje(j.getNombre() + " hizo " + mulligans + " mulligan(s) por no tener Pokémon básico.");
        }
        List<CartaPokemon> basicos = pokemonEnMano(j);
        int i = vista.elegirOpcion(j.getNombre() + ": elige tu Pokémon activo", nombres(basicos), false);
        j.colocarActivo(basicos.get(i));

        while (j.getBanco().size() < 2) {
            basicos = pokemonEnMano(j);
            if (basicos.isEmpty()) break;
            i = vista.elegirOpcion(j.getNombre() + ": ¿colocas un Pokémon en el banco?", nombres(basicos), true);
            if (i < 0) break;
            j.colocarEnBanco(basicos.get(i));
        }
    }

    private void jugarTurno(Jugador j, Jugador rival) {
        boolean terminado = false;
        while (!terminado && ganador == null) {
            vista.mostrarCampo(j, rival, turno);
            switch (vista.leerAccion()) {
                case 1 -> adjuntarEnergia(j);
                case 2 -> jugarEntrenador(j);
                case 3 -> colocarEnBanco(j);
                case 4 -> retirar(j);
                case 5 -> terminado = atacar(j, rival);
                case 6 -> terminado = true;
            }
        }
    }

    private void adjuntarEnergia(Jugador j) {
        if (!j.isEnergiaDisponible()) {
            vista.mostrarMensaje("Ya usaste la energía de este turno.");
            return;
        }
        List<CartaPokemon> destinos = j.getPokemonEnJuego();
        int i = vista.elegirOpcion("¿A qué Pokémon adjuntas la energía?", nombres(destinos), true);
        if (i >= 0) {
            j.adjuntarEnergia(destinos.get(i));
            vista.mostrarMensaje("Energía " + j.getZonaEnergia().getSimbolo() + " adjuntada a " + destinos.get(i).getNombre() + ".");
        }
    }

    private void jugarEntrenador(Jugador j) {
        List<CartaEntrenador> entrenadores = new ArrayList<>();
        for (Carta c : j.getMano()) {
            if (c instanceof CartaEntrenador e) entrenadores.add(e);
        }
        if (entrenadores.isEmpty()) {
            vista.mostrarMensaje("No tienes cartas de entrenador en la mano.");
            return;
        }
        List<String> nombres = new ArrayList<>();
        for (CartaEntrenador e : entrenadores) {
            nombres.add(e.getNombre() + (e instanceof Soporte ? " (Soporte)" : " (Objeto)"));
        }
        int i = vista.elegirOpcion("¿Qué carta de entrenador juegas?", nombres, true);
        if (i < 0) return;

        CartaEntrenador carta = entrenadores.get(i);
        if (carta instanceof Soporte && j.isSoporteJugado()) {
            vista.mostrarMensaje("Solo puedes jugar 1 carta de Soporte por turno.");
            return;
        }
        j.quitarDeMano(carta);
        if (carta.activar(j, this)) {
            j.descartar(carta);
            if (carta instanceof Soporte) j.marcarSoporteJugado();
        } else {
            j.agregarAMano(carta);
        }
    }

    private void colocarEnBanco(Jugador j) {
        if (j.getBanco().size() >= 2) {
            vista.mostrarMensaje("Tu banco está lleno.");
            return;
        }
        List<CartaPokemon> basicos = pokemonEnMano(j);
        if (basicos.isEmpty()) {
            vista.mostrarMensaje("No tienes Pokémon en la mano.");
            return;
        }
        int i = vista.elegirOpcion("¿Qué Pokémon colocas en el banco?", nombres(basicos), true);
        if (i >= 0) j.colocarEnBanco(basicos.get(i));
    }

    private void retirar(Jugador j) {
        if (j.getBanco().isEmpty()) {
            vista.mostrarMensaje("No tienes Pokémon en el banco.");
            return;
        }
        if (!j.puedeRetirar()) {
            vista.mostrarMensaje("Necesitas " + j.getCostoRetiradaEfectivo() + " energía(s) adjunta(s) para retirar.");
            return;
        }
        int i = vista.elegirOpcion("¿Qué Pokémon pasa a activo? (costo: " + j.getCostoRetiradaEfectivo() + " energía/s)",
                nombres(j.getBanco()), true);
        if (i >= 0) j.retirarActivo(i);
    }

    private boolean atacar(Jugador j, Jugador rival) {
        if (turno == 1) {
            vista.mostrarMensaje("El primer jugador no puede atacar en su primer turno.");
            return false;
        }
        CartaPokemon atacante = j.getActivo();
        List<String> opciones = new ArrayList<>();
        for (Ataque a : atacante.getAtaques()) opciones.add(a.toString());
        int i = vista.elegirOpcion("Elige un ataque", opciones, true);
        if (i < 0) return false;

        Ataque ataque = atacante.getAtaques().get(i);
        if (!atacante.puedeAtacar(ataque)) {
            vista.mostrarMensaje("No tienes las energías necesarias para " + ataque.getNombre() + ".");
            return false;
        }
        CartaPokemon defensor = rival.getActivo();
        int danio = ataque.getDanioBase();
        if (defensor.getTipo().esDebilA(atacante.getTipo())) {
            danio += 20;
            vista.mostrarMensaje("¡Es súper efectivo! +20 de daño.");
        }
        defensor.recibirDanio(danio);
        vista.mostrarMensaje(atacante.getNombre() + " usa " + ataque.getNombre() + " y hace " + danio + " de daño a " + defensor.getNombre() + ".");

        if (defensor.estaDerrotado()) {
            resolverDerrota(j, rival);
        }
        return true;
    }

    private void resolverDerrota(Jugador atacante, Jugador defensor) {
        CartaPokemon derrotado = defensor.getActivo();
        int premio = derrotado.getPuntosPremio();
        atacante.sumarPremio(premio);
        defensor.eliminarActivo();
        vista.mostrarMensaje(derrotado.getNombre() + " fue derrotado. " + atacante.getNombre() + " gana " + premio + " punto(s) de premio.");

        if (atacante.getPuntosPremio() >= PREMIOS_PARA_GANAR) {
            terminar(atacante, "acumuló " + PREMIOS_PARA_GANAR + " puntos de premio");
        } else if (defensor.getBanco().isEmpty()) {
            terminar(atacante, "el rival se quedó sin Pokémon en juego");
        } else {
            int i = vista.elegirOpcion(defensor.getNombre() + ": elige tu nuevo Pokémon activo", nombres(defensor.getBanco()), false);
            defensor.promoverDesdeBanco(i);
        }
    }

    private void terminar(Jugador g, String razon) {
        ganador = g;
        motivo = razon;
    }

    private List<CartaPokemon> pokemonEnMano(Jugador j) {
        List<CartaPokemon> lista = new ArrayList<>();
        for (Carta c : j.getMano()) {
            if (c instanceof CartaPokemon p) lista.add(p);
        }
        return lista;
    }

    private List<String> nombres(List<? extends Carta> cartas) {
        List<String> n = new ArrayList<>();
        for (Carta c : cartas) n.add(c.getNombre());
        return n;
    }
}
