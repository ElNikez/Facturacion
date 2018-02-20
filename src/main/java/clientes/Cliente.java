package clientes;

import facturas.Tarifa;

import java.util.Calendar;
import java.util.Date;

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
        this.fechaDeAlta = null;
        this.tarifa = tarifa;
    }
    public Cliente(Cliente cliente) {
        this.nif = cliente.nif;
        this.nombre = cliente.nombre;
        this.correoElectronico = cliente.correoElectronico;
        this.direccion = cliente.direccion;
        this.fechaDeAlta = Calendar.getInstance();
        this.tarifa = cliente.tarifa;
    }

    //Recuperar los datos de un cliente a partir de su NIF.
    public String getNif(){
        return nif;
    }

    public Tarifa getTarifa(){
        return tarifa;
    }

    public boolean setTarifa(Tarifa nuevaTarifa){

        tarifa = nuevaTarifa;
        return true;
    }

    public Calendar getFecha(){

        return fechaDeAlta;

    }
}
