package swing.modelo.clientes;

import swing.modelo.facturas.Tarifa;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Empresa extends Cliente implements Serializable {

    public Empresa(String nif, String nombre, String correo, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correo, direccion, tarifa);
    }


    @Override
    public String toString() {
        return "Empresa {" + "\n" +
                "   NIF: " + this.nif() + "\n" +
                "   Nombre: " + this.nombre() + "\n" +
                "   Correo: " + this.correo() + "\n" +
                "   Dirección: " + this.direccion() + "\n" +
                "   Tarifa: " + this.tarifa() + "\n" +
                "   Fecha de alta: " + this.fecha().get(GregorianCalendar.DAY_OF_MONTH) + "/" + this.fecha().get(GregorianCalendar.MONTH) + "/" + this.fecha().get(GregorianCalendar.YEAR) + "\n" +
                "}" + "\n";
    }

}
