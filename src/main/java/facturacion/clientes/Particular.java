package facturacion.clientes;

import facturacion.facturas.Tarifa;

import java.util.Calendar;

public class Particular extends Cliente {

    private String apellidos;

    public Particular() {
        super();
    }

    public Particular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion, tarifa);
        this.apellidos = apellidos;
    }

    public Particular(Particular particular) {
        super(particular);
        this.apellidos = particular.apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Particular{" + "\n" +
                "   nif=" + getNif() + "\n" +
                "   nombre=" + getNombre() + "\n" +
                "   apellidos=" + apellidos + "\n" +
                "   correoElectronico=" + getCorreo() + "\n" +
                "   direccion=" + getDireccion() + "\n" +
                "   fechaDeAlta=" + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (getFecha().get(Calendar.MONTH) + 1) + "/" + getFecha().get(Calendar.YEAR) + "\n" +
                "   tarifa=" + getTarifa().getPrecio() + "e/min" + "\n" +
                "}" + "\n";
    }

}
