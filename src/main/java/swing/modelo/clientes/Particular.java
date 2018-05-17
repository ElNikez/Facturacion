package swing.modelo.clientes;

import swing.modelo.facturas.Tarifa;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Particular extends Cliente implements Serializable {

    private String apellidos;

    public Particular(String nif, String nombre, String apellidos, String correo, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correo, direccion, tarifa);
        this.apellidos = apellidos;
    }

    public String apellidos() {
        return this.apellidos;
    }

    @Override
    public String toString() {
        return "Particular {" + "\n" +
                "   NIF: " + this.nif() + "\n" +
                "   Nombre: " + this.nombre() + "\n" +
                "   Apellidos: " + this.apellidos() + "\n" +
                "   Correo: " + this.correo() + "\n" +
                "   Dirección: " + this.direccion() + "\n" +
                "   Tarifa: " + this.tarifa() + "\n" +
                "   Fecha de alta: " + this.fecha().get(GregorianCalendar.DAY_OF_MONTH) + "/" + this.fecha().get(GregorianCalendar.MONTH) + "/" + this.fecha().get(GregorianCalendar.YEAR) + "\n" +
                "}" + "\n";
    }

}
