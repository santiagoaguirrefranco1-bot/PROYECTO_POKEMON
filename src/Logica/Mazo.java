package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mazo {
    private final List<ClaseCarta> cartas;
    private final List<ClaseTipo> tiposPokemon = new ArrayList<>();

    public Mazo(List<ClaseCarta> cartas) {
        this.cartas = new ArrayList<>(cartas);
        calcularTipos();
    }

    //Se ejecuta SOLO UNA VEZ al crear el mazo cuando aun estan las 20 cartas
    private void calcularTipos() {
        for (ClaseCarta c : cartas) {
            if (c instanceof ClasePokemon p && p.getTipo() != ClaseTipo.INCOLORO && !tiposPokemon.contains(p.getTipo())) {
                tiposPokemon.add(p.getTipo());
            }
        }
        if (tiposPokemon.isEmpty()) {
            tiposPokemon.add(ClaseTipo.INCOLORO);
        }
    }

    //20 cartas maximo 2 copias por nombre minimo 6 Pokémon basicos Todos los Pokemon del taller son basicos así que contamos ClasePokemon
    public boolean esValido() {
        if (cartas.size() != 20) return false;
        Map<String, Integer> copias = new HashMap<>();
        int pokemon = 0;
        for (ClaseCarta c : cartas) {
            String nombre = c.getNombre();
            if (copias.containsKey(nombre)) {
                copias.put(nombre, copias.get(nombre) + 1);
            } else {
                copias.put(nombre, 1);
            }
            if (c instanceof ClasePokemon) pokemon++;
        }
        for (int n : copias.values()) {
            if (n > 2) return false;
        }
        return pokemon >= 6;
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    //null si el mazo esta vacio
    public ClaseCarta robar() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(0);
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int getCantidad() {
        return cartas.size();
    }

    public void agregarTodas(List<ClaseCarta> otras) {
        cartas.addAll(otras);
    }

    //Busca el primer Pokemon basico lo saca del mazo y lo devuelve, null si no hay
    public ClasePokemon sacarPokemonBasico() {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i) instanceof ClasePokemon p) {
                cartas.remove(i);
                return p;
            }
        }
        return null;
    }

    //Tipos de Pokemon sin Incoloro con los que se armo el mazo, de ahí sale la energia de la zona
    public List<ClaseTipo> getTiposPokemon() {
        return Collections.unmodifiableList(tiposPokemon);
    }
}