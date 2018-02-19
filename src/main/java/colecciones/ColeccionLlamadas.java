package colecciones;

import facturas.Llamada;

import java.util.HashMap;
import java.util.LinkedList;

public class ColeccionLlamadas {

    private HashMap<String, LinkedList<Llamada>> llamadas;

    public ColeccionLlamadas() {
        llamadas = new HashMap<>();
    }

}
