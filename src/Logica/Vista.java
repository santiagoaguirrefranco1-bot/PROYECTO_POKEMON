package Logica;

import java.util.List;

public interface Vista {
    void mostrarMensaje(String mensaje);
    int elegirOpcion(String titulo, List<String> opciones, boolean permitirCancelar);
    void mostrarCampo(Jugador actual, Jugador rival, int turno);
    int leerAccion();
    void anunciarGanador(Jugador ganador, String motivo);
}