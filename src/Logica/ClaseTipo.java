package Logica;

public enum ClaseTipo {
    FUEGO,AGUA,ELECTRICO,PSIQUICO,INCOLORO;

    //sirve cuando deteca un tipo atacante
    public boolean esDebilA(ClaseTipo atacante) {
    if (this == AGUA) {
        return atacante == FUEGO || atacante == ELECTRICO;
    }
    if (this == FUEGO) {
        return atacante == AGUA;
    }
    if (this == INCOLORO) {
        return atacante == PSIQUICO;
    }
    return false;

    }
    
}
