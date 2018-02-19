package colecciones;

import facturas.Factura;

import java.util.HashMap;

public class ColeccionFacturas {

    private HashMap<String, HashMap<Integer, Factura>> facturas;

    public ColeccionFacturas() {
        facturas = new HashMap<>();
    }

}
