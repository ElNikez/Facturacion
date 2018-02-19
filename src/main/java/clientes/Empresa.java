package clientes;

import tarifas.Tarifa;

public class Empresa extends Cliente {

    public Empresa() {
        super();
    }

    public Empresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion,tarifa);
    }

}
