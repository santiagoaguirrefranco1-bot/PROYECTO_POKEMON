package Logica;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import Logica.Carta;
import Logica.CartaPokemon;
import Logica.Jugador;
import Logica.TipoEnergia;
import Logica.Vista;

public class Consola implements Vista {
    private static final String LINEA = "═".repeat(54);
    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    public void mostrarBienvenida() {
        System.out.println(LINEA);
        System.out.println("   POKEMON TCG POCKET — Genetic Apex (versión consola)");
        System.out.println(LINEA);
    }

    public String pedirNombre(String etiqueta) {
        System.out.print(etiqueta + ", ingresa tu nombre: ");
        String nombre = scanner.nextLine().trim();
        return nombre.isEmpty() ? etiqueta : nombre;
    }

    public int elegirMazo(String nombreJugador, String[] mazos) {
        System.out.println(nombreJugador + ", elige tu mazo:");
        for (int i = 0; i < mazos.length; i++) {
            System.out.println((i + 1) + ". " + mazos[i]);
        }
        return leerEntero(1, mazos.length);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    @Override
    public int elegirOpcion(String titulo, List<String> opciones, boolean permitirCancelar) {
        System.out.println(titulo);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        if (permitirCancelar) System.out.println("0. Cancelar");
        int n = leerEntero(permitirCancelar ? 0 : 1, opciones.size());
        return n - 1;
    }

    @Override
    public void mostrarCampo(Jugador actual, Jugador rival, int turno) {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("TURNO " + turno + " — " + actual.getNombre());
        System.out.println(LINEA);

        System.out.println("RIVAL (" + rival.getNombre() + ")" + barraDe(rival.getActivo()));
        imprimirCampo(rival);
        System.out.println("Puntos de premio rival: " + rival.getPuntosPremio() + "/3");
        System.out.println();

        System.out.println("TU CAMPO (" + actual.getNombre() + ")" + barraDe(actual.getActivo()));
        imprimirCampo(actual);
        System.out.println("Puntos de premio propios: " + actual.getPuntosPremio() + "/3");
        System.out.println();

        System.out.println("Zona de energía: " + actual.getZonaEnergia().getSimbolo()
                + (actual.isEnergiaDisponible() ? " (disponible)" : " (ya usada)"));
        System.out.println("Tu mano (" + actual.getMano().size() + " cartas):");
        int n = 1;
        for (Carta c : actual.getMano()) {
            System.out.println("  " + n++ + ". " + c.getNombre());
        }
        System.out.println(LINEA);
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Adjuntar energía de zona");
        System.out.println("2. Jugar carta de entrenador");
        System.out.println("3. Colocar Pokémon en banco");
        System.out.println("4. Retirar Pokémon activo");
        System.out.println("5. Atacar");
        System.out.println("6. Pasar turno");
        System.out.println(LINEA);
    }

    @Override
    public int leerAccion() {
        return leerEntero(1, 6);
    }

    @Override
    public void anunciarGanador(Jugador ganador, String motivo) {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("¡" + ganador.getNombre() + " es el nuevo Maestro Pokémon!");
        System.out.println("Victoria porque " + motivo + ".");
        System.out.println(LINEA);
    }

    private void imprimirCampo(Jugador j) {
        CartaPokemon activo = j.getActivo();
        System.out.println("[Activo] " + (activo == null ? "—" : activo.getNombre()
                + "   Energía adjunta: " + energias(activo)));
        StringBuilder banco = new StringBuilder("[Banco] ");
        if (j.getBanco().isEmpty()) banco.append("—");
        for (CartaPokemon p : j.getBanco()) {
            banco.append(p.getNombre()).append(" (").append(p.getPsActual()).append(" PS)  ");
        }
        System.out.println(banco);
    }

    private String barraDe(CartaPokemon p) {
        if (p == null) return "";
        int llenos = (int) Math.round(10.0 * p.getPsActual() / p.getPsMax());
        if (p.getPsActual() > 0 && llenos == 0) llenos = 1;
        return "   PS: " + "█".repeat(llenos) + "░".repeat(10 - llenos)
                + " " + p.getPsActual() + "/" + p.getPsMax();
    }

    private String energias(CartaPokemon p) {
        if (p.getEnergias().isEmpty()) return "ninguna";
        StringBuilder sb = new StringBuilder();
        for (TipoEnergia e : p.getEnergias()) sb.append(e.getSimbolo());
        return sb.toString();
    }

    private int leerEntero(int min, int max) {
        while (true) {
            System.out.print("> ");
            String linea = scanner.nextLine().trim();
            try {
                int n = Integer.parseInt(linea);
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException e) {
            }
            System.out.println("Opción inválida. Ingresa un número entre " + min + " y " + max + ".");
        }
    }
}
