package facturacion.clientes;

import facturacion.facturas.Tarifa;

import java.util.GregorianCalendar;

public class Particular extends Cliente {

    private String apellidos;

    public Particular() {
        super();
    }

    public Particular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Particular {" + "\n" +
                "   nif=" + getNif() + "\n" +
                "   nombre=" + getNombre() + "\n" +
                "   apellidos=" + apellidos + "\n" +
                "   correoElectronico=" + getCorreo() + "\n" +
                "   direccion=" + getDireccion() + "\n" +
                "   fechaDeAlta=" + getFecha().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (getFecha().get(GregorianCalendar.MONTH) + 1) + "/" + getFecha().get(GregorianCalendar.YEAR) + "\n" +
                "   tarifa=" + getTarifa().descripcion() + "\n" +
                "}" + "\n";
    }

}
