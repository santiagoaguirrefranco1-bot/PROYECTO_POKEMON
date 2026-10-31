package Logica;

public abstract class ClaseCarta {
    private String nombre;

    public ClaseCarta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
