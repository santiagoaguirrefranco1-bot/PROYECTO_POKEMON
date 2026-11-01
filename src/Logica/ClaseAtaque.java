package Logica;

public class ClaseAtaque {
    
    private final String nombre;
    private final int costo;
    private final ClaseTipo tipoCosto;
    private final int danioBase;

    public ClaseAtaque(String nombre, int costo, ClaseTipo tipoCosto, int danioBase){
        this.nombre = nombre;
        this.costo = costo;
        this.tipoCosto = tipoCosto;
        this.danioBase = danioBase;
    }
    
    public String getNombre(){ 
        return nombre; 
    }
    
    public int getCosto(){ 
        return costo; 
    }
    
    public ClaseTipo getTipoCosto(){ 
        return tipoCosto;
    }
    public int getDanioBase(){ 
        return danioBase;
    }

    @Override
    public String toString() {
        return nombre + " (" + costo + tipoCosto + "): " + danioBase + " daño";
    }
    

}
