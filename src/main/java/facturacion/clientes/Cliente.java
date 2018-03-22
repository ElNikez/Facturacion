package facturacion.clientes;

import facturacion.facturas.Tarifa;
import facturacion.interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;

public class Cliente implements Fecha, Serializable {

    private String nif;
    private String nombre;
    private String correoElectronico;
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

    public Cliente(Cliente cliente) {
        this.nif = cliente.nif;
        this.nombre = cliente.nombre;
        this.correoElectronico = cliente.correoElectronico;
        this.direccion = cliente.direccion;
        this.fechaDeAlta = cliente.getFecha();
        this.tarifa = cliente.tarifa;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correoElectronico;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Calendar getFecha() {
        return fechaDeAlta;

    }

    @Override
    public String toString() {
        return "Empresa {" + "\n" +
                "   nif=" + getNif() + "\n" +
                "   nombre=" + getNombre() + "\n" +
                "   correoElectronico=" + getCorreo() + "\n" +
                "   direccion=" + getDireccion() + "\n" +
                "   fechaDeAlta=" + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (getFecha().get(Calendar.MONTH) + 1) + "/" + getFecha().get(Calendar.YEAR) + "\n" +
                "   tarifa=" + getTarifa().getPrecio() + "e/min" + "\n" +
                "}" + "\n";
    }

}
