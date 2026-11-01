package Logica;

public class InvestigacionProfesor extends Soporte {
    public InvestigacionProfesor() {
        super("Investigación del Profesor");
    }

    @Override
    public boolean activar() {
        return true;
    }
}