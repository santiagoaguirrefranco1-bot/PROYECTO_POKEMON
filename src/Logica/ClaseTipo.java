package Logica;

public enum ClaseTipo {
    FUEGO("F"), AGUA("A"), ELECTRICO("E"), PSIQUICO("P"), INCOLORO("I");

    private final String simbolo;

    ClaseTipo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

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