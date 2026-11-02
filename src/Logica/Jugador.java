package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jugador {
    private static final int MAX_BANCO = 2;
    private static final Random RANDOM = new Random();

    private final String nombre;
    private final Mazo mazo;
    private final List<ClaseCarta> mano = new ArrayList<>();
    private final List<ClaseCarta> descarte = new ArrayList<>();
    private final List<ClasePokemon> banco = new ArrayList<>();
    private ClasePokemon activo;
    private int puntosPremio;

    private ClaseTipo zonaEnergia;
    private boolean energiaDisponible;
    private boolean soporteJugado;
    private int reduccionRetirada;

    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;
    }

    //Da 5 cartas y si no hay Pokemon basico hace mulligan, por ultimo devuelve cuántos mulligans hizo

    public int repartirManoInicial() {
        int mulligans = 0;
        mazo.barajar();
        do {
            for (int i = 0; i < 5; i++) {
                robar();
            }
            if (!tienePokemonEnMano()) {
                mazo.agregarTodas(mano);
                mano.clear();
                mazo.barajar();
                mulligans++;
            }
        } while (!tienePokemonEnMano());
        return mulligans;
    }

    public void colocarActivo(ClasePokemon p) {
        mano.remove(p);
        activo = p;
    }

    public boolean colocarEnBanco(ClasePokemon p) {
        if (banco.size() >= MAX_BANCO) return false;
        mano.remove(p);
        banco.add(p);
        return true;
    }

    //inicia el turno 

    public void iniciarTurno() {
        soporteJugado = false;
        reduccionRetirada = 0;
        List<ClaseTipo> tipos = mazo.getTiposPokemon();
        zonaEnergia = tipos.get(RANDOM.nextInt(tipos.size()));
        energiaDisponible = true;
    }

    //false si el mazo esta vacio osea el jugador pierde

    public boolean robar() {
        ClaseCarta c = mazo.robar();
        if (c == null) return false;
        mano.add(c);
        return true;
    }

    public boolean adjuntarEnergia(ClasePokemon destino) {
        if (!energiaDisponible) return false;
        destino.adjuntarEnergia(zonaEnergia);
        energiaDisponible = false;
        return true;
    }

    public int getCostoRetiradaEfectivo() {
    int costo = activo.getCostoRetirada() - reduccionRetirada;
    if (costo < 0) {
        costo = 0;
    }
    return costo;
    }

    public boolean puedeRetirar() {
        return activo != null && !banco.isEmpty() && activo.getEnergias().size() >= getCostoRetiradaEfectivo();
    }

    public boolean retirarActivo(int indiceBanco) {
        if (!puedeRetirar()) return false;
        activo.descartarEnergias(getCostoRetiradaEfectivo());
        ClasePokemon nuevo = banco.remove(indiceBanco);
        banco.add(activo);
        activo = nuevo;
        return true;
    }

    //cuando se derrota el Pokemon

    public void eliminarActivo() {
        descarte.add(activo);
        activo = null;
    }

    public void promoverDesdeBanco(int indice) {
        activo = banco.remove(indice);
    }

    public boolean tienePokemonEnJuego() {
        return activo != null || !banco.isEmpty();
    }

    public void sumarPremio(int puntos) {
        puntosPremio += puntos;
    }

    //Mano 

    public boolean tienePokemonEnMano() {
        for (ClaseCarta c : mano) {
            if (c instanceof ClasePokemon) return true;
        }
        return false;
    }

    public void descartarMano() {
        descarte.addAll(mano);
        mano.clear();
    }

    public void agregarAMano(ClaseCarta c) { mano.add(c); }
    public boolean quitarDeMano(ClaseCarta c) { return mano.remove(c); }
    public void descartar(ClaseCarta c) { descarte.add(c); }

    //Esto sirve para saber si en ese turno se uso un soporte o turbo velocidad

    public boolean isSoporteJugado() { return soporteJugado; }
    public void marcarSoporteJugado() { soporteJugado = true; }
    public void sumarReduccionRetirada(int n) { reduccionRetirada += n; }

    public String getNombre() { return nombre; }
    public Mazo getMazo() { return mazo; }
    public ClasePokemon getActivo() { return activo; }
    public int getPuntosPremio() { return puntosPremio; }
    public ClaseTipo getZonaEnergia() { return zonaEnergia; }
    public boolean isEnergiaDisponible() { return energiaDisponible; }
    public List<ClaseCarta> getMano() { return Collections.unmodifiableList(mano); }
    public List<ClasePokemon> getBanco() { return Collections.unmodifiableList(banco); }

    public List<ClasePokemon> getPokemonEnJuego() {
        List<ClasePokemon> lista = new ArrayList<>();
        if (activo != null) lista.add(activo);
        lista.addAll(banco);
        return lista;
    }
}