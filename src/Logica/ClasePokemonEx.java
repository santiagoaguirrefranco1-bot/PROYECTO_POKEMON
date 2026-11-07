package Logica;

import java.util.List;

public class ClasePokemonEx extends ClasePokemon {
    public ClasePokemonEx(String nombre, ClaseTipo tipo,
                int ps, int costoRetirada, List<ClaseAtaque> ataques){
                super(nombre, tipo, ps, costoRetirada, ataques);
    }

    //los pokemon ex otorga dos puntos de premio
    @Override
    public int getPuntosPremio(){
        return 2;
    }
}