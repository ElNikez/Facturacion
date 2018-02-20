package clientes;

import facturas.Tarifa;

import java.util.Calendar;

public class Cliente {

    private String nif, nombre, correoElectronico;
    private Direccion direccion;
    private Calendar fechaDeAlta;
    private Tarifa tarifa;

    public Cliente() {
        super();
    }

    public Cliente(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        this.nif = nif;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.fechaDeAlta = Calendar.getInstance();
        this.tarifa = tarifa;
    }

    public String getNif() {
        return nif;
    }

    public Calendar getFechaDeAlta() {
        return fechaDeAlta;
    }
}
