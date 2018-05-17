package swing.modelo.clientes;

import swing.modelo.facturas.Tarifa;
import swing.modelo.misc.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cliente implements Fecha, Serializable {

    private String nif;
    private String nombre;
    private String correo;
    private Direccion direccion;
    private Tarifa tarifa;
    private Calendar fechaDeAlta;

    public Cliente(String nif, String nombre, String correo, Direccion direccion, Tarifa tarifa) {
        this.nif = nif;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.tarifa = tarifa;
        this.fechaDeAlta = GregorianCalendar.getInstance();
    }

    public String nif() {
        return this.nif;
    }

    public String nombre() {
        return this.nombre;
    }

    public String correo() {
        return this.correo;
    }

    public Direccion direccion() {
        return this.direccion;
    }

    public Tarifa tarifa() {
        return this.tarifa;
    }

    public boolean cambiarTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;

        return true;
    }

    @Override
    public Calendar fecha() {
        return this.fechaDeAlta;
    }

}
