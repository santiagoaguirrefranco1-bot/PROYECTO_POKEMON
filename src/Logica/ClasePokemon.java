package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClasePokemon extends ClaseCarta {
    private final ClaseTipo tipo;
    private final int psMax;
    private int psActual;
    private final int costoRetirada;
    private final List<ClaseAtaque> ataques;
    private final List<ClaseTipo> energias = new ArrayList<>();

    public ClasePokemon(String nombre, ClaseTipo tipo, int ps,
                        int costoRetirada, List<ClaseAtaque> ataques) {
        super(nombre);
        this.tipo = tipo;
        this.psMax = ps;
        this.psActual = ps;
        this.costoRetirada = costoRetirada;
        this.ataques = new ArrayList<>(ataques);
    }
    
    public void recibirDanio(int danio){
    psActual = psActual - danio;
    if(psActual < 0) {
        psActual = 0;
        }
    }
    
    public void curar(int ps){
    psActual = psActual + ps;
    if (psActual > psMax) {
        psActual = psMax;
        }
    }

    public boolean estaDerrotado() {
        return psActual == 0;
    }

    public void adjuntarEnergia(ClaseTipo energia) {
        energias.add(energia);
    }

    public void descartarEnergias(int cantidad){
        for (int i = 0; i < cantidad && !energias.isEmpty(); i++){
            energias.remove(energias.size() - 1);
        }
    }

    // El costo incoloro se paga con cualquier energía los demás con su mismo tipo
    public boolean puedeAtacar(ClaseAtaque ataque) {
        if (ataque.getTipoCosto() == ClaseTipo.INCOLORO) {
            return energias.size() >= ataque.getCosto();
        }
        int coinciden = 0;
        for (ClaseTipo e : energias) {
            if (e == ataque.getTipoCosto()) coinciden++;
        }
        return coinciden >= ataque.getCosto();
    }

    // Los Pokémon-ex sobrescriben este método para dar 2.
    public int getPuntosPremio() {
        return 1;
    }

    public ClaseTipo getTipo(){
        return tipo;
    }
    public int getPsMax(){
        return psMax; 
    }
    public int getPsActual(){ 
        return psActual;
    }
    public int getCostoRetirada(){
        return costoRetirada; 
    }
    public List<ClaseAtaque> getAtaques(){
        return Collections.unmodifiableList(ataques);
    }
    public List<ClaseTipo> getEnergias(){
        return Collections.unmodifiableList(energias); 
    }
}

