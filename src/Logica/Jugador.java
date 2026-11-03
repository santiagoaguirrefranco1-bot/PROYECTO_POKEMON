package Logica;

public class Jugador {
    public Jugador(String nombre, Mazo mazo, Mano mano, Campo campo, ZonaEnergia zonaEnergia);

    public String getNombre();
    public Mazo getMazo();
    public Mano getMano();
    public Campo getCampo();
    public ZonaEnergia getZonaEnergia();

    public int getPuntosPremio();
    public void agregarPuntosPremio(int cantidad);
    public boolean tieneTresPuntosPremio();
}
