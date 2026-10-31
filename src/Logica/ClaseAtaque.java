package Logica;

public class ClaseAtaque {
    
    private final String nombre;
    private final int costo;
    private final ClaseTipo tipoCosto;
    private final int danioBase;

    public ClaseAtaque(String nombre, int costo, ClaseTipo tipoCosto, int danioBase) {
        this.nombre = nombre;
        this.costo = costo;
        this.tipoCosto = tipoCosto;
        this.danioBase = danioBase;
    }
    

}
