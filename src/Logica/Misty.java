package Logica;

import java.util.ArrayList;
import java.util.List;

public class Misty extends Soporte {
    public Misty() {
        super("Misty");
    }
    // la logica seria recorrer los pokemon del jugador activo y en banca, se filtra y guarda en la lista aguas
    // unicamente los tipo agua, guarda sus nombres en una lista y mostrarlos en consola para que a la final el
    // jugador seleccione 1 y adjuntar hasta 3 de energia agua 
    // necesito la clase jugador y partida avanzada para poder implementar la logica necesaria
    @Override
    public boolean activar() {
        List<ClasePokemon> aguas = new ArrayList<>();
        List<String> nombres = new ArrayList<>();
        return true;
    }
}