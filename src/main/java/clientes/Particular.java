package clientes;

import tarifas.Tarifa;

public class Particular extends Cliente {

    private String apellidos;

    public Particular() {
        super();
    }

    public Particular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion, tarifa);
        this.apellidos = apellidos;
    }
}
