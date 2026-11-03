package Logica;

public class InterfazConsola {
    public String leerTexto(String mensaje);
    public int leerOpcion(String mensaje, int minimo, int maximo);
    public void mostrarMensaje(String mensaje);
    public void mostrarEstadoTurno(Partida partida);
}