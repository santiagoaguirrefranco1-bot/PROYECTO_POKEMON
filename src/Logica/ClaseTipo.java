package Logica;

public enum ClaseTipo {
    FUEGO,AGUA,ELECTRICO,PSIQUICO,INCOLORO;

    //sirve cuando deteca un tipo atacante
    public boolean esDebilA(ClaseTipo atacante) {
        return switch (this) {
            case AGUA -> atacante == FUEGO || atacante == ELECTRICO;
            case FUEGO -> atacante == AGUA;
            case INCOLORO -> atacante == PSIQUICO;
            default -> false;
        };
    }
}
